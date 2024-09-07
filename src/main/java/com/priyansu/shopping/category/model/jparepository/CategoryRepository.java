package com.priyansu.shopping.category.model.jparepository;

import com.priyansu.shopping.category.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on Category entities.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
