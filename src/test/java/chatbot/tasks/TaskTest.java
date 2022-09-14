package chatbot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void save_format_success() {
        Task event = new Event("Dinner", LocalDate.parse("2022-10-10"), new String[] {});
        assertEquals("E | 0 | Dinner | 2022-10-10", event.save());

        Task deadline = new Deadline("PS1", LocalDate.parse("2022-09-10"), new String[] {"important", "schoolwork"});
        deadline.mark();
        assertEquals("D | 1 | PS1 | 2022-09-10 | important#schoolwork", deadline.save());

        Task todo = new Todo("Feed Baby", new String[] {});
        assertEquals("T | 0 | Feed Baby", todo.save());
    }

    @Test
    public void test_mark_unmark() {
        Task event = new Event("Dinner", LocalDate.parse("2022-10-10"), new String[] {});
        event.mark();
        assertEquals("[E][X] Dinner (at: Oct 10 2022)", event.toString());

        Task deadline = new Deadline("PS1", LocalDate.parse("2022-09-10"),
                new String[] {"schoolwork", "time-consuming"});
        deadline.mark();
        assertEquals("[D][X] PS1 (by: Sep 10 2022)", deadline.toString());

        event.unmark();
        assertEquals("[E][ ] Dinner (at: Oct 10 2022)", event.toString());

        deadline.unmark();
        assertEquals("[D][ ] PS1 (by: Sep 10 2022)", deadline.toString());
    }
}
