package main;

import org.junit.jupiter.api.Test;

import exception.MeowerException;
import meower.TaskList;
import task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTest(){
        TaskList tasks = new TaskList();
        Task testTask = Task.empty();
        try {
            tasks.add(testTask);
            assertEquals(tasks.get(1), testTask);
        } catch (MeowerException e) {
            return;
        }
    }

    @Test
    public void sizeTest(){
        TaskList tasks = new TaskList();
        Task testTask = Task.empty();
        int testSize = 5;
        for (int i = 0; i < testSize; i++) {
            tasks.add(testTask);
        }
        assertEquals(tasks.getSize(), testSize);
    }

    @Test
    public void deleteTest(){
        TaskList tasks = new TaskList();
        Task testTask = Task.empty();
        try {
            tasks.add(testTask);
            tasks.delete(1);
            assertEquals(tasks.isEmpty(), true);
        } catch (MeowerException e) {
            return;
        }
    }
    
}
