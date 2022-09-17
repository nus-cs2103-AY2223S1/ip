package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import duke.exceptions.OutOfBoundException;
import duke.task.Task;

public class TaskListTest {
    class TaskStud extends Task {

        TaskStud() {
            super("No time", Optional.empty());
        }

        TaskStud(long time) {
            super(Long.toString(time), Optional.of(LocalDateTime.ofEpochSecond(time, 0, ZoneOffset.UTC)));
        }

        LocalDateTime getDatetime() {
            return dateTime.orElse(null);
        }

        String getDesc() {
            return description;
        }



        @Override
        public ParsedData convertToParseData() {
            return new ParsedData("Dummy", description);
        }

    }

    @Test
    public void addEntryTest() {
        TaskList tl = new TaskList();
        assertEquals(tl.getSize(), 0);
        tl.addEntry(new TaskStud());
        tl.addEntry(new TaskStud(1));
        tl.addEntry(new TaskStud(2));
        tl.addEntry(new TaskStud(3));
        assertEquals(tl.getSize(), 4);
        tl.addEntry(new TaskStud(3));
        assertEquals(tl.getSize(), 5);
    }


    @Test
    public void deleteEntryTest() {
        TaskList tl = new TaskList();
        tl.addEntry(new TaskStud());
        tl.addEntry(new TaskStud(1));
        tl.addEntry(new TaskStud(2));
        tl.addEntry(new TaskStud(3));
        assertEquals(tl.getSize(), 4);
        tl.addEntry(new TaskStud(3));
        assertEquals(tl.getSize(), 5);
        try {
            Task r = tl.deleteEntry(0);
            assertEquals(r.toString(), "[ ] No time");
            r = tl.deleteEntry(0);
            assertEquals(r.toString(), "[ ] 1");
            r = tl.deleteEntry(0);
            assertEquals(r.toString(), "[ ] 2");
            r = tl.deleteEntry(0);
            assertEquals(r.toString(), "[ ] 3");

        } catch (OutOfBoundException e) {
            fail();
        }
        try {
            tl.deleteEntry(1);
            fail();
        } catch (OutOfBoundException e) {
            // pass test
        }
    }

    @Test
    public void getTaskBeforeTest() {
        List<Task> list = new ArrayList<>();
        for (long i = 0; i < 50000; i += 2000) {
            list.add(new TaskStud(i));
        }

        Collections.shuffle(list);

        TaskList tl = new TaskList(list);
        assertEquals(list.size(), tl.getSize());
        LocalDateTime cmp = LocalDateTime.ofEpochSecond(50000 / 2, 0, ZoneOffset.UTC);
        List<Task> res = tl.getTaskBefore(cmp);
        for (int i = 0; i < res.size(); i++) {
            assertEquals(true, res.get(i).containsDatetime());
            assertEquals(true, ((TaskStud) res.get(i)).getDatetime().compareTo(cmp) <= 0);
        }
    }

    @Test
    public void getParsedDataTest() {
        List<Task> list = new ArrayList<>();
        for (long i = 0; i < 50000; i += 2000) {
            list.add(new TaskStud(i));
        }

        Collections.shuffle(list);

        TaskList tl = new TaskList(list);
        assertEquals(list.size(), tl.getSize());
        var res = tl.getParsedData();
        for (int i = 0; i < res.length; i++) {
            assertEquals("Dummy", res[i].command);
            assertEquals(((TaskStud) list.get(i)).getDesc(), res[i].description);
            assertEquals("", res[i].additionalInfo);
        }
    }
}
