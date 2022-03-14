package com.abn.recipemanagement.model;

import com.abn.recipemanagement.config.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

import static com.abn.recipemanagement.config.DBConstants.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "id", "lastUpdated", "category", "serves", "ingredients", "instruction" })
public class Recipe {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_IDENTIFIER_RECIPE_ID)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @Column(name = COLUMN_NAME_IDENTIFIER_LASTUPDATE)
    private LocalDateTime lastUpdated; //(formatted as dd‐MM‐yyyy HH:mm)
    @Column(name = COLUMN_NAME_IDENTIFIER_CATEGORY)
    private Category category;
    @Column(name = COLUMN_NAME_IDENTIFIER_SERVES)
    @Min(2)
    @Max(8)
    private Integer serves;
    @OneToMany(targetEntity = Ingredient.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_fk",referencedColumnName = COLUMN_NAME_IDENTIFIER_RECIPE_ID, nullable=false)
    @Column(name = COLUMN_NAME_IDENTIFIER_INGREDIENTS)
    private List<Ingredient> ingredients;
    @Pattern(message="Type can contain alphanumeric characters only", regexp = "[a-zA-Z0-9 ]+")
    @Column(name = COLUMN_NAME_IDENTIFIER_INSTRUCTION)
    private String instruction;
}
