package candice.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimedTaskTest {
    @Test
    public void testDeadlineStatusString() {
        LocalDate localDate = LocalDate.parse("2022-01-01");
        LocalTime localTime = LocalTime.parse("23:59");
        TimedTask.Deadline deadlineTask = new TimedTask.Deadline("read a book", localDate, localTime);

        assertEquals("[D][ ] read a book (by: 1 Jan 2022 23:59)", deadlineTask.getStatus());

        deadlineTask.setFinished();
        assertEquals("[D][X] read a book (by: 1 Jan 2022 23:59)", deadlineTask.getStatus());

        TimedTask.Deadline deadlineTaskWithoutTime = new TimedTask.Deadline("read a book", localDate, null);

        assertEquals("[D][ ] read a book (by: 1 Jan 2022)", deadlineTaskWithoutTime.getStatus());

        deadlineTaskWithoutTime.setFinished();
        assertEquals("[D][X] read a book (by: 1 Jan 2022)", deadlineTaskWithoutTime.getStatus());
    }

    @Test
    public void testDeadlineStorageDescription() {
        LocalDate localDate = LocalDate.parse("2022-01-01");
        LocalTime localTime = LocalTime.parse("23:59");
        TimedTask.Deadline deadlineTask = new TimedTask.Deadline("read a book", localDate, localTime);

        assertEquals("[D], unfinished, read a book, 2022-01-01, 23:59\n", deadlineTask.getStorageDescription());

        deadlineTask.setFinished();
        assertEquals("[D], finished, read a book, 2022-01-01, 23:59\n", deadlineTask.getStorageDescription());

        TimedTask.Deadline deadlineTaskWithoutTime = new TimedTask.Deadline("read a book", localDate, null);

        assertEquals("[D], unfinished, read a book, 2022-01-01, no time given\n",
                deadlineTaskWithoutTime.getStorageDescription());

        deadlineTaskWithoutTime.setFinished();
        assertEquals("[D], finished, read a book, 2022-01-01, no time given\n",
                deadlineTaskWithoutTime.getStorageDescription());
    }

    @Test
    public void testEventStatusString() {
        LocalDate localDate = LocalDate.parse("2022-01-01");
        LocalTime startTime = LocalTime.parse("16:00");
        LocalTime endTime = LocalTime.parse("20:00");
        TimedTask.Event eventTask = new TimedTask.Event("read a book", localDate, startTime, endTime);

        assertEquals("[E][ ] read a book (at: 1 Jan 2022 16:00-20:00)", eventTask.getStatus());

        eventTask.setFinished();
        assertEquals("[E][X] read a book (at: 1 Jan 2022 16:00-20:00)", eventTask.getStatus());

        TimedTask.Event eventTaskWithoutTime = new TimedTask.Event("read a book",
                localDate, null, null);

        assertEquals("[E][ ] read a book (at: 1 Jan 2022)", eventTaskWithoutTime.getStatus());

        eventTaskWithoutTime.setFinished();
        assertEquals("[E][X] read a book (at: 1 Jan 2022)", eventTaskWithoutTime.getStatus());
    }

    @Test
    public void testEventStorageDescription() {
        LocalDate localDate = LocalDate.parse("2022-01-01");
        LocalTime startTime = LocalTime.parse("16:00");
        LocalTime endTime = LocalTime.parse("20:00");
        TimedTask.Event eventTask = new TimedTask.Event("read a book", localDate, startTime, endTime);

        assertEquals("[E], unfinished, read a book, 2022-01-01, 16:00, 20:00\n", eventTask.getStorageDescription());

        eventTask.setFinished();
        assertEquals("[E], finished, read a book, 2022-01-01, 16:00, 20:00\n", eventTask.getStorageDescription());

        TimedTask.Event eventTaskWithoutTime = new TimedTask.Event("read a book",
                localDate, null, null);

        assertEquals("[E], unfinished, read a book, 2022-01-01, no time given\n",
                eventTaskWithoutTime.getStorageDescription());

        eventTaskWithoutTime.setFinished();
        assertEquals("[E], finished, read a book, 2022-01-01, no time given\n",
                eventTaskWithoutTime.getStorageDescription());
    }
}
