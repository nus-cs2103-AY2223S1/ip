import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.AddTaskCommand;
import duke.command.BarGraphStatsCommand;
import duke.command.Command;
import duke.command.DeleteTaskCommand;
import duke.command.EditTaskCommand;
import duke.command.ExitCommand;
import duke.command.FindTaskCommand;
import duke.command.ListTasksCommand;
import duke.command.MarkTaskDoneCommand;
import duke.command.MarkTaskNotDoneCommand;
import duke.command.Parser;
import duke.command.PieChartStatsCommand;
import duke.command.ViewScheduleCommand;
import duke.exception.DukeException;

public class ParserTest {
    @Test
    public void parseCommand_listCommand_success() throws DukeException {
        Command listCommand = Parser.parse("list");
        assertEquals(listCommand.getClass(), ListTasksCommand.class);
    }

    @Test
    public void parseCommand_markCommand_success() throws DukeException {
        Command markCommand = Parser.parse("mark 1");
        assertEquals(markCommand.getClass(), MarkTaskDoneCommand.class);
    }

    @Test
    public void parseCommand_unmarkCommand_success() throws DukeException {
        Command unmarkCommand = Parser.parse("unmark 1");
        assertEquals(unmarkCommand.getClass(), MarkTaskNotDoneCommand.class);
    }

    @Test
    public void parseCommand_deleteCommand_success() throws DukeException {
        Command deleteCommand = Parser.parse("delete 1");
        assertEquals(deleteCommand.getClass(), DeleteTaskCommand.class);
    }

    @Test
    public void parseCommand_editCommand_success() throws DukeException {
        Command editCommand = Parser.parse("edit 1 test dummy input");
        assertEquals(editCommand.getClass(), EditTaskCommand.class);
    }

    @Test
    public void parseCommand_findCommand_success() throws DukeException {
        Command findCommand = Parser.parse("find abc");
        assertEquals(findCommand.getClass(), FindTaskCommand.class);
    }

    @Test
    public void parseCommand_scheduleCommand_success() throws DukeException {
        Command scheduleCommand = Parser.parse("schEDUle");
        assertEquals(scheduleCommand.getClass(), ViewScheduleCommand.class);
    }

    @Test
    public void parseCommand_statsCommand_success() throws DukeException {
        Command statsPieChartCommand = Parser.parse("STATS");
        assertEquals(statsPieChartCommand.getClass(), PieChartStatsCommand.class);
    }

    @Test
    public void parseCommand_pieChartCommand_success() throws DukeException {
        Command statsPieChartCommand = Parser.parse("stATS -p");
        assertEquals(statsPieChartCommand.getClass(), PieChartStatsCommand.class);
    }

    @Test
    public void parseCommand_barChartCommand_success() throws DukeException {
        Command statsBarChartCommand = Parser.parse("stATS -b");
        assertEquals(statsBarChartCommand.getClass(), BarGraphStatsCommand.class);
    }

    @Test
    public void parseCommand_toDoCommand_success() throws DukeException {
        Command toDoCommand = Parser.parse("todo");
        assertEquals(toDoCommand.getClass(), AddTaskCommand.class);
    }

    @Test
    public void parseCommand_deadlineCommand_success() throws DukeException {
        Command deadlineCommand = Parser.parse("deadline");
        assertEquals(deadlineCommand.getClass(), AddTaskCommand.class);
    }

    @Test
    public void parseCommand_eventCommand_success() throws DukeException {
        Command eventCommand = Parser.parse("event");
        assertEquals(eventCommand.getClass(), AddTaskCommand.class);
    }

    @Test
    public void parseCommand_exitCommand_success() throws DukeException {
        Command exitCommand = Parser.parse("bye");
        assertEquals(exitCommand.getClass(), ExitCommand.class);
    }
}
