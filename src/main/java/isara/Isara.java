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
    protected TaskList tasks;
    /**
     * The location where the bot will write into.
     */
    protected Storage storage;
    /**
     * Ui that prints statements for the bot.
     */
    protected Ui ui;

    protected File dukeFile;

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
                if (commandLine.equals("bye")) {
                    return output;
                }
                return output;
            } else {
                return ui.commandDoesNotExist();
            }
        } catch (IsaraException e) {
            return ui.errorMessage(e);
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        return run(input);
    }

}
