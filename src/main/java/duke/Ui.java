package duke;

import duke.task.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    Scanner scn;

    /**
     * To greet the user with logo and welcoming messages
     */
    public void greet() {
        String logo = "\n" +
                "   ██▓    ▄▄▄       ███▄ ▄███▓▓█████▄  ▄▄▄\n" +
                "  ▓██▒   ▒████▄    ▓██▒▀█▀ ██▒▒██▀ ██▌▒████▄\n" +
                "  ▒██░   ▒██  ▀█▄  ▓██    ▓██░░██   █▌▒██  ▀█▄\n" +
                "  ▒██░   ░██▄▄▄▄██ ▒██    ▒██ ░▓█▄   ▌░██▄▄▄▄██\n" +
                "  ░██████▒▓█   ▓██▒▒██▒   ░██▒░▒████▓  ▓█   ▓██▒\n" +
                "  ░ ▒░▓  ░▒▒   ▓▒█░░ ▒░   ░  ░ ▒▒▓  ▒  ▒▒   ▓▒█░\n" +
                "  ░ ░ ▒  ░ ▒   ▒▒ ░░  ░      ░ ░ ▒  ▒   ▒   ▒▒ ░\n" +
                "    ░ ░    ░   ▒   ░      ░    ░ ░  ░   ░   ▒\n" +
                "      ░  ░     ░  ░       ░      ░          ░  ░\n" +
                "                               ░\n";
        System.out.print(logo);
        System.out.println("Hi, I am LaMDA.\nHow may I assist you today?\n");
        scn = new Scanner(System.in);
    }

    /**
     * To display a splitting line
     */
    public void showLine() {
        System.out.println("\t____________________________________________");
    }

    /**
     * To read the input of the user
     * @return the user input in the form of a string
     */
    public String readCommand() {
        return scn.nextLine();
    }

    /**
     * To show error if the file is not found
     */

    public void showLoadingError() {
        System.out.println("Error locating \"tasks.dat\".\nA new file will be created.");
    }

    /**
     * To show general errors
     * @param s the error message
     */
    public void showError(String s) {
        System.out.println(s);
    }


    /**
     * To display exit message
     */
    public void exitTask() {
        System.out.println("\t It's a great time talking with you.\n\t See you next time!");
        scn.close();
    }

    /**
     * To display the list of current tasks
     * @param tasks
     */
    public void listTask(TaskList tasks) {
        System.out.println("\t Here are the tasks in your list:");
        tasks.listTasks();
    }

    /**
     * To display a message after a {@code Todo} has been successfully added
     * @param tasks the current {@code TaskList}
     * @param todo the added {@code Todo}
     */
    public void todoTask(TaskList tasks, Todo todo) {
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   " + todo.toString());
        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * To display a message after a {@code Deadline} has been successfully added
     * @param tasks the current {@code TaskList}
     * @param deadline the added {@code Deadline}
     */
    public void deadlineTask(TaskList tasks, Deadline deadline) {
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   " + deadline.toString());
        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * To display a message after a {@code Event} has been successfully added
     * @param tasks the current {@code TaskList}
     * @param event the added {@code Event}
     */
    public void eventTask(TaskList tasks, Event event) {
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   " + event.toString());
        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * To display a message after a {@code Task} has been successfully marked
     * @param task the marked {@code Task}
     */
    public void markTask(Task task) {
        System.out.println("\t I've marked this task as done:");
        System.out.println("\t   " + task.toString());
    }

    /**
     * To display a message after a {@code Task} has been successfully unmarked
     * @param task the unmarked {@code Task}
     */
    public void unmarkTask(Task task) {
        System.out.println("\t I've marked this task as not done yet:");
        System.out.println("\t   " + task.toString());
    }

    /**
     * To display a message after a {@code Task} has been successfully deleted
     * @param task the deleted {@code Task}
     */
    public void deleteTask(TaskList tasks, Task task) {
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t   " + task.toString());
        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
    }

    public void findTask(TaskList tasks, String description) {
        System.out.println("\t Here are the matching tasks in your list:");
        ArrayList<Task> temp = tasks.find(description);
        for (int i = 0; i < temp.size(); i++) {
            System.out.println("\t " + (i + 1) + "." + temp.get(i).toString());
        }
    }
}
