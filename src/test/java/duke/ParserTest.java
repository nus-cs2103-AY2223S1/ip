package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import duke.command.Command;
import duke.command.AddCommand;
import java.time.LocalDate;

public class ParserTest {

    @Test
    public void getTimeTest(){
        String[] input = new String[] {"deadline", "description /by 2000-01-01"};
        LocalDate test = Parser.getTime(input, "/by");
        assertEquals(test, LocalDate.parse("2000-01-01"));

        input = new String[] {"event", "description /at 2022-12-12"};
        test = Parser.getTime(input, "/at");
        assertEquals(test, LocalDate.parse("2022-12-12"));
    }

    @Test
    public void getDescriptionTest(){
        String[] input = new String[] {"todo", "description 1"};
        String description = Parser.getDescription(input, null);
        assertEquals(description, "description 1");

        input = new String[] {"deadline", "description 2 /by 2000-01-01"};
        description = Parser.getDescription(input, "/by");
        assertEquals(description, "description 2");

        input = new String[] {"deadline", "description 3 /at 2022-12-12"};
        description = Parser.getDescription(input, "/at");
        assertEquals(description, "description 3");
    }

}
