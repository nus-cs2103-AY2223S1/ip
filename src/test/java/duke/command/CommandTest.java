package duke.command;

import java.io.File;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;


public abstract class CommandTest {
    public static final int INITIAL_TASK_COUNT = 3;

    public static final Todo TODO = new Todo("do tutorial");
    public static final Event EVENT_ON_20220606 = new Event("cs1101s studio",
            LocalDate.parse("2022-06-06"));
    public static final Deadline DEADLINE_ON_20220505 = new Deadline("stuff",
            LocalDate.parse("2022-05-05"));
    private Storage storage;
    private TaskList tasks;

    public String testCommandExecution(Command command) throws DukeException {
        return command.execute(tasks, storage);
    }

    public TaskList getTasks() {
        return tasks;
    }

    @BeforeEach
    public void setUp() {
        storage = new Storage("data/test.txt");
        tasks = new TaskList();
        tasks.addTask(TODO);
        tasks.addTask(DEADLINE_ON_20220505);
        tasks.addTask(EVENT_ON_20220606);
    }

    @AfterAll
    public static void deleteTestFile() {
        new File("data/test.txt").delete();
    }
}
