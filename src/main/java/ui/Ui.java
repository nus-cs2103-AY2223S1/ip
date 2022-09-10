package ui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
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
    private static final String SEP = "\n✧  ✡︎✮ ✰ ✦ ✨️ ❍  ✫  ✶  ✧︎ ✱✬ ✨   ❇︎ ✫❍   ❈ ✶  ❍✶  ✯❃  ✨\n";
    private static Scanner sc;
    private boolean isLoaded;
    private Parser parser;

    /**
     * Starts the UI by initialising a scanner to take in user commands.
     */
    public Ui(Parser parser) {
        this.sc = new Scanner(System.in);
        this.isLoaded = false;
        this.parser = parser;
    }

    public void setLoaded() {
        this.isLoaded = true;
    }

    /**
     * Displays the welcome message upon start up of the chatbot.
     * Shows the bot logo, lists available commands and prints out stored tasks if any.
     */
    public String showWelcome() {
        // Print Welcome message
        String toPrint = "Hello. ⛅️ This is\n \t\t\t\t\t L \t U \t N \t A 🌙\n";

        // Print available commands
        toPrint += "\n  Luna commands"
                + "\n    🌸 list"
                + "\n    🌷 todo <task>"
                + "\n    🌺 deadline <task> /by <yyyy-mm-dd>"
                + "\n    🌹 event <event> /at <yyyy-mm-dd>"
                + "\n    🪷 mark <num>"
                + "\n    🌻 unmark <num>"
                + "\n    💐 find <keyword>"
                + "\n    🥀 bye\n";

        // Print items in storage
        if (!this.isLoaded) {
            toPrint += "\n  You don't have anything to do yet!\n  Tell Luna your tasks for the day ☀️";
        } else {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("data/luna.txt"));
                Stream<String> contentStream = reader.lines();
                List<String> contentList = contentStream.collect(Collectors.toList());

                for (String content : contentList) {
                    toPrint += "\n" + content;
                }

            } catch (FileNotFoundException e) {
                showError(new LunaLoadingException());
            }
        }

        // Print final separation line
        return addLine(toPrint);
    }

    /**
     *  Bids the user farewell before exiting the chatbot.
     */
    public String showFarewell() {
        return "\n . ❍  ❃ ☆  ✶ ❅  🌙 Goodbye from Luna 🌙  ❅ ✶  ☆ ❃  ❍  .\n";
    }

    public String addLine(String message) {
        return SEP + "\n" + message + "\n" + SEP;
    }

    /**
     * Prints out error message formatted with line divider.
     *
     * @param e An Exception to be displayed to user
     */
    public String showError(LunaException e) {
        return "✡︎✮ ✰ ✦ ✨️ ❍ " + e.toString() + " ✨   ❇︎ ✫❍   ❈ ✶";
    }

    /**
     * Informs user that a task has been successfully added.
     *
     * @param tasks Updated list of tasks added by user.
     * @param task Current task added.
     */
    public String showAdded(TaskList tasks, Task task) {
        return addLine("Luna has added:\n" + task.toString()
                + "\n" + tasks.size() + " task(s) left in your list 🌻");
    }

    /**
     * Informs user that a task has been successfully deleted.
     *
     * @param tasks Updated list of tasks saved by user.
     * @param task Task deleted.
     */
    public String showDeleted(TaskList tasks, Task task) {
        return addLine("Luna has removed:\n" + task.toString()
                + "\n" + tasks.size() + " task(s) left in your list 🌻");

    }

    /**
     * Displays all the tasks that the user has added.
     *
     * @param tasks List of tasks added by user.
     */
    public String showList(TaskList tasks) {
        return addLine("\n☀️ Stuff you have to do! ☀️\n\n" + tasks);
    }

    /**
     * Informs user that a task has been successfully marked as completed.
     *
     * @param task Task marked as completed.
     */
    public String showMark(Task task) {
        return addLine("Marked as completed 🌈️\n" + task.toString());
    }

    /**
     * Informs user that a task has been successfully marked as uncompleted.
     *
     * @param task Task marked as uncompleted.
     */
    public String showUnmark(Task task) {
        return addLine("Marked as uncompleted 🌩\n" + task.toString());
    }

    /**
     * Displays the tasks found according to search keywords.
     *
     * @param tasks String representation of relevant tasks.
     */
    public String showFound(String tasks) {
        String result = "";
        if (tasks.equals("")) {
            result += "\nLuna didn't find any relevant tasks 🍂\n";
        } else {
            result += "\n☁️ Here are the tasks Luna has found! ☁️" + tasks;
        }
        return addLine(result);
    }
}
