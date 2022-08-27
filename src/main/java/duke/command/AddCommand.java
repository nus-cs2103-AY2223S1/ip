package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.ui.Ui;
import duke.util.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AddCommand extends Command {
    private final ArrayList<String> words;
    private final String firstWord;

    public AddCommand(ArrayList<String> words, String firstWord) {
        this.words = words;
        this.firstWord = firstWord;
    }

    public void execute(Storage storage, TaskList tasklist, Ui ui) throws DukeException {
        String input = String.join(" ", words);
        switch (firstWord) {
        case "todo":
            if (words.size() != 0) {
                Todo todo = new Todo(input);
                tasklist.addTask(todo);
                System.out.println(ui.SPACER + "\n"
                        + "I've added this task for you! :>\n"
                        + todo + "\n"
                        + "You have " + tasklist.tasks.size()
                        + (tasklist.tasks.size() == 1 ? " task! :D\n" : " tasks! :D\n")
                        + ui.SPACER);
            } else {
                throw new DukeException(ui.SPACER + "\n"
                        + "Please enter a task following 'todo' and I'll add it into your list. T^T\n"
                        + ui.SPACER);
            }
            break;
        case "deadline":
            // After adding new exceptions, throw them here
            String remainingDdlWords = String.join(" ", words.subList(0, words.indexOf("/by")));
            String ddl = String.join(" ", words.subList(words.indexOf("/by") + 1, words.size()));
            if (ddl.matches("\\d{4}-\\d{2}-\\d{2}")) {
                LocalDate ddlDate = LocalDate.parse(ddl);
                String newDdl = ddlDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
                Deadline deadline = new Deadline(remainingDdlWords, newDdl);
                tasklist.addTask(deadline);
                System.out.println(ui.SPACER + "\n"
                        + "I've added this task for you! :>\n"
                        + deadline + "\n"
                        + "You have " + tasklist.tasks.size()
                        + (tasklist.tasks.size() == 1 ? " task! :D\n" : " tasks! :D\n")
                        + ui.SPACER);
            } else {
                Deadline deadline = new Deadline(remainingDdlWords, ddl);
                tasklist.addTask(deadline);
                System.out.println(ui.SPACER + "\n"
                        + "I've added this task for you! :>\n"
                        + deadline + "\n"
                        + "You have " + tasklist.tasks.size()
                        + (tasklist.tasks.size() == 1 ? " task! :D\n" : " tasks! :D\n")
                        + ui.SPACER);
            }
            break;
        case "event":
            // After adding new exceptions, throw them here
            String remainingEventWords = String.join(" ", words.subList(0, words.indexOf("/at")));
            String evt = String.join(" ", words.subList(words.indexOf("/at") + 1, words.size()));
            if (evt.matches("\\d{4}-\\d{2}-\\d{2}")) {
                LocalDate evtDate = LocalDate.parse(evt);
                String newEvt = evtDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
                Event event = new Event(remainingEventWords, newEvt);
                tasklist.addTask(event);
                System.out.println(ui.SPACER + "\n"
                        + "I've added this task for you! :>\n"
                        + event + "\n"
                        + "You have " + tasklist.tasks.size()
                        + (tasklist.tasks.size() == 1 ? " task! :D\n" : " tasks! :D\n")
                        + ui.SPACER);
            } else {
                Event event = new Event(remainingEventWords, evt);
                tasklist.addTask(event);
                System.out.println(ui.SPACER + "\n"
                        + "I've added this task for you! :>\n"
                        + event + "\n"
                        + "You have " + tasklist.tasks.size()
                        + (tasklist.tasks.size() == 1 ? " task! :D\n" : " tasks! :D\n")
                        + ui.SPACER);
            }
            break;
        }
    }
}