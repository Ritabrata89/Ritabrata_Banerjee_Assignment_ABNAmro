package com.abn.recipemanagement.repository;

import com.abn.recipemanagement.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeManagementRepository extends JpaRepository<Recipe, Long> {
}
