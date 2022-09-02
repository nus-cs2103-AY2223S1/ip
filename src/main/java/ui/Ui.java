package ui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.stream.Stream;

import exception.LunaException;
import exception.LunaLoadingException;
import parser.Parser;
import task.Task;
import task.TaskList;

/**
 * Deals with interactions with the user.
 *
 * @author fannyjian
 */
public class Ui {
    private static final String SEP = "\n✧  ✡︎✮ ✰ ✦ ✨️ ❍  ✫   ✣❈ ✶  ✧︎ ✱✬ ✨\n";
    private static Scanner sc;
    private boolean loaded;
    private Parser parser;

    /**
     * Starts the UI by initialising a scanner to take in user commands.
     */
    public Ui(Parser parser) {
        this.sc = new Scanner(System.in);
        this.loaded = false;
        this.parser = parser;
    }

    public void setLoaded() {
        this.loaded = true;
    }

    /**
     * Displays the welcome message upon start up of the chatbot.
     * Shows the bot logo, lists available commands and prints out stored tasks if any.
     */
    public String showWelcome() {
        // Print Welcome message
        String logo =
                  "    _                     ★ ☁️   ⋆\n"
                + "   | |    _   _ _____   ___ _ 🌙 ☁️\n"
                + "   | |   | | | |  __ \\ /     |☁️  ️✴  ⋆\n"
                + "   | |__ | |_| | |  | |    | |\n"
                + "   |____| \\__,_|_|  |_|\\__/|_|\n";
        String toPrint = SEP + "\nHello. ⛅️\n   This is\n" + logo;

        // Print available commands
        toPrint += "\n  Luna commands"
                + "\n    🌸 list"
                + "\n    🌷 todo <task>"
                + "\n    🌺 deadline <task> /by <yyyy-mm-dd>"
                + "\n    🌹 event <event> /at <yyyy-mm-dd>"
                + "\n    🪷 mark <num>"
                + "\n    🌻 unmark <num>"
                + "\n    💐 find <keyword>"
                + "\n    🥀 bye";

        // Print items in storage
        if (!this.loaded) {
            toPrint += "\n  You do not have anything to do yet!\n  Tell Luna your tasks for the day ☀️";
        } else {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("data/luna.txt"));
                Stream<String> content = reader.lines();

                content.forEach(s -> System.out.println(s));
            } catch (FileNotFoundException e) {
                showError(new LunaLoadingException());
            }
        }

        // Print final separation line
        return toPrint + "\n" + SEP;
    }

    /**
     *  Bids the user farewell before exiting the chatbot.
     */
    public String farewell() {
        return "\n . ❍  ❃ ☆  ✶ ❅  🌙 Goodbye from Luna 🌙  ❅ ✶  ☆ ❃  ❍  .\n";
//        sc.close();
    }

    public String showLine() {
        return SEP;
    }

    /**
     * Prints out error message formatted with line divider.
     *
     * @param e An Exception to be displayed to user
     */
    public String showError(LunaException e) {
        return showLine() + "\n" + e.toString() + "\n" + showLine();
    }

    /**
     * Informs user that a task has been successfully added.
     *
     * @param tasks Updated list of tasks added by user.
     * @param task Current task added.
     */
    public String showAdded(TaskList tasks, Task task) {
        return showLine() + "\n Luna has added:\n" + task.toString()
                + "\n" + tasks.size() + " task(s) left in your list 🌻\n" + showLine();
    }

    /**
     * Informs user that a task has been successfully deleted.
     *
     * @param tasks Updated list of tasks saved by user.
     * @param task Task deleted.
     */
    public String showDeleted(TaskList tasks, Task task) {
        return showLine() + "\nLuna has removed:\n" + task.toString()
                + "\n" + tasks.size() + " task(s) left in your list 🌻)\n" + showLine();

    }

    /**
     * Displays all the tasks that the user has added.
     *
     * @param tasks List of tasks added by user.
     */
    public String showList(TaskList tasks) {
        return showLine() + "\n\n☀️ Stuff you have to do! ☀️\n\n" + tasks + "\n" + showLine();
    }

    /**
     * Informs user that a task has been successfully marked as completed.
     *
     * @param task Task marked as completed.
     */
    public String showMark(Task task) {
        return showLine() + "\nMarked as completed 🌈️\n" + task.toString() + "\n" + showLine();
    }

    /**
     * Informs user that a task has been successfully marked as uncompleted.
     *
     * @param task Task marked as uncompleted.
     */
    public String showUnmark(Task task) {
        return showLine() + "\nMarked as uncompleted 🌩\n" + task.toString() + "\n" + showLine();
    }

    /**
     * Displays the tasks found according to search keywords.
     *
     * @param tasks String representation of relevant tasks.
     */
    public String showFound(String tasks) {
        String result = showLine();
        if (tasks.equals("")) {
            result += "\nLuna did not manage to find any relevant tasks 🍂\n";
        } else {
            result += "\n☁️ Here are the tasks Luna has found! ☁️" + tasks;
        }
        return result + "\n" + showLine();
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
