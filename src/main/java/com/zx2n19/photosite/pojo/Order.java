package com.zx2n19.photosite.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zx2n19.photosite.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "productorder")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String orderCode;
    private String address;
    private String receiver;
    private String email;
    private String userMessage;
    private Date createDate;
    private String status;
    private int number;
    private int sid;

    @ManyToOne
    @JoinColumn(name="cid")
    private User user;

    @ManyToOne
    @JoinColumn(name="pid")
    private Product product;

    public String getStatusDesc(){
        String desc ="未知";
        switch(status){
            case OrderService.waitPay:
                desc="待付款";
                break;
            case OrderService.waitDelivery:
                desc="待发货";
                break;
            case OrderService.waitConfirm:
                desc="待收货";
                break;
            case OrderService.waitReview:
                desc="等评价";
                break;
            case OrderService.finish:
                desc="完成";
                break;
            case OrderService.delete:
                desc="刪除";
                break;
            default:
                desc="未知";
        }
        return desc;
    }

}
