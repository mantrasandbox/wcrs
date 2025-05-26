package com.wcrs.inventory.model;

import com.wcrs.inventory.enums.SupplierCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Data
public class Supplier {

    @Id
    @Column(nullable = false, updatable = false, unique = true,name = "supplier_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "supplier_seq")
    @SequenceGenerator(sequenceName = "supplier_seq",name = "supplier_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;
    private String address;

    @Column(nullable = false, unique = true)
    private String phoneContact;

    @Column(unique = true)
    private String email;
    private String website;
    private String logo;

    @Enumerated(EnumType.STRING)
    private SupplierCategory supplierCategory;

    @Column(unique = true)
    private Integer tin;

    @Column(unique = true)
    private String nin;

    @OneToMany(mappedBy = "supplier")
    private List<Material> material;

    @CreatedDate
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime modifiedAt;
}
