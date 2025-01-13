package ch.tbz.categoryservice.domain;

import ch.tbz.categoryservice.core.generic.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "category")
/*
    @Question: bruuche mir das?: @JsonIgnoreProperties(value = { "id" })
 */
public class Category extends AbstractEntity {

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    @Size(max = 30, message = "Name must be less than 30 characters")
    private String name;

    @Column(name = "description")
    @Size(max = 50, message = "Description must be less than 50 characters")
    private String description;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @ElementCollection
    @CollectionTable(name = "category_task_ids", joinColumns = @JoinColumn(name = "category_id"))
    @Column(name = "task_id")
    private List<UUID> taskIds;

    public Category() {
    }

    public Category(UUID id, String name, String description, UUID userId, List<UUID> taskIds) {
        super(id);
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.taskIds = taskIds;
    }
}
