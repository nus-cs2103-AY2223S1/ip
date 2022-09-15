package anya;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ParserTest {

    @Test
    public void parse() throws AnyaException {
        Parser dummyParser = new Parser();
        List<Task> dummyList1 = new ArrayList<Task>();
        TaskList dummyTaskList = new TaskList(dummyList1);
        Ui dummyUi = new Ui();
        Command actualCommand = dummyParser.parse("deadline finish assignment 1 /by 2022-09-15");
        String actualOutput = actualCommand.execute(dummyTaskList, dummyUi);
        Task dummyTask = new Deadline("finish assignment 1", "2022-09-15");
        String expectedOutput = dummyUi.added(dummyTask.toString(), 1);
        assertEquals(expectedOutput, actualOutput);
    }

}
