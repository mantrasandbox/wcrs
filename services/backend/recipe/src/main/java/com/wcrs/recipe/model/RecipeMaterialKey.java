package com.wcrs.recipe.model;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class RecipeMaterialKey implements Serializable {

    private Integer recipeId;
    private Integer MaterialId;
}
