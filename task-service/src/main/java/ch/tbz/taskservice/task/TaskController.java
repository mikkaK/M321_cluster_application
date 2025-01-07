package ch.tbz.taskservice.task;

import ch.tbz.taskservice.task.dto.TaskDTO;
import ch.tbz.taskservice.task.dto.TaskMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;
    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable UUID id) {
        log.trace("Getting task with id: {}", id);
        Task task = taskService.findById(id);
        log.info("Returning task with id: {}", id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Task>> getAllTasks() {
        log.trace("Getting all tasks");
        List<Task> tasks = taskService.findAll();
        log.info("Returning all tasks");
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("")
    public ResponseEntity<TaskDTO> newTask(@Valid @RequestBody TaskDTO taskDTO) throws InstanceAlreadyExistsException {
        log.trace("Creating new task with name: {}, description: {}, done: {}, category: {}", taskDTO.getName(), taskDTO.getDescription(), taskDTO.isDone(), taskDTO.getCategory());
        Task task = taskMapper.fromDTO(taskDTO);
        taskService.createTask(task);
        log.info("Task {} created with id: {}",task.getName(), task.getId());
        return new ResponseEntity<>(taskMapper.toDTO(task), HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable UUID id, @Valid @RequestBody TaskDTO taskDTO) {
        log.trace("Updating task with id: {}", id);
        Task task = taskMapper.fromDTO(taskDTO);
        taskService.updateById(id, task);
        log.info("Task with id: {} updated", id);
        return new ResponseEntity<>(taskMapper.toDTO(task), HttpStatus.OK);
    }


    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        log.trace("Deleting task with id: {}", id);
        taskService.deleteById(id);
        log.info("Task with id: {} deleted", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
