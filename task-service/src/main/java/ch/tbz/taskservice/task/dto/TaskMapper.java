package ch.tbz.taskservice.task.dto;

import ch.tbz.taskservice.core.generic.AbstractMapper;
import ch.tbz.taskservice.task.Task;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper extends AbstractMapper<Task, TaskDTO> {

}
