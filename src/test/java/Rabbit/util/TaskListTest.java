package Rabbit.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import Rabbit.RabbitException.RabbitException;
import java.time.LocalDateTime;

public class TaskListTest {

    @Test
    public void addToList_addTask_taskAdded() {
        try {
            TaskList list = new TaskList();
            LocalDateTime time = LocalDateTime.of(2022, 12, 12, 12, 00);
            list.addToList(TaskList.TaskType.EVENT, "event do homework /2022-12-12-1200");
            System.out.println(list.get(0));
            assertEquals(list.get(0).toString(), "[E][ ] do homework at 2022-12-12 12:00");
        } catch (RabbitException e) {
            System.out.println("Test failed due to exception.");
            fail();
        }
    }
}