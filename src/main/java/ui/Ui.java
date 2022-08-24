package ui;

import tasks.Task;
import tasks.TaskList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Deals with interactions with the user.
 *
 * @author fannyjian
 */
public class Ui {
    private static final String sep = "\n✧  ✡︎✮ ✰ ✦ ✨️ ❍  ✫   ✣❈ ✶  ✧︎ ✱✬ ✨   ❇︎ ✫❍   ❈ ✶  ❍✶  ✯❃  ✨\n";
    private static Scanner sc;
    private boolean loaded;

    /**
     * Starts the UI by initialising a scanner to take in user commands.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
        this.loaded = false;
    }

    public void setLoaded() {
        this.loaded = true;
    }

    /**
     * Displays the welcome message upon start up of the chatbot.
     * Shows the bot logo, lists available commands and prints out stored tasks if any.
     */
    public void showWelcome() {
        //print Welcome message
        String logo =
                  "    _\n"
                + "   | |    _   _ _____   ___ _\n"
                + "   | |   | | | |  __ \\ /     |\n"
                + "   | |__ | |_| | |  | |    | |\n"
                + "   |____| \\__,_|_|  |_|\\__/|_|\n";
        System.out.println(sep + "\nHello. ⛅️\n   This is\n" + logo);

        //print available commands
        System.out.println("  Luna commands" +
                    "\n    🌸 list                             | View all tasks on your agenda" +
                    "\n    🌷 todo \"task\"                      | Add a task to your agenda" +
                    "\n    🌺 deadline \"task\" /by \"yyyy-mm-dd\" | Add a task to complete by the specified deadline" +
                    "\n    🌹 event \"event\" /at \"yyyy-mm-dd\"   | Add an event on the specified date" +
                    "\n    🪷 mark \"num\"                       | Mark the (num)th item in your list as completed"+
                    "\n    🌻 unmark \"num\"                     | Mark the (num)th item in your list as uncompleted" +
                    "\n    🥀 bye                              | Quit Luna\n");

        //print items in storage
        if (!this.loaded) {
            System.out.println("  You do not have anything to do yet!\n  Tell Luna your tasks for the day ☀️");
        } else {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("data/luna.txt"));
                Stream<String> content = reader.lines();

                content.forEach(s -> System.out.println(s));
            } catch (FileNotFoundException e) {
                showLoadingError();
            }
        }

        //print final separation line
        System.out.println(sep);
    }

    public void showLoadingError() {
        System.out.println("⚡️Luna has encountered an error while loading tasks⚡️" +
                    "\n️Please exit and try again ️⛈");
    }

    /**
     *  Bids the user farewell before exiting the chatbot.
     */
    public void farewell() {
        System.out.println("\n . ❍  ❃ ☆  ✶ ❅  🌙 Goodbye from Luna 🌙  ❅ ✶  ☆ ❃  ❍  .\n");
        sc.close();
    }

    public void showLine() {
        System.out.println(sep);
    }

    /**
     * Prints out error message formatted with line divider.
     *
     * @param message Error message.
     */
    public void showError(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    /**
     * Informs user that a task has been successfully added.
     *
     * @param tasks Updated list of tasks added by user.
     * @param task Current task added.
     */
    public void showAdded(TaskList tasks, Task task) {
        System.out.println(sep + "\nLuna has added:\n" + task.toString() + "\n" + tasks.size() + " task(s) left in your list 🌻\n" + sep);
    }

    /**
     * Informs user that a task has been successfully deleted.
     *
     * @param tasks Updated list of tasks saved by user.
     * @param task Task deleted.
     */
    public void showDeleted(TaskList tasks, Task task) {
        System.out.println(sep + "\nLuna has removed:\n" + task.toString() + "\n" + tasks.size() + " task(s) left in your list 🌻\n" + sep);

    }

    /**
     * Displays all the tasks that the user has added.
     *
     * @param tasks List of tasks added by user.
     */
    public void showList(TaskList tasks) {
        showLine();
        System.out.println("\n☀️ Stuff you have to do! ☀️\n");
        System.out.println(tasks);
        showLine();
    }

    /**
     * Informs user that a task has been successfully marked as completed.
     *
     * @param task Task marked as completed.
     */
    public void showMark(Task task) {
        System.out.println(sep + "\nMarked as completed 🌈️\n" + task.toString() + "\n" + sep);
    }

    /**
     * Informs user that a task has been successfully marked as uncompleted.
     *
     * @param task Task marked as uncompleted.
     */
    public void showUnmark(Task task) {
        System.out.println(sep + "\nMarked as uncompleted 🌩\n" + task.toString() + "\n" + sep);
    }

    /**
     * Scans the next command entered by user.
     *
     * @return Command entered by user.
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
