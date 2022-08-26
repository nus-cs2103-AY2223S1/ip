package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.StorageStub;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.Todo;

public class CommandTest {
        private final StorageStub storageStub = new StorageStub(List.<Task>of());
        private final TaskList taskList = new TaskList();
        private final Ui ui = new Ui();
        private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        private final PrintStream orignalOut = System.out;

        @BeforeEach
        void setUp() {
                System.setOut(new PrintStream(outContent));
                Command.setStorage(storageStub);
                Command.setTaskList(taskList);
                Command.setUi(ui);
        }

        @AfterEach
        void restore() {
                System.setOut(orignalOut);
        }

        @Test
        void execute_addCommand() throws DukeException {
                assertEquals(storageStub.getTasks().size(), 0);

                Task task = new Todo("Test task");
                Command command = new AddCommand(task);
                command.execute();

                assertEquals(storageStub.getTasks().size(), 1);
                assertEquals(taskList.size(), 1);
        }

        @Test
        void execute_listCommand() throws DukeException {
                String divider = "    ____________________________________________________________"
                                + System.lineSeparator();

                Command command = new ListCommand();
                command.execute();

                String firstExpected = divider + divider;
                assertEquals(firstExpected,
                                outContent.toString());

                Task todo = new Todo("Test task");
                Command.setTaskList(new TaskList(List.of(todo)));
                command.execute();

                String secondExpected = firstExpected
                                + divider
                                + "     1. " + todo + System.lineSeparator()
                                + divider;
                assertEquals(secondExpected,
                                outContent.toString());
        }

        @Test
        void execute_findCommand() throws DukeException {
                String divider = "    ____________________________________________________________"
                                + System.lineSeparator();
                Task todo = new Todo("Test task");
                Command.setTaskList(new TaskList(List.of(todo)));

                Command command = new FindCommand("Test");
                command.execute();

                String firstExpected = divider
                                + "     Here is what I found: "
                                + System.lineSeparator()
                                + "     1. " + todo + System.lineSeparator()
                                + divider;
                assertEquals(firstExpected,
                                outContent.toString());

                command = new FindCommand("bad");
                command.execute();
                String secondExpected = firstExpected
                                + divider
                                + "     No task matched your query!" + System.lineSeparator()
                                + divider;
                assertEquals(secondExpected,
                                outContent.toString());

        }
}
