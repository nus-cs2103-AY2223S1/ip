package anya.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

public class TaskListTest {

    private ArrayList<Task> tasksArrayList;
    private TaskList tasks;

    @BeforeEach
    public void init() {
        Task todoTask = new Todo("Test Todo Task");
        Task deadlineTask = new Deadline("Test Deadline Task",
                LocalDateTime.parse("01/01/2022 1800", DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
        Task eventTask = new Event("Test Event Task", "Test Location");

        this.tasksArrayList = new ArrayList<>();
        this.tasksArrayList.add(todoTask);
        this.tasksArrayList.add(deadlineTask);
        this.tasksArrayList.add(eventTask);
        this.tasks = new TaskList(this.tasksArrayList);
    }

    @Test
    public void addTaskTest() {
        Task  todoTask = new Todo("Add Todo task");
        tasks.addTask(todoTask);
        int expected = 4;

        assertEquals(expected, tasksArrayList.size());
    }

    @Test
    public void deleteTaskFromIndexTest() {
        int indexToDelete = 3;
        tasks.deleteTaskFromIndex(indexToDelete);
        int expected = 2;

        assertEquals(expected, tasksArrayList.size());
    }

    @Test
    public void getLengthTest() {
        int expected = 3;

        assertEquals(expected, tasks.getLength());
    }

    @Test
    public void getTaskFromIndexTest() {
        int index = 3;
        Task task = tasks.getTaskFromIndex(index);
        Task expected = new Event("Test Event Task", "Test Location");

        assertEquals(expected, task);
    }
}
