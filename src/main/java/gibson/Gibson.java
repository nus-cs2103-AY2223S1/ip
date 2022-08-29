package gibson;

import java.util.regex.Pattern;

import gibson.task.Deadline;
import gibson.task.Event;
import gibson.task.Parser;
import gibson.task.Task;
import gibson.task.TaskList;

/**
 *  The Gibson program. It works as a Personal Assistant Chatbot
 *  that helps a person to keep track of various things.
 */
public class Gibson {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;


    /**
     * Constructs a new Gibson object that starts the whole program.
     * It saves a text file with the given name to the given path.
     */
    public Gibson() {
        ui = new Ui();
        storage = new Storage("data", "gibson.txt");
        taskList = new TaskList(storage.load());
    }

    /**
     * Start running the Gibson program from an instance of Gibson.
     */
    public String processInput(String input) {
        // BYE
        if (input.equals("bye")) {
            return "Bye! Hope to see you soon.";
        // LIST
        } else if (input.equals("list")) {
            return "Here are the task(s) in your list:\n" + taskList.toString();
        // MARK
        } else if (Pattern.matches("mark [0-9]+", input)) {
            int number = Parser.getTrailingInt(input);
            int index = number - 1;
            try {
                Task t = taskList.mark(index);
                storage.save(taskList.toString());
                return "Nice! I've marked this task as done:\n" + t.toString();
            } catch (IndexOutOfBoundsException e) {
                return "There is not task numbered as " + number + ".";
            }
        // UNMARK
        } else if (Pattern.matches("unmark [0-9]+", input)) {
            int number = Parser.getTrailingInt(input);
            int index = number - 1;
            try {
                Task t = taskList.unmark(index);
                storage.save(taskList.toString());
                return "OK, I've marked this task as not done yet:\n" + t.toString();
            } catch (IndexOutOfBoundsException e) {
                return "There is not task numbered as " + number + ".";
            }
        // TODOS
        } else if (Pattern.matches("(todo .+)|(todo( )?)", input)) {
            String taskString = Parser.substringAfterToken(input, "todo");
            try {
                Task task = new Task(taskString);
                taskList.add(task);
                storage.save(taskList.toString());
                return "Got it. I've added this task:\n"
                        + task
                        + "\nNow you have " + taskList.size() + " task(s) in the list.";
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            }
        // DEADLINES
        } else if (Pattern.matches("(deadline .+ /by .+)|(deadline .+ /by( )?)", input)) {
            String taskString = Parser.substringAfterToken(input, "deadline");
            String[] stringArray = Parser.substringBeforeAfterToken(taskString, "/by");
            try {
                Deadline deadline = new Deadline(stringArray[0], stringArray[1]);
                taskList.add(deadline);
                storage.save(taskList.toString());
                return "Got it. I've added this task:\n"
                        + deadline
                        + "\nNow you have " + taskList.size() + " task(s) in the list.";
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            }
        // EVENTS
        } else if (Pattern.matches("(event .+ /at .+)|(event .+ /at( )?)", input)) {
            String taskString = Parser.substringAfterToken(input, "event");
            String[] stringArray = Parser.substringBeforeAfterToken(taskString, "/at");
            try {
                Event event = new Event(stringArray[0], stringArray[1]);
                taskList.add(event);
                storage.save(taskList.toString());
                return "Got it. I've added this task:\n"
                        + event
                        + "\nNow you have " + taskList.size() + " task(s) in the list.";
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            }
        // REMOVE
        } else if (Pattern.matches("delete [0-9]+", input)) {
            int number = Parser.getTrailingInt(input);
            int index = number - 1;
            try {
                Task t = taskList.remove(index);
                storage.save(taskList.toString());
                return "Noted. I've removed this task:\n"
                        + t.toString()
                        + "\nNow you have " + taskList.size() + " task(s) in the list.";
            } catch (IndexOutOfBoundsException e) {
                return "There is not task numbered as " + number + ".";
            }
        // FIND
        } else if (Pattern.matches("(find .+)|(find( )?)", input)) {
            String searchQuery = Parser.substringAfterToken(input, "find");
            TaskList result = taskList.find(searchQuery);
            if (!result.isEmpty()) {
                return "Here are your results:\n" + result.toString();
            } else {
                return "Unable to find task with \"" + searchQuery + "\".";
            }
        // NOT RECOGNIZED
        } else {
            return "I'm sorry. I do not know what that means.";
        }
    }
}
