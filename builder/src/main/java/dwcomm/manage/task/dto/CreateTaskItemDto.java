package dwcomm.manage.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import dwcomm.manage.task.TaskType;

@Getter
@Setter
@AllArgsConstructor
public class CreateTaskItemDto {
    private TaskType type;
    private Integer count;
    private Integer countInList;
}
