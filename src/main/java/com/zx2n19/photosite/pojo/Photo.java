package com.zx2n19.photosite.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "photo")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Document(indexName = "photosite",type = "photo")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="uid")
    private User user;

    private String name;
    private String category;
    private String description;
    private String shutterSpeed;
    private String aperture;
    private String iso;
    private String camera;
    private Date createDate;

    @Transient
    private int commentCount;

}
