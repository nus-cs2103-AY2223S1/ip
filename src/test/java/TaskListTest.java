package anya;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    private static List<Task> dummyList1 = new ArrayList<Task>();
    private final Ui dummyUi = new Ui();

    public void addDummyTasks(List<Task> dummyList) {
        dummyList.add(new Deadline("finish assignment 1", "2022-09-15"));
        dummyList.add(new Deadline("finish assignment 2", "2022-09-20"));
        dummyList.add(new ToDo("learn violin"));
        dummyList.add(new Event("violin trial lesson", "2022-12-11"));
        dummyList.add(new Deadline("finish hw 1", "2022-01-01"));
    }
    @Test
    public void constructorTest1() {
        addDummyTasks(dummyList1);
        TaskList dummyTasks = new TaskList(dummyList1);
        String actualOutput = dummyUi.printList(dummyTasks.getList());
        String expectedOutput = dummyUi.printList(dummyList1);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void constructorTest2() {
        TaskList dummyTasks = new TaskList(dummyList1);
        String actualOutput = dummyUi.printList(dummyTasks.getList());
        String expectedOutput = dummyUi.printList(dummyList1);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void addTaskTest() {
        Task dummyTask = new Deadline("finish matlab code", "2022-10-12");
        addDummyTasks(dummyList1);
        List<Task> dummyList2 = new ArrayList<Task>();
        addDummyTasks(dummyList2);
        TaskList dummyTasks = new TaskList(dummyList1);
        dummyTasks.add(dummyTask);
        String actualOutput = dummyUi.printList(dummyTasks.getList());
        String expectedOutput = dummyUi.printList(dummyList2) + "6. " + dummyTask.toString() + "\n";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void findTaskWithKeywordTest() {
        addDummyTasks(dummyList1);
        TaskList dummyTasks = new TaskList(dummyList1);
        String actualOutput = dummyUi.printMatchingList(dummyTasks.findTaskWithThisKeyword("violin"));
        List<Task> dummyList2 = new ArrayList<Task>();
        dummyList2.add(new ToDo("learn violin"));
        dummyList2.add(new Event("violin trial lesson", "2022-12-11"));
        String expectedOutput = dummyUi.printMatchingList(dummyList2);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void getSortedDeadlinesListTest() {
        addDummyTasks(dummyList1);
        TaskList dummyTasks = new TaskList(dummyList1);
        String actualOutput = dummyUi.printSortedDeadlineList(dummyTasks.getSortedDeadlinesList());
        List<Deadline> dummyList2 = new ArrayList<Deadline>();
        dummyList2.add(new Deadline("finish hw 1", "2022-01-01"));
        dummyList2.add(new Deadline("finish assignment 1", "2022-09-15"));
        dummyList2.add(new Deadline("finish assignment 2", "2022-09-20"));
        String expectedOutput = dummyUi.printSortedDeadlineList(dummyList2);
        assertEquals(expectedOutput, actualOutput);
    }

}
