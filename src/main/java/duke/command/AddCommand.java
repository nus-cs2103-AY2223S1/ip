package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.ui.Ui;
import duke.util.TaskList;

import java.util.ArrayList;
import java.util.Arrays;

public class AddCommand extends Command {
    private final String input;

    public AddCommand(String input) {
        this.input = input;
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) throws DukeException {
        ArrayList<String> words = new ArrayList<>(Arrays.asList(input.split(" ")));
        switch (input) {
        case "todo":
            if (words.size() != 0) {
                Todo todo = new Todo(input);
                tasks.addTask(todo);
                System.out.println(ui.SPACER + "\n"
                        + "I've added this task for you! :>\n"
                        + todo + "\n"
                        + "You have " + tasks.tasklist.size()
                        + (tasks.tasklist.size() == 1 ? " task! :D\n" : " tasks! :D\n")
                        + ui.SPACER);
            } else {
                throw new DukeException(ui.SPACER + "\n"
                        + "Please enter a task following 'todo' and I'll add it into your list. T^T\n"
                        + ui.SPACER);
            }
            break;
        case "deadline":
            // After adding new exceptions, throw them here
            String remainingDdlWords = String.join(" ",words.subList(0, words.indexOf("/by")));
            String ddl = String.join(" ", words.subList(words.indexOf("/by") + 1, words.size()));
            Deadline deadline = new Deadline(remainingDdlWords, ddl);
            tasks.addTask(deadline);
            System.out.println(ui.SPACER + "\n"
                    + "I've added this task for you! :>\n"
                    + deadline + "\n"
                    + "You have " + tasks.tasklist.size()
                    + (tasks.tasklist.size() == 1 ? " task! :D\n" : " tasks! :D\n")
                    + ui.SPACER);
            break;
        case "event":
            // After adding new exceptions, throw them here
            String remainingEventWords = String.join(" ",words.subList(0, words.indexOf("/at")));
            String evt = String.join(" ", words.subList(words.indexOf("/at") + 1, words.size()));
            Event event = new Event(remainingEventWords, evt);
            tasks.addTask(event);
            System.out.println(ui.SPACER + "\n"
                    + "I've added this task for you! :>\n"
                    + event + "\n"
                    + "You have " + tasks.tasklist.size()
                    + (tasks.tasklist.size() == 1 ? " task! :D\n" : " tasks! :D\n")
                    + ui.SPACER);
            break;
        default:
            throw new DukeException("Sorry, I don't understand. T^T\n"
                    + "Please start your command with list, mark, unmark, todo, deadline, event or bye. :')\n");
        }
    }
}