package duke;

import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import javafx.application.Platform;

/**
 * This class handles the UI
 */
public class Ui {

    /**
     * The logo
     * @return the logo
     */
    public static String logo() {
        String logo = ""
                + " ██▓    ▄▄▄       ███▄ ▄███▓▓█████▄  ▄▄▄      \n"
                + "▓██▒   ▒████▄    ▓██▒▀█▀ ██▒▒██▀ ██▌▒████▄    \n"
                + "▒██░   ▒██  ▀█▄  ▓██    ▓██░░██   █▌▒██  ▀█▄  \n"
                + "▒██░   ░██▄▄▄▄██ ▒██    ▒██ ░▓█▄   ▌░██▄▄▄▄██ \n"
                + "░██████▒▓█   ▓██▒▒██▒   ░██▒░▒████▓  ▓█   ▓██▒\n"
                + "░ ▒░▓  ░▒▒   ▓▒█░░ ▒░   ░  ░ ▒▒▓  ▒  ▒▒   ▓▒█░\n"
                + "░ ░ ▒  ░ ▒   ▒▒ ░░  ░      ░ ░ ▒  ▒   ▒   ▒▒ ░\n"
                + "  ░ ░    ░   ▒   ░      ░    ░ ░  ░   ░   ▒   \n"
                + "    ░  ░     ░  ░       ░      ░          ░  ░\n"
                + "                           ░                  \n";
        return logo;
    }

    /**
     * To greet the user with welcoming messages
     * @return greeting message
     */
    public static String greet() {
        return "Hi, I am LaMDA, your personal assistant.\nHow may I assist you today?\n";
    }

    /**
     * To wait for 3 seconds before closing the program
     */
    public static void exitProgram() {
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                Platform.exit();
            }
        }).start();
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
        return ("It's a great time talking with you.\nSee you next time!\n\nClosing automatically after 3 seconds.\n");
    }

    /**
     * Display a list of commands that LaMDA can understand
     * @return list of available commands
     */
    public String helpTask() {
        return ("I can only understand the commands below:\n"
                + "  1. todo <task name>\n"
                + "  2. deadline <task name> /by <date>\n"
                + "  3. event <task name> /at <place>\n"
                + "  4. list\n"
                + "  5. mark <task number>\n"
                + "  6. unmark <task number>\n"
                + "  7. delete <task number>\n"
                + "  8. find <keyword>\n"
                + "  9. bye\n");
    }

    /**
     * To display the list of current tasks
     * @param tasks the current {@code TaskList}
     * @return the list of tasks
     */
    public String listTask(TaskList tasks) {
        return ("Here are the tasks in your list:\n" + tasks.listTasks());
    }

    /**
     * To display a message after a {@code Todo} has been successfully added
     * @param tasks the current {@code TaskList}
     * @param todo the added {@code Todo}
     * @return a notification that tell's the user that the todo task has been created
     */
    public String todoTask(TaskList tasks, Todo todo) {
        return ("Got it. I've added this task:\n")
                + ("  " + todo.toString() + "\n")
                + ("Now you have " + tasks.size() + " tasks in the list.\n");
    }

    /**
     * To display a message after a {@code Deadline} has been successfully added
     * @param tasks the current {@code TaskList}
     * @param deadline the added {@code Deadline}
     * @return a notification that tell's the user that the deadline task has been created
     */
    public String deadlineTask(TaskList tasks, Deadline deadline) {
        return ("Got it. I've added this task:\n")
                + ("  " + deadline.toString() + "\n")
                + ("Now you have " + tasks.size() + " tasks in the list.\n");
    }


    /**
     * To display a message after a {@code Event} has been successfully added
     * @param tasks the current {@code TaskList}
     * @param event the added {@code Event}
     * @return a notification that tell's the user that the event task has been created
     */
    public String eventTask(TaskList tasks, Event event) {
        return ("Got it. I've added this task:\n")
                + ("  " + event.toString() + "\n")
                + ("Now you have " + tasks.size() + " tasks in the list.\n");
    }


    /**
     * To display a message after a {@code Task} has been successfully marked
     * @param task the marked {@code Task}
     * @return a notification that tell's the user that the task has been marked
     */
    public String markTask(Task task) {
        return ("I've marked this task as done:\n")
                + ("  " + task.toString() + "\n");
    }


    /**
     * To display a message after a {@code Task} has been successfully unmarked
     * @param task the unmarked {@code Task}
     * @return a notification that tell's the user that the task has been unmarked
     */
    public String unmarkTask(Task task) {
        return ("I've marked this task as not done yet:\n")
                + ("  " + task.toString() + "\n");
    }


    /**
     * To display a message after a {@code Task} has been successfully deleted
     * @param task the deleted {@code Task}
     * @return a notification that tell's the user that the task has been removed
     */
    public String deleteTask(TaskList tasks, Task task) {
        return ("Noted. I've removed this task:\n")
                + ("  " + task.toString() + "\n")
                + ("Now you have " + tasks.size() + " tasks in the list.\n");
    }

    /**
     * To display all the tasks that match the search
     * @param tasks the current {@code TaskList}
     * @param description the user input to find marching {@code Task}
     * @return the task that matches the search
     */
    public String findTask(TaskList tasks, String description) {
        ArrayList<Task> temp = tasks.find(description);
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < temp.size(); i++) {
            list.append("  ").append(i + 1).append(".").append(temp.get(i).toString()).append("\n");
        }
        return ("Here are the matching tasks in your list:\n")
                + list;
    }
}
