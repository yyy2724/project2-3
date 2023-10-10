package org.spring.dev.company.entity.pay;

import org.spring.dev.company.entity.util.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "c_pay")
public class PayEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pay_price")
    private String price;

}
