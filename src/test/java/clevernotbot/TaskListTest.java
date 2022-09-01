package clevernotbot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void getTaskListTest(){
        TaskList list = new TaskList(new ArrayList<Task>());
        Task task1 = new ToDo("Test1",false);
        list.addTask(task1);
        ArrayList<Task> testArray = new ArrayList<>();
        testArray.add(task1);
        assertEquals(list.getTaskList(),testArray);
    }

    @Test
    public void addTaskTest(){
        TaskList list = new TaskList(new ArrayList<Task>());
        Task task1 = new ToDo("Test1",false);
        Task task2 = new Event("Test2",false,"Aug 6th 2-4pm");
        Task task3 = new Deadline("Test3",false,"02-12-2022 18:00");
        list.addTask(task1);
        list.addTask(task2);
        list.addTask(task3);
        ArrayList<Task> testTasks = new ArrayList<>();
        testTasks.add(task1);
        testTasks.add(task2);
        testTasks.add(task3);
        assertEquals(list.getTaskList(),testTasks);
    }

    @Test
    public void removeTaskTest(){
        TaskList listActual = new TaskList(new ArrayList<Task>());
        Task task1 = new ToDo("Test1",false);
        Task task2 = new Event("Test2",false,"Aug 6th 2-4pm");
        Task task3 = new Deadline("Test3",false,"02-12-2022 18:00");
        listActual.addTask(task1);
        listActual.addTask(task2);
        listActual.addTask(task3);
        listActual.removeTask(task2);
        ArrayList<Task> arrayExpected = new ArrayList<>();
        arrayExpected.add(task1);
        arrayExpected.add(task3);
        assertEquals(arrayExpected,listActual.getTaskList());
    }

    @Test
    public void getTaskTest(){
        TaskList listActual = new TaskList(new ArrayList<Task>());
        Task task1 = new ToDo("Test1",false);
        Task task2 = new Event("Test2",false,"Aug 6th 2-4pm");
        Task task3 = new Deadline("Test3",false,"02-12-2022 18:00");
        listActual.addTask(task1);
        listActual.addTask(task2);
        listActual.addTask(task3);
        listActual.removeTask(task1);
        assertEquals(task2,listActual.getTask(0));
    }



}
