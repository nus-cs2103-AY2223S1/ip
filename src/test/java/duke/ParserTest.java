package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void executeCommandTest() {
        TaskList tasks = new TaskList(new ArrayList<>(List.of("[T][ ] wake up",
                "[D][ ] homework (by: Aug 24 2022)",
                "[E][ ] lab (at: Jul 30 2023)")));
        String desiredOutput = "[T][X] wake up\n" +
                "[E][ ] lab (at: Jul 30 2023)\n" +
                "[D][ ] leetcode (by: Mar 11 2022)\n";
        Parser p = new Parser(tasks);
        try {
            p.executeCommand("delete", "delete 2");
            p.executeCommand("deadline", "deadline leetcode /by 2022-03-11");
            TaskList result = p.executeCommand("mark", "mark 1");
            assertEquals(desiredOutput, result.getList());

        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
