package com.zx2n19.photosite.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;


@Entity
@Table(name = "liked")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Document(indexName = "photosite",type = "liked")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Liked {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="uid")
    private User user;

    @ManyToOne
    @JoinColumn(name="pid")
    private Photo photo;

}
