package ado.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

//import org.junit.Before;
import org.junit.jupiter.api.Test;



public class TaskListTest {

    @Test
    public void taskList_constructor_success() {
        TaskList taskList = new TaskList();
        assertEquals(false, taskList == null);
    }

    @Test
    public void taskList_constructor_success2() {
        List<Task> list = new ArrayList<>();
        list.add(new Todo("Task 1"));
        list.add(new Todo("Task 2"));
        list.add(new Todo("Task 3"));

        TaskList taskList = new TaskList(list);
        assertEquals(false, taskList == null);
    }

    @Test
    void test_addTask() {
        List<Task> list = new ArrayList<>();
        list.add(new Todo("Task 1"));
        list.add(new Todo("Task 2"));
        list.add(new Todo("Task 3"));
        TaskList taskList = new TaskList(list);
        Todo task4 = new Todo("Task 4");
        taskList.addTask(task4);
        assertEquals(4, taskList.listSize());
        assertEquals(true, taskList.getList().contains(task4));
    }

    @Test
    void test_removeTask() {
        List<Task> list = new ArrayList<>();
        Todo task1 = new Todo("Task 1");
        list.add(task1);
        list.add(new Todo("Task 2"));
        list.add(new Todo("Task 3"));
        TaskList taskList = new TaskList(list);

        taskList.removeTaskAtIndex(0);
        assertEquals(2, taskList.listSize());
        assertEquals(false, taskList.getList().contains(task1));

    }

    @Test
    void test_getTaskAtIndex() {
        List<Task> list = new ArrayList<>();
        Todo task1 = new Todo("Task 1");
        Todo task2 = new Todo("Task 2");
        Todo task3 = new Todo("Task 3");
        list.add(task1);
        list.add(task2);
        list.add(task3);
        TaskList taskList = new TaskList(list);

        assertEquals(task1, taskList.getTaskAtIndex(0));
        assertEquals(task2, taskList.getTaskAtIndex(1));
        assertEquals(task3, taskList.getTaskAtIndex(2));
    }

    @Test
    void listsize_zero() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.listSize());
    }

    @Test
    void listsize_nonzero() {
        List<Task> list = new ArrayList<>();
        Todo task1 = new Todo("Task 1");
        Todo task2 = new Todo("Task 2");
        Todo task3 = new Todo("Task 3");
        list.add(task1);
        list.add(task2);
        list.add(task3);
        TaskList taskList = new TaskList(list);

        assertEquals(3, taskList.listSize());
    }

    @Test
    public void test_getList() {
        List<Task> list = new ArrayList<>();
        Todo task1 = new Todo("Task 1");
        Todo task2 = new Todo("Task 2");
        Todo task3 = new Todo("Task 3");
        list.add(task1);
        list.add(task2);
        list.add(task3);
        TaskList taskList = new TaskList(list);

        assertEquals(list, taskList.getList());
    }
}
