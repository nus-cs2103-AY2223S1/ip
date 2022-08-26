package duke.main;

import java.io.IOException;
import java.time.LocalDateTime;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidTimeFormatException;
import duke.exceptions.InvalidValueException;
import duke.exceptions.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.util.DukeIo;
import duke.util.ParsedData;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;

public class Duke {
    private static final String LOGO = "Welcome to\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "      Chatbot!\n";

    private static final String INTRO = "Hello! I'm Duke\n"
            + "What can I do for you?";

    
    private DukeIo userIO;
    private TaskList tasks;
    private Storage dukeData;

    Duke(TaskList tasks, Storage dukeData) {
        userIO = new DukeIo();
        this.dukeData = dukeData;
        this.tasks = tasks;
    }

    boolean handleInput() {
        String txt = userIO.readLine();
        Command c = Parser.parseCommand(txt);
        try {
            c.execute(tasks, userIO, dukeData);
        } catch (DukeException e) {
            userIO.printError(e);
        } catch (IOException e) {
            userIO.printError(e);
            return true;
        }

        return !c.isExit();
    }

    static Duke createApplication(String filepath) {
        DukeIo userIO = new DukeIo();
        userIO.printTask(LOGO, 2);
        userIO.printTask(INTRO, 3);
        Storage dukeData;
        TaskList tasks;
        try {
            dukeData = Storage.createStorage(filepath);
            tasks = new TaskList(dukeData.readFile());
        } catch (IOException e) {
            userIO.printError(e);
            userIO.printTask("Fatal Error! The system will exit abnormally!");
            return null;
        }

        return new Duke(tasks, dukeData);
    }

    static Duke createApplication() {
        DukeIo userIO = new DukeIo();
        userIO.printTask(LOGO, 2);
        userIO.printTask(INTRO, 3);
        Storage dukeData;
        TaskList tasks;
        try {
            dukeData = Storage.createStorage();
            tasks = new TaskList(dukeData.readFile());
        } catch (IOException e) {
            userIO.printError(e);
            userIO.printTask("Fatal Error! The system will exit abnormally!");
            return null;
        }

        return new Duke(tasks, dukeData);
    }

    public static void main(String[] args) {
        Duke duke = createApplication();
        if (duke == null) {
            return;
        }
        while (duke.handleInput()) {
        }
    }
}
