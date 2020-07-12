package com.zx2n19.photosite.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Document(indexName = "photosite",type = "product")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String name;
    private String description;
    private String category;
    private double price;
    private int stock;
    private Date createDate;

    @Transient
    private ProductImage firstProductImage;

    @ManyToOne
    @JoinColumn(name="uid")
    private User user;

}
