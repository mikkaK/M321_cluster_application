package ch.tbz.categoryservice.domain;

import ch.tbz.categoryservice.core.generic.AbstractRepository;
import ch.tbz.categoryservice.core.generic.AbstractServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Log4j2
public class CategoryServiceImpl extends AbstractServiceImpl<Category> implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    protected CategoryServiceImpl(
            AbstractRepository<Category> repository,
            CategoryRepository categoryRepository
    ) {
        super(repository);
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryById(UUID id) throws NoSuchElementException {
        if (categoryRepository.existsById(id)) {
            return categoryRepository.findById(id).orElseThrow(NoSuchElementException::new);
        } else throw new NoSuchElementException(String.format("Entity with ID '%s' could not be found", id));
    }

    @Override
    public Category updateCategory(UUID id, Category category) throws NoSuchElementException {
        if (categoryRepository.existsById(id)) {
            category.setId(id);
           return categoryRepository.save(category);
        } else throw new NoSuchElementException(String.format("Entity with ID '%s' could not be found", category.getId()));
    }

    @Override
    public Category createCategory(Category category, UUID userId) {
        return repository.save(
                new Category(
                        category.getId(),
                        category.getName(),
                        category.getDescription(),
                        userId,
                        category.getTaskIds()
                )
        );
    }

    @Override
    public void deleteCategory(UUID id) throws NoSuchElementException {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else throw new NoSuchElementException(String.format("Entity with ID '%s' could not be found", id));
    }

    @Override
    public List<Category> getCategoriesByUserId(UUID userId) {
        return categoryRepository.findByUserId(userId);
    }
}
