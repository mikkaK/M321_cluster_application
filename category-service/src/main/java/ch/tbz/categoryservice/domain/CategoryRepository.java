package ch.tbz.categoryservice.domain;

import ch.tbz.categoryservice.core.generic.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends AbstractRepository<Category> {
    List<Category> findByUserId(UUID userId);
}
