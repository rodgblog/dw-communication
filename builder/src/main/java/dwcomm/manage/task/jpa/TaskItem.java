package dwcomm.manage.task.jpa;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import dwcomm.manage.task.TaskState;
import dwcomm.manage.task.TaskType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TaskItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TaskType type;
    private Integer count;
    private Integer processedCount;
    private TaskState taskState;
    // Количество элементов в листах для типа GENERATE_LISTS_OF_LONG
    private Integer countInList;
}


