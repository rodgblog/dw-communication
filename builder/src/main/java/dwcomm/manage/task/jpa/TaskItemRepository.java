package dwcomm.manage.task.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dwcomm.manage.task.TaskState;

import java.util.List;

@Repository
public interface TaskItemRepository extends JpaRepository<TaskItem, Long> {
    List<TaskItem> findAllByTaskState(TaskState taskState);

}
