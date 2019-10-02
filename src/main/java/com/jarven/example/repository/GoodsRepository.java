package com.jarven.example.repository;

import com.jarven.example.domain.product.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019-05-10 16:30
 */
public interface GoodsRepository extends JpaRepository<Goods,Integer> {
}
