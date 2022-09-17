package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.command.AliasCommand;
import duke.command.ByCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandsEnum;
import duke.command.DeadlineCommand;
import duke.command.DeleteAliasCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ResetAliasCommand;
import duke.command.SwapFaceCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.exceptions.CorruptedLineException;

public class DataParserTest {
    @Test
    public void parseDataFromLine_invalid1() {
        try {
            DataParser.parseDataFromLine("txt");
            fail();
        } catch (CorruptedLineException e) {
            // pass test
        }
    }

    @Test
    public void parseDataFromLine_invalid2() {
        try {
            DataParser.parseDataFromLine("Tz <<<< nothing <<<< temperol");
            fail();
        } catch (CorruptedLineException e) {
            // pass test
        }
    }

    @Test
    public void dataToCommandTest() {
        ParsedData dummy;
        // new ParsedData(bye, "b", "c");
        for (CommandsEnum c : CommandsEnum.values()) {
            dummy = new ParsedData(c.toString(), "b", "c");
            Command command = DataParser.dataToCommand(dummy);
            assertEquals(command instanceof Command, true);

            if (command instanceof ByeCommand && c == CommandsEnum.BYE) {
                continue;
            }
            if (command instanceof ListCommand && c == CommandsEnum.LIST) {
                continue;
            }
            if (command instanceof MarkCommand && c == CommandsEnum.MARK) {
                continue;
            }
            if (command instanceof UnmarkCommand && c == CommandsEnum.UNMARK) {
                continue;
            }
            if (command instanceof DeleteCommand && c == CommandsEnum.DELETE) {
                continue;
            }
            if (command instanceof TodoCommand && c == CommandsEnum.TODO) {
                continue;
            }
            if (command instanceof DeadlineCommand && c == CommandsEnum.DEADLINE) {
                continue;
            }
            if (command instanceof EventCommand && c == CommandsEnum.EVENT) {
                continue;
            }
            if (command instanceof ByCommand && c == CommandsEnum.BY) {
                continue;
            }
            if (command instanceof FindCommand && c == CommandsEnum.FIND) {
                continue;
            }
            if (command instanceof AliasCommand && c == CommandsEnum.ADDCOMMAND) {
                continue;
            }
            if (command instanceof DeleteAliasCommand && c == CommandsEnum.DELETECOMMAND) {
                continue;
            }
            if (command instanceof SwapFaceCommand && c == CommandsEnum.SWAP) {
                continue;
            }
            if (command instanceof ResetAliasCommand && c == CommandsEnum.RESETALIAS) {
                continue;
            }
            if (command instanceof InvalidCommand && c == CommandsEnum.INVALID) {
                continue;
            }
            fail();
        }
    }

    @Test
    public void parseTest1() {
        String sample = "todo asdfl /by asd";
        ParsedData d = DataParser.parse(sample);

        assertEquals(d.command, "todo");
        assertEquals(d.description, "asdfl /by asd");
        assertEquals(d.additionalInfo, "");
    }

    @Test
    public void parseTest2() {
        String sample = "deadline asdfl /by asd";
        ParsedData d = DataParser.parse(sample);

        assertEquals(d.command, "deadline");
        assertEquals(d.description, "asdfl");
        assertEquals(d.additionalInfo, "asd");
    }

    @Test
    public void parseTest3() {
        String sample = "deadline asdfl /at asd";
        ParsedData d = DataParser.parse(sample);

        assertEquals(d.command, "deadline");
        assertEquals(d.description, "asdfl /at asd");
        assertEquals(d.additionalInfo, "");
    }

    @Test
    public void parseTest4() {
        String sample = "event asdfl /at asd";
        ParsedData d = DataParser.parse(sample);

        assertEquals(d.command, "event");
        assertEquals(d.description, "asdfl");
        assertEquals(d.additionalInfo, "asd");
    }

    @Test
    public void parseTest5() {
        String sample = "event asdfl /by asd";
        ParsedData d = DataParser.parse(sample);

        assertEquals(d.command, "event");
        assertEquals(d.description, "asdfl /by asd");
        assertEquals(d.additionalInfo, "");
    }

    @Test
    public void parseTest6() {
        String sample = "event asdfl";
        ParsedData d = DataParser.parse(sample);

        assertEquals(d.command, "event");
        assertEquals(d.description, "asdfl");
        assertEquals(d.additionalInfo, "");
    }

    @Test
    public void parseTest7() {
        String sample = "list";
        ParsedData d = DataParser.parse(sample);

        assertEquals(d.command, "list");
        assertEquals(d.description, "");
        assertEquals(d.additionalInfo, "");
    }

    @Test
    public void strToDateTimeTest1() {
        String test;
        test = "5sdf";
        assertEquals(DataParser.strToDateTime(test).isEmpty(), true);
    }

    @Test
    public void strToDateTimeTest2() {
        String test;
        test = "Oct 4 2021 21:15";
        assertEquals(DataParser.strToDateTime(test).get(), LocalDateTime.of(2021, 10, 4, 21, 15));
    }

    @Test
    public void strToDateTimeTest3() {
        String test;
        test = "04/10/2021 21:15";
        assertEquals(DataParser.strToDateTime(test).get(), LocalDateTime.of(2021, 10, 4, 21, 15));
    }

    @Test
    public void strToDateTimeTest4() {
        String test;
        test = "04-10-2021 21:15";
        assertEquals(DataParser.strToDateTime(test).get(), LocalDateTime.of(2021, 10, 4, 21, 15));
    }

    @Test
    public void strToDateTimeTest5() {
        String test;
        test = "Oct 4 2021";
        assertEquals(DataParser.strToDateTime(test).get(), LocalDateTime.of(2021, 10, 4, 0, 0));
    }

    @Test
    public void strToDateTimeTest6() {
        String test;
        test = "04/10/2021";
        assertEquals(DataParser.strToDateTime(test).get(), LocalDateTime.of(2021, 10, 4, 0, 0));
    }

    @Test
    public void strToDateTimeTest7() {
        String test;
        test = "04-10-2021";
        assertEquals(DataParser.strToDateTime(test).get(), LocalDateTime.of(2021, 10, 4, 0, 0));
    }

}
