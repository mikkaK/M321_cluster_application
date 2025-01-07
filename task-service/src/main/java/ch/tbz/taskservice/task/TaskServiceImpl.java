package ch.tbz.taskservice.task;

import ch.tbz.taskservice.core.generic.AbstractRepository;
import ch.tbz.taskservice.core.generic.AbstractServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;

@Service
@Log4j2
public class TaskServiceImpl extends AbstractServiceImpl<Task> implements TaskService {

    protected TaskServiceImpl(AbstractRepository<Task> repository, TaskRepository taskRepository) {
        super(repository);
    }

    @Override
    public Task createTask(Task task) throws InstanceAlreadyExistsException {
        return repository.save(task);
    }
}
