package duke;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import duke.Exceptions.descriptionException;
import duke.Exceptions.NoSuchCommandException;
import duke.Parser;
import duke.Task.Task;
public class Duke {
    private TaskList taskList;
    private Parser parser;
    private Ui ui;
    private Storage storage;
    public enum Commands {
        LIST,
        BYE,
        TODO,
        MARK,
        UNMARK,
        EVENT,
        DEADLINE,
        DELETE
    }
    public Duke(Parser parser, Ui ui, TaskList taskList, Storage storage){
        this.parser = parser;
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }
    public void run() {
        Scanner scanner = new Scanner((System.in));
        ui.showGreeting();

        loop: while (true) {

            String input = scanner.nextLine();
            parser.executeInput(ui, input, storage, taskList);
        }
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        Parser parser = new Parser();
        TaskList taskList = new TaskList(new ArrayList<>());
        Storage storage = new Storage();
        Duke duke = new Duke(parser, ui, taskList, storage);

        duke.run();
        
    }
}
