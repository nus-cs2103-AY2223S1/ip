package mort.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    private TaskListTestUtil testUtil = new TaskListTestUtil();
    private TaskList taskList = testUtil.getTaskList();

    @Test
    public void testList() {
        String expected = "1. [T][ ] buy 6 apples & chicken\n"
                + "2. [D][X] spanish video project (by: 16 Sep 2022, 12:00 PM)\n"
                + "3. [E][ ] CS2106 midterm (at: 1 Oct 2022, 10:00 AM)\n"
                + "4. [D][ ] french video project (by: 16 Sep 2022)\n"
                + "5. [E][X] Ann's Birthday (at: 1 Oct 2022)\n";

        assertEquals(expected, taskList.list());
    }

    @Test
    public void addTask_correctTasksAdded() {
        taskList.addTask(testUtil.getNewTask("T"));
        taskList.addTask(testUtil.getNewTask("D"));
        taskList.addTask(testUtil.getNewTask("E"));

        String expected1 = "[T][ ] get new kicks";
        String expected2 = "[D][ ] CS2106 lab 2 (by: 1 Oct 2022)";
        String expected3 = "[E][ ] CS2105 Midterm (at: 3 Oct 2022, 2:00 PM)";

        assertEquals(expected1, testUtil.getTask(5));
        assertEquals(expected2, testUtil.getTask(6));
        assertEquals(expected3, testUtil.getTask(7));
    }

    @Test
    public void deleteTask_correctTaskRemoved() {
        taskList.deleteTask(1);
        String expected = "[E][ ] CS2106 midterm (at: 1 Oct 2022, 10:00 AM)";
        assertEquals(expected, testUtil.getTask(1));
    }

    @Test
    public void deleteTask_correctTaskString() {
        String expected = "[D][X] spanish video project (by: 16 Sep 2022, 12:00 PM)";
        assertEquals(expected, taskList.deleteTask(1).toString());
    }

    @Test
    public void markTask_taskAlreadyMarked_taskRemainsMarked() {
        String expected = "You can't finish the same task twice, genius.\n"
                + "  [D][X] spanish video project (by: 16 Sep 2022, 12:00 PM)\n";
        assertEquals(expected, taskList.markTask(1));
    }

    @Test
    public void markTask_taskUnmarked_taskBecomesMarked() {
        String expected = "You really took your time with this one, didn't you?\n"
                + "  [E][X] CS2106 midterm (at: 1 Oct 2022, 10:00 AM)\n";
        assertEquals(expected, taskList.markTask(2));
    }

    @Test
    public void unmarkTask_taskAlreadyUnmarked_taskRemainsUnmarked() {
        String expected = "You're trying to unmark a task you haven't done.\n"
                + "Let that sink in for a moment.\n  [T][ ] buy 6 apples & chicken\n";
        assertEquals(expected, taskList.unmarkTask(0));
    }

    @Test
    public void unmarkTask_taskMarked_taskBecomesUnmarked() {
        String expected = "And here I was thinking you were getting somewhere...\n"
                + "  [D][ ] spanish video project (by: 16 Sep 2022, 12:00 PM)\n";
        assertEquals(expected, taskList.unmarkTask(1));
    }

    @Test
    public void testGetSaveFormat() {
        String expected = "T | 0 | buy 6 apples & chicken\n"
                + "D | 1 | spanish video project | 16/9/2022 1200\n"
                + "E | 0 | CS2106 midterm | 1/10/2022 1000\n"
                + "D | 0 | french video project | 16/9/2022\n"
                + "E | 1 | Ann's Birthday | 1/10/2022\n";

        assertEquals(expected, taskList.getSaveFormat());
    }

    @Test
    public void testGetSize() {
        assertEquals(5, taskList.getSize());
    }

    @Test
    public void find_caseInsensitive() {
        String expected = "1. [T][ ] buy 6 apples & chicken\n";
        assertEquals(expected, taskList.find("applE"));
    }

    @Test
    public void find_matchesWholeTaskString() {
        String expected1 = "1. [T][ ] buy 6 apples & chicken\n"
                + "2. [E][ ] CS2106 midterm (at: 1 Oct 2022, 10:00 AM)\n"
                + "3. [D][ ] french video project (by: 16 Sep 2022)\n";
        String expected2 = "1. [E][ ] CS2106 midterm (at: 1 Oct 2022, 10:00 AM)\n"
                + "2. [E][X] Ann's Birthday (at: 1 Oct 2022)\n";

        assertEquals(expected1, taskList.find("[ ]"));
        assertEquals(expected2, taskList.find("at: 1 O"));
    }

    @Test
    public void find_doesNotMatchIndex() {
        String expected = "";
        assertEquals(expected, taskList.find("3."));
    }

    @Test
    public void testViewSchedule() {
        String expected1 = "";
        String expected2 = "1. [D][X] spanish video project (by: 16 Sep 2022, 12:00 PM)\n"
                + "2. [D][ ] french video project (by: 16 Sep 2022)\n";

        assertEquals(expected1, taskList.viewSchedule(testUtil.getDate(3)));
        assertEquals(expected2, taskList.viewSchedule(testUtil.getDate(1)));
    }

}
