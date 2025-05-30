package com.wcrs.inventory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Data
public class Material {

    @Id
    @Column(nullable = false, updatable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "material_seq")
    @SequenceGenerator(sequenceName = "material_seq",name = "material_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String unitOfMeasure;

    @Column(nullable = false)
    private BigDecimal costPerUnit;

    @Column(nullable = false)
    private Double quantity;

    @Column(nullable = false)
    private BigDecimal totalCost;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;


    //Use S3 for images storage
    //private String image;

    @CreatedDate
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime modifiedAt;

    public void setTotalCost(){
        this.totalCost = BigDecimal.valueOf(quantity).multiply(costPerUnit);
    }

}
