package com.zx2n19.photosite.dao;

import com.zx2n19.photosite.pojo.Product;
import com.zx2n19.photosite.pojo.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageDAO extends JpaRepository<ProductImage, Integer> {
    List<ProductImage> findByProductAndTypeOrderByIdDesc(Product product, String type);
}
