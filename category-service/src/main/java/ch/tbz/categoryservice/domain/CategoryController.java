package ch.tbz.categoryservice.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Transactional
    @PostMapping("")
    public ResponseEntity<Category> newCategory(@Valid @RequestBody Category category, UUID userId) {
        log.trace("Creating new Category with name: {}", category.getName());
        return new ResponseEntity<>(categoryService.createCategory(category, userId), HttpStatus.CREATED);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<Category>> getAllCategories(@PathVariable UUID id) {
        log.trace("Getting all Categories by user id: {}", id);
        List<Category> categories = categoryService.getCategoriesByUserId(id);
        log.info("Returning all Categories");
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable UUID id) {
        log.trace("Getting Category with id: {}", id);
        Category category = categoryService.findById(id);
        log.info("Returning Category with id: {}", id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable UUID id, @Valid @RequestBody Category category) {
        log.trace("Updating Category with id: {}", id);
        Category updatedCategory = categoryService.updateCategory(id, category);
        log.info("Category with id: {} updated", id);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        log.trace("Deleting Category with id: {}", id);
        categoryService.deleteCategory(id);
        log.info("Category with id: {} deleted", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}