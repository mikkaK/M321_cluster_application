package ch.tbz.categoryservice.domain;

import ch.tbz.categoryservice.core.generic.AbstractService;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public interface CategoryService extends AbstractService<Category> {
    Category getCategoryById(UUID id) throws NoSuchElementException;
    Category updateCategory(UUID id, Category category) throws NoSuchElementException;
    Category createCategory(Category category, UUID userId);
    void deleteCategory(UUID id) throws NoSuchElementException;
    List<Category> getCategoriesByUserId(UUID userId);
}
