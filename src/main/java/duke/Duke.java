package duke;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.processor.Parser;
import duke.processor.TaskList;
import duke.storage.Storage;
import duke.task.Task;

/**
 * The main class that executes the Duke chatbot.
 *
 * @author Melissa Anastasia Harijanto
 */
public class Duke {
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

    /**
     * Constructs an instance of the Duke chatbot.
     */
    public Duke() {
        this.tasks = new TaskList();
        this.storage = new Storage();
        this.ui = new Ui();
    }

    /**
     * Runs the Duke chatbot.
     */
    public void run() {
        ui.greet();

        try {
            File dukeFile = storage.initialize();
            ArrayList<Task> tasksFromFile = storage.readFile(dukeFile);
            this.tasks = new TaskList(tasksFromFile);
            Scanner sc = new Scanner(System.in);
            String userInput = sc.nextLine();

            while (true) {
                String commandLine = userInput.split(" ")[0];

                try {
                    if (Parser.isTask(commandLine)) {
                        Task task = Parser.parseTask(userInput);
                        tasks.addTask(task);
                        int amountOfTasks = tasks.getNumberOfTasks();
                        ui.addTask(task, amountOfTasks);
                    } else if (Parser.isCommand(commandLine)) {
                        Command command = Parser.parseCommand(userInput);
                        command.execute(tasks);
                        if (commandLine.equals("bye")) {
                            storage.writeAndSaveToFile(dukeFile, tasks);
                            sc.close();
                            return;
                        }
                    } else {
                        ui.commandDoesNotExist();
                    }
                } catch (DukeException e) {
                    ui.errorMessage(e);
                }

                userInput = sc.nextLine();
            }

        } catch (DukeException e) {
            ui.errorMessage(e);
        }
    }

    /**
     * Executes the bot.
     *
     * @param args Main arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
