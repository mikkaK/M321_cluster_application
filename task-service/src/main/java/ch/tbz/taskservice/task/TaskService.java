package ch.tbz.taskservice.task;

import ch.tbz.taskservice.core.generic.AbstractService;

import javax.management.InstanceAlreadyExistsException;

public interface TaskService extends AbstractService<Task> {
    Task createTask(Task task) throws InstanceAlreadyExistsException;
}
