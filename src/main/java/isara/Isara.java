package isara;

import java.io.File;
import java.util.ArrayList;

import isara.command.Command;
import isara.exception.IsaraException;
import isara.processor.Parser;
import isara.processor.TaskList;
import isara.storage.Storage;
import isara.task.Task;

/**
 * The main class that executes the Duke chatbot.
 *
 * @author Melissa Anastasia Harijanto
 */
public class Isara {
    /** List of tasks. */
    private TaskList tasks;
    /**
     * The location where the bot will write into.
     */
    private Storage storage;
    /**
     * Ui that prints statements for the bot.
     */
    private Ui ui;
    private File dukeFile;
    private boolean hasExit;

    /**
     * Constructs an instance of the Duke chatbot.
     */
    public Isara() {
        this.tasks = new TaskList();
        this.storage = new Storage();
        this.ui = new Ui();

        try {
            File dukeFile = storage.initialize();
            ArrayList<Task> tasksFromFile = storage.readFile(dukeFile);
            this.tasks = new TaskList(tasksFromFile);
            this.dukeFile = dukeFile;
        } catch (IsaraException e) {
            ui.errorMessage(e);
        }
    }

    /**
     * Runs the Duke chatbot.
     */
    public String run(String userInput) {
        String output = "";
        String commandLine = userInput.split(" ")[0];
        try {
            if (Parser.isTask(commandLine)) {
                Task task = Parser.parseTask(userInput);
                tasks.addTask(task);
                int amountOfTasks = tasks.getNumberOfTasks();
                storage.writeAndSaveToFile(dukeFile, tasks);
                return ui.addTask(task, amountOfTasks);
            } else if (Parser.isCommand(commandLine)) {
                Command command = Parser.parseCommand(userInput);
                output = command.execute(tasks, dukeFile, storage);
                hasExit = command.getExitStatus();
                return output;
            } else {
                return ui.commandDoesNotExist();
            }
        } catch (IsaraException e) {
            return ui.errorMessage(e);
        }
    }

    /**
     * Gets response from the bot.
     */
    protected String getResponse(String input) {
        return run(input);
    }

    /**
     * Gets the exit status from the bot.
     *
     * @return Boolean value whether the bot has exited or not.
     */
    public boolean getExitStatus() {
        return hasExit;
    }

}
