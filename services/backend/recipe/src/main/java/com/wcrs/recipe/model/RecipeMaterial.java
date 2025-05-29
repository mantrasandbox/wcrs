package com.wcrs.recipe.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeMaterial extends BaseEntity{

    @EmbeddedId
    private RecipeMaterialKey id;

    private String comment;

    private BigDecimal cost;
    private Double quantity;

    @ManyToOne
    @MapsId("recipeId")
    private Recipe recipe;

    private Integer materialId; // reference key to material entity in inventory microservice

}
