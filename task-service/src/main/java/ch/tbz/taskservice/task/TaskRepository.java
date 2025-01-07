package ch.tbz.taskservice.task;

import ch.tbz.taskservice.core.generic.AbstractRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends AbstractRepository<Task> {
}
