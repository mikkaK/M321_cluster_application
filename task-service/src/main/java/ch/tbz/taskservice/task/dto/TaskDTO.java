package ch.tbz.taskservice.task.dto;

import ch.tbz.taskservice.core.generic.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TaskDTO extends AbstractDTO implements Serializable {
    private UUID id;
    private String name;
    private String description;
    private boolean done;
    private String category;
}
