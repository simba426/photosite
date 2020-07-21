package com.zx2n19.photosite.service;


import com.zx2n19.photosite.dao.ProductImageDAO;
import com.zx2n19.photosite.pojo.Product;
import com.zx2n19.photosite.pojo.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductImageService {

    public static final String type_detail = "detail";

    @Autowired
    ProductImageDAO productImageDAO;
    @Autowired
    ProductService productService;

    public void add (ProductImage bean) {
        productImageDAO.save(bean);
    }

    public void delete(int id) {
        productImageDAO.delete(id);
    }

    public ProductImage get(int id) {
        return productImageDAO.findOne(id);
    }

    public List<ProductImage> listDetailProductImages(Product product) {
        return productImageDAO.findByProductAndTypeOrderByIdDesc(product, type_detail);
    }
}
