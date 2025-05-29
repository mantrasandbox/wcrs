package com.wcrs.recipe.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@ToString(exclude = "menuGroup")
public class Recipe extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_seq")
    @SequenceGenerator(name = "recipe_seq", allocationSize = 1, sequenceName = "recipe_seq")
    private Integer id;

    private String recipeName;
    private String description;

    private BigDecimal price;

    private BigDecimal profit;

    private String recipeTitle;

    private BigDecimal totalPrice;

    private int servings;

    private boolean available;

    @ManyToOne
    @JoinColumn(name = "menu_group_id")
    private MenuGroup menuGroup;

    @OneToMany(mappedBy = "recipe")
    private List<RecipeMaterial> recipeMaterials;
}
