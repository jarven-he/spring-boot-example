package com.jarven.example.service;

import com.jarven.example.domain.PageVo;
import com.jarven.example.domain.ResponseVo;
import com.jarven.example.domain.product.Goods;
import com.jarven.example.domain.product.Product;
import com.jarven.example.domain.product.dto.*;
import com.jarven.example.domain.product.vo.LinkProductVo;
import com.jarven.example.domain.product.vo.ProductDetailVo;
import com.jarven.example.domain.product.vo.ProductVo;
import com.jarven.example.domain.product.vo.RecommendProductVo;
import com.jarven.example.repository.GoodsRepository;
import com.jarven.example.repository.ProductRepository;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RedissonClient;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.*;

import static com.jarven.example.constant.RedisKey.PRODUCT_SALE_INVENTORY_;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019-04-29 14:21
 */
@Service
@Transactional
public class ProductService extends BaseService {

    @Resource
    private ProductRepository productRepository;

    @Resource
    private GoodsRepository goodsRepository;

    @Resource
    private RedissonClient redissonClient;

    /**
     * 分页查询商品
     *
     * @param productPageDto 查询信息
     * @return 商品信息
     */
    @Transactional(readOnly = true)
    public ResponseVo<PageVo<ProductVo>> queryPage(QueryProductPageDto productPageDto) {
        var sort = StringUtils.isEmpty(getUserId()) ? Sort.by("updateTime").descending() : Sort.by("sortWeight").descending();
        var page = productRepository.findAll((Root<Product> root,
                                              CriteriaQuery<?> query,
                                              CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            //根据分类查询
            if (productPageDto.getCategory() != null) {
                var category = cb.equal(root.get("category")
                        .as(Integer.class), productPageDto.getCategory());
                predicates.add(category);
            }
            //如果搜索条件不为空是前端查询
            if (!StringUtils.isEmpty(productPageDto.getSearchText())) {
                var searchText = cb.like(root.get("productName")
                        .as(String.class), productPageDto.getSearchText() + "%");
                predicates.add(searchText);
            } else {
                //根据销售状态查询
                if (productPageDto.getSaleState() != null) {
                    var saleState = cb.equal(root.get("saleState")
                            .as(Integer.class), productPageDto.getSaleState());
                    predicates.add(saleState);
                }

                //商品类型
                if (productPageDto.getProductType() != null) {
                    var productType = cb.equal(root.get("productType")
                            .as(Integer.class), productPageDto.getProductType());
                    predicates.add(productType);
                }

                //根据商品名称查询
                if (!StringUtils.isEmpty(productPageDto.getProductName())) {
                    var productName = cb.like(root.get("productName")
                            .as(String.class), "%" + productPageDto.getProductName() + "%");
                    predicates.add(productName);
                }
            }
            //查询列表展示的数据
            if (!StringUtils.isEmpty(getUserId())) {
                var deleteFlag = cb.equal(root.get("showList")
                        .as(Integer.class), 1);
                predicates.add(deleteFlag);
            }
            //查询未删除货物
            var deleteFlag = cb.equal(root.get("deleteFlag")
                    .as(Integer.class), 0);
            predicates.add(deleteFlag);
            var predicateArray = new Predicate[predicates.size()];
            predicates.toArray(predicateArray);
            return cb.and(predicateArray);
        }, getPageRequest(productPageDto, sort));
        //库存放在redis中,需要从redis中获取
        var productList = page.getContent().stream().map(product -> {
            var productVo = product.convertTo(ProductVo.class);
            //获取库存
            var inventory = redissonClient.getAtomicLong(PRODUCT_SALE_INVENTORY_ + product.getProductId());
            return productVo.setInventory(inventory.get())
                    .setSpec(product.getSpec());
        }).collect(toList());
        return ResponseVo.success(new PageVo<ProductVo>(page).setContent(productList));
    }

    /**
     * 查询商品详情
     *
     * @param productId 商品Id
     * @return 商品详情
     */
    @Transactional(readOnly = true)
    public ResponseVo<ProductDetailVo> query(Integer productId) {
        //查询商品详情(经过缓存)
        var productDetail = findDetailById(productId);
        if (productDetail != null) {
            //从redis中获取库存
            var inventory = redissonClient.getAtomicLong(PRODUCT_SALE_INVENTORY_ + productDetail.getProductId());
            productDetail.setInventory(inventory.get());
            return ResponseVo.success(productDetail);
        }
        return ResponseVo.fail("商品不存在或者已下架");
    }

    /**
     * 修改商品信息
     *
     * @param productDto 商品信息
     * @return 修改结果
     */
    public ResponseVo modify(ModifyProductDto productDto) {
        var product = productRepository.findById(productDto.getProductId()).orElse(null);
        if (product != null && product.getDeleteFlag() == 0) {
            productDto.convertTo(product)
                    .setUpdateTime(LocalDateTime.now());
            productRepository.save(product);
        }
        return ResponseVo.success();
    }

    /**
     * 删除商品
     *
     * @param productId 商品Id
     * @return 删除结果
     */
    public ResponseVo delete(Integer productId) {
        var product = productRepository.findById(productId).orElse(null);
        if (product != null && product.getDeleteFlag() == 0) {
            product.setDeleteFlag(1);
            productRepository.save(product);
        }
        return ResponseVo.success();
    }

    /**
     * 商品上下架
     *
     * @param productStateDto 上下架信息
     * @return 上下架结果
     */
    public ResponseVo setSaleState(SetProductStateDto productStateDto) {
        return productRepository.findById(productStateDto.getProductId()).map(product -> {
            product.setSaleState(productStateDto.getSaleState());
            productRepository.save(product);
            return ResponseVo.success();
        }).orElse(ResponseVo.fail());
    }

    /**
     * 推荐商品
     *
     * @param productDto 推荐商品信息
     * @return 推荐结果
     */
    public ResponseVo recommend(RecommendProductDto productDto) {
        return productRepository.findById(productDto.getProductId()).map(product -> {
            var recommendProduct = product.getRecommendProduct();
            if (CollectionUtils.isEmpty(recommendProduct)) {
                recommendProduct = new HashSet<>();
            }
            recommendProduct.addAll(productDto.getRecommendProductIds());
            //过滤掉自身
            recommendProduct = recommendProduct.stream()
                    .filter(productId -> !productId.equals(productDto.getProductId()))
                    .collect(toSet());
            product.setRecommendProduct(recommendProduct);
            productRepository.save(product);
            return ResponseVo.success();
        }).orElse(ResponseVo.fail("商品不存在"));
    }

    /**
     * 查询关联商品
     *
     * @param productId 主商品ID
     * @return 商品信息
     */
    @Transactional(readOnly = true)
    public ResponseVo<List<ProductVo>> queryRecommend(Integer productId) {
        return productRepository.findById(productId).map(product -> {
            List<ProductVo> list = new ArrayList<>();
            if (!CollectionUtils.isEmpty(product.getRecommendProduct())) {
                list = productRepository.findAllById(product.getRecommendProduct()).parallelStream()
                        .filter(recommend -> recommend.getSaleState() != 0)
                        .map(linkProduct -> linkProduct.convertTo(ProductVo.class))
                        .collect(toList());
            }
            return ResponseVo.success(list);
        }).orElse(ResponseVo.success(Collections.emptyList()));
    }



    /**
     * @param inventoryDto 库存
     * @return 修改结果
     */
    public ResponseVo addInventory(AddInventoryDto inventoryDto) {
        var inventory = redissonClient.getAtomicLong(PRODUCT_SALE_INVENTORY_ + inventoryDto.getProductId());
        inventory.addAndGet(inventoryDto.getInventory());
        return ResponseVo.success();
    }

    /**
     * 添加多规格商品
     *
     * @param productDto 商品信息
     * @return 添加结果
     */
    public ResponseVo addSpecProduct(AddSpecProductDto productDto) {
        var goods = new Goods().setGoodsName(productDto.getProductName());
        goodsRepository.save(goods);
        var products = productDto.getSpecProduct().stream().map(productSpecDto -> {
            var product = productDto.convertTo(Product.class)
                    .setGoodsId(goods.getGoodsId())
                    .setSpec(productSpecDto.getSpec())
                    .setShowList(0)
                    .setSortWeight(0)
                    .setMarketPrice(productSpecDto.getMarketPrice())
                    .setSalePrice(productSpecDto.getSalePrice());
            productRepository.save(product);
            //库存
            var inventory = redissonClient.getAtomicLong(PRODUCT_SALE_INVENTORY_ + product.getProductId());
            inventory.addAndGet(productSpecDto.getInventory());
            return product;
        }).collect(toList());
        //第一个列表显示
        productRepository.save(products.get(0).setShowList(1));
        return ResponseVo.success();
    }

    /**
     * 获取库存
     *
     * @param productId 商品id
     * @return 当前库存
     */
    public long getInventory(Integer productId) {
        var inventory = redissonClient.getAtomicLong(PRODUCT_SALE_INVENTORY_ + productId);
        return inventory.get();
    }

    /**
     * 扣库存
     *
     * @param reduceInventoryDto 扣减信息
     * @return 扣减结果
     */
    public boolean reduceInventory(ReduceInventoryDto reduceInventoryDto) {
        var inventory = redissonClient.getAtomicLong(PRODUCT_SALE_INVENTORY_ + reduceInventoryDto.getProductId());
        if (inventory.addAndGet(-reduceInventoryDto.getReduceNum()) < 0) {
            //扣库存失败,重新加回去
            inventory.addAndGet(reduceInventoryDto.getReduceNum());
            return false;
        }
        return true;
    }

    /**
     * 查询商品信息
     *
     * @return 商品信息
     */
    public ProductDetailVo findDetailById(Integer productId) {
        return productRepository.findById(productId).map(product -> {
            var productDetail = product.convertTo(ProductDetailVo.class).setSpec(product.getSpec());
            //查询关联商品
            if (product.getGoodsId() != null) {
                var linkProducts = productRepository.findAll(Example.of(new Product().setGoodsId(product.getGoodsId())))
                        .stream().map(linkProduct -> linkProduct.convertTo(LinkProductVo.class)
                                .setSpec(product.getSpec()))
                        .collect(toList());
                productDetail.setLinkProduct(linkProducts);
            }
            //查询推荐商品
            if (!CollectionUtils.isEmpty(product.getRecommendProduct())) {
                var recommendProducts = productRepository.findAllById(product.getRecommendProduct())
                        .stream().map(recommendProduct -> recommendProduct.convertTo(RecommendProductVo.class).setSpec(recommendProduct.getSpec()))
                        .collect(toList());
                productDetail.setRecommendProduct(recommendProducts);
            }
            return productDetail;
        }).orElse(null);
    }
}
