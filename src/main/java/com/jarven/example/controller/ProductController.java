package com.jarven.example.controller;

import com.jarven.example.domain.PageVo;
import com.jarven.example.domain.ResponseVo;
import com.jarven.example.domain.product.dto.*;
import com.jarven.example.domain.product.vo.ProductDetailVo;
import com.jarven.example.domain.product.vo.ProductVo;
import com.jarven.example.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019-04-29 14:14
 */
@RestController
@RequestMapping("product")
@Api(tags = "product", value = "商品接口")
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("query-page")
    @ApiOperation(value = "分页查询商品列表")
    public ResponseVo<PageVo<ProductVo>> queryPage(QueryProductPageDto productPageDto) {
        return productService.queryPage(productPageDto);
    }

    @GetMapping("query/{productId}")
    @ApiOperation(value = "查询商品详情")
    @ApiImplicitParam(name = "productId", value = "商品Id", paramType = "path")
    public ResponseVo<ProductDetailVo> query(@PathVariable Integer productId) {
        return productService.query(productId);
    }

    @ApiOperation(value = "删除商品")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete/{productId}")
    @ApiImplicitParam(name = "productId", value = "商品Id", paramType = "path")
    public ResponseVo delete(@PathVariable Integer productId) {
        return productService.delete(productId);
    }

    @PutMapping("modify")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "修改商品信息")
    public ResponseVo modify(@RequestBody ModifyProductDto productDto) {
        return productService.modify(productDto);
    }

    @PutMapping("set-sale-state")
    @ApiOperation(value = "上/下架")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseVo setSaleState(@RequestBody SetProductStateDto productStateDto) {
        return productService.setSaleState(productStateDto);
    }

    @PutMapping("recommend")
    @ApiOperation(value = "推荐商品")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseVo recommend(@RequestBody RecommendProductDto productDto) {
        return productService.recommend(productDto);
    }

    @ApiOperation(value = "查询推荐商品")
    @GetMapping("query-recommend/{productId}")
    @ApiImplicitParam(name = "productId", value = "商品Id", paramType = "path")
    public ResponseVo<List<ProductVo>> queryRecommend(@PathVariable Integer productId) {
        return productService.queryRecommend(productId);
    }

    @PostMapping("add-inventory")
    @ApiOperation(value = "增加库存")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseVo addInventory(@RequestBody AddInventoryDto inventoryDto) {
        return productService.addInventory(inventoryDto);
    }

    @ApiOperation("添加多规格商品")
    @PostMapping("add-spec-product")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseVo addSpecProduct(@RequestBody AddSpecProductDto productDto) {
        return productService.addSpecProduct(productDto);
    }
}
