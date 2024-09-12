package tako;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import tako.task.Deadline;
import tako.task.Event;
import tako.task.Task;
import tako.task.Todo;

public class TaskListTest {
    @Test
    public void mark_validTaskNumber_success() throws TakoException {
        TaskList tasks = new TaskList();
        tasks.add(new Task("sleep"));
        tasks.mark(0);
        assertEquals("[X] sleep", tasks.get(0).toString());
    }

    @Test
    public void mark_invalidTaskNumber_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.mark(0);
            fail();
        } catch (TakoException e) {
            assertEquals("The task number to mark does not exist.", e.getMessage());
        }
    }

    @Test
    public void remove_validTaskNumber_success() throws TakoException {
        TaskList tasks = new TaskList();
        Task task = new Task("sleep");
        tasks.add(task);
        assertEquals(task, tasks.remove(0));
        assertEquals(0, tasks.getSize());
    }

    @Test
    public void remove_invalidTaskNumber_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.remove(0);
            fail();
        } catch (TakoException e) {
            assertEquals("The task number to delete does not exist.", e.getMessage());
        }
    }

    @Test
    public void find_validTask_success() {
        TaskList tasks = new TaskList();
        tasks.add(new Task("sleep"));
        tasks.add(new Task("eat"));
        tasks.add(new Task("eat more"));
        TaskList foundTasks = tasks.find("eat");
        assertEquals(2, foundTasks.getSize());
        assertEquals("[ ] eat", foundTasks.get(0).toString());
        assertEquals("[ ] eat more", foundTasks.get(1).toString());
    }

    @Test
    public void find_validTaskWithRemove_success() throws TakoException {
        TaskList tasks = new TaskList();
        tasks.add(new Task("sleep"));
        tasks.add(new Task("eat"));
        tasks.add(new Task("eat more"));
        tasks.remove(2);
        TaskList foundTasks = tasks.find("eat");
        assertEquals(1, foundTasks.getSize());
        assertEquals("[ ] eat", foundTasks.get(0).toString());
    }

    @Test
    public void find_noTask_success() {
        TaskList tasks = new TaskList();
        TaskList foundTasks = tasks.find("eat");
        assertEquals(0, foundTasks.getSize());
    }

    @Test
    public void sort_byDate_success() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("sleep"));
        tasks.add(new Deadline("eat", LocalDateTime.parse("2022-11-11T11:11")));
        tasks.add(new Event("go home", LocalDateTime.parse("2011-11-11T11:11")));
        tasks.add(new Deadline("play", LocalDateTime.parse("2022-11-11T11:10")));
        tasks.add(new Event("drink", LocalDateTime.parse("2011-10-11T11:11")));

        tasks.sort(Sort.DATE, true);
        assertEquals("[E][ ] drink (at: Oct 11 2011 11:11)", tasks.get(0).toString());
        assertEquals("[E][ ] go home (at: Nov 11 2011 11:11)", tasks.get(1).toString());
        assertEquals("[D][ ] play (by: Nov 11 2022 11:10)", tasks.get(2).toString());
        assertEquals("[D][ ] eat (by: Nov 11 2022 11:11)", tasks.get(3).toString());
        assertEquals("[T][ ] sleep", tasks.get(4).toString());

        tasks.sort(Sort.DATE, false);
        assertEquals("[T][ ] sleep", tasks.get(0).toString());
        assertEquals("[D][ ] eat (by: Nov 11 2022 11:11)", tasks.get(1).toString());
        assertEquals("[D][ ] play (by: Nov 11 2022 11:10)", tasks.get(2).toString());
        assertEquals("[E][ ] go home (at: Nov 11 2011 11:11)", tasks.get(3).toString());
        assertEquals("[E][ ] drink (at: Oct 11 2011 11:11)", tasks.get(4).toString());
    }

    @Test
    public void sort_byAlphabet_success() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("sleep"));
        tasks.add(new Deadline("eat", LocalDateTime.parse("2022-11-11T11:11")));
        tasks.add(new Event("go home", LocalDateTime.parse("2011-11-11T11:11")));
        tasks.add(new Deadline("play", LocalDateTime.parse("2022-11-11T11:10")));
        tasks.add(new Event("drink", LocalDateTime.parse("2011-10-11T11:11")));

        tasks.sort(Sort.ALPHABET, true);
        assertEquals("[E][ ] drink (at: Oct 11 2011 11:11)", tasks.get(0).toString());
        assertEquals("[D][ ] eat (by: Nov 11 2022 11:11)", tasks.get(1).toString());
        assertEquals("[E][ ] go home (at: Nov 11 2011 11:11)", tasks.get(2).toString());
        assertEquals("[D][ ] play (by: Nov 11 2022 11:10)", tasks.get(3).toString());
        assertEquals("[T][ ] sleep", tasks.get(4).toString());

        tasks.sort(Sort.ALPHABET, false);
        assertEquals("[T][ ] sleep", tasks.get(0).toString());
        assertEquals("[D][ ] play (by: Nov 11 2022 11:10)", tasks.get(1).toString());
        assertEquals("[E][ ] go home (at: Nov 11 2011 11:11)", tasks.get(2).toString());
        assertEquals("[D][ ] eat (by: Nov 11 2022 11:11)", tasks.get(3).toString());
        assertEquals("[E][ ] drink (at: Oct 11 2011 11:11)", tasks.get(4).toString());
    }
}
