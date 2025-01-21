package ch.tbz.statisticservice.task;

import ch.tbz.statisticservice.core.generic.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends AbstractRepository<Task> {
    List<Task> findByUserAndCategory(String user, String category);

    List<Task> findByUser(String user);
}
