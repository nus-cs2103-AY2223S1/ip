package duke;

import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * This class handles the UI
 */
public class Ui {

    /**
     * To greet the user with logo and welcoming messages
     */
    public String greet() {
        String logo = "\n"
                + "   ██▓    ▄▄▄       ███▄ ▄███▓▓█████▄  ▄▄▄\n"
                + "  ▓██▒   ▒████▄    ▓██▒▀█▀ ██▒▒██▀ ██▌▒████▄\n"
                + "  ▒██░   ▒██  ▀█▄  ▓██    ▓██░░██   █▌▒██  ▀█▄\n"
                + "  ▒██░   ░██▄▄▄▄██ ▒██    ▒██ ░▓█▄   ▌░██▄▄▄▄██\n"
                + "  ░██████▒▓█   ▓██▒▒██▒   ░██▒░▒████▓  ▓█   ▓██▒\n"
                + "  ░ ▒░▓  ░▒▒   ▓▒█░░ ▒░   ░  ░ ▒▒▓  ▒  ▒▒   ▓▒█░\n"
                + "  ░ ░ ▒  ░ ▒   ▒▒ ░░  ░      ░ ░ ▒  ▒   ▒   ▒▒ ░\n"
                + "    ░ ░    ░   ▒   ░      ░    ░ ░  ░   ░   ▒\n"
                + "      ░  ░     ░  ░       ░      ░          ░  ░\n"
                + "                               ░\n";
        return logo + "Hi, I am LaMDA.\nHow may I assist you today?\n";
    }

    /**
     * To display a splitting line
     */
    public String showLine() {
        return ("\t____________________________________________\n");
    }

    /**
     * To show error if the file is not found
     */

    public String showLoadingError() {
        return ("Error locating \"tasks.dat\".\nA new file will be created.\n");
    }

    /**
     * To show general errors
     * @param s the error message
     */
    public String showError(String s) {
        return s + "\n";
    }

    /**
     * To display exit message
     */
    public String exitTask() {
        return ("\t It's a great time talking with you.\n\t See you next time!\n");
    }

    /**
     * To display the list of current tasks
     * @param tasks the current {@code TaskList}
     * @return
     */
    public String listTask(TaskList tasks) {
        return ("\t Here are the tasks in your list:\n" + tasks.listTasks());
    }

    /**
     * To display a message after a {@code Todo} has been successfully added
     * @param tasks the current {@code TaskList}
     * @param todo the added {@code Todo}
     */
    public String todoTask(TaskList tasks, Todo todo) {
        return ("\t Got it. I've added this task:\n")
                + ("\t   " + todo.toString() + "\n")
                + ("\t Now you have " + tasks.size() + " tasks in the list.\n");
    }

    /**
     * To display a message after a {@code Deadline} has been successfully added
     * @param tasks the current {@code TaskList}
     * @param deadline the added {@code Deadline}
     */
    public String deadlineTask(TaskList tasks, Deadline deadline) {
        return ("\t Got it. I've added this task:\n")
                + ("\t   " + deadline.toString() + "\n")
                + ("\t Now you have " + tasks.size() + " tasks in the list.\n");
    }

    /**
     * To display a message after a {@code Event} has been successfully added
     * @param tasks the current {@code TaskList}
     * @param event the added {@code Event}
     */
    public String eventTask(TaskList tasks, Event event) {
        return ("\t Got it. I've added this task:\n")
                + ("\t   " + event.toString() + "\n")
                + ("\t Now you have " + tasks.size() + " tasks in the list.\n");
    }

    /**
     * To display a message after a {@code Task} has been successfully marked
     * @param task the marked {@code Task}
     */
    public String markTask(Task task) {
        return ("\t I've marked this task as done:\n")
                + ("\t   " + task.toString() + "\n");
    }

    /**
     * To display a message after a {@code Task} has been successfully unmarked
     * @param task the unmarked {@code Task}
     */
    public String unmarkTask(Task task) {
        return ("\t I've marked this task as not done yet:\n")
                + ("\t   " + task.toString() + "\n");
    }

    /**
     * To display a message after a {@code Task} has been successfully deleted
     * @param task the deleted {@code Task}
     */
    public String deleteTask(TaskList tasks, Task task) {
        return ("\t Noted. I've removed this task:\n")
                + ("\t   " + task.toString() + "\n")
                + ("\t Now you have " + tasks.size() + " tasks in the list.\n");
    }

    /**
     * To display all the tasks that match the search
     * @param tasks the current {@code TaskList}
     * @param description the user input to find marching {@code Task}
     */
    public String findTask(TaskList tasks, String description) {
        ArrayList<Task> temp = tasks.find(description);
        String list = "";
        for (int i = 0; i < temp.size(); i++) {
            list += ("\t " + (i + 1) + "." + temp.get(i).toString() + "\n");
        }
        return ("\t Here are the matching tasks in your list:\n")
                + list;
    }
}
