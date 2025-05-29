package com.wcrs.recipe.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class MenuGroup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "menu_group_seq", sequenceName = "menu_group_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String groupName;

    private String description;

    @Column(nullable = false)
    private boolean isActive;

    private boolean isAvailable;

    private LocalDate availableDay;

    private Period availableFrom;
    private Period availableTo;

    @OneToMany(mappedBy = "menuGroup")
    private List<Recipe> recipe;
}
