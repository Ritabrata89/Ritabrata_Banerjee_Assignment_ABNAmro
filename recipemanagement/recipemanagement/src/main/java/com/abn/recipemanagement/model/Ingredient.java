package com.abn.recipemanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

import java.util.Objects;

import static com.abn.recipemanagement.config.DBConstants.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"name"})
public class Ingredient {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_IDENTIFIER_INGREDIENT_ID)
    private Long id;
    @Column(name = COLUMN_NAME_IDENTIFIER_NAME)
    private String name;
}
