package duke.core;

import duke.commands.*;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * A task manager bot that supports saving and loading.
 */
public class Duke {

    private final Ui ui = new Ui();
    private final Parser parser;
    private final TaskList taskList = new TaskList();

    private final String fileName;

    public Duke(String fileName) {
        this.parser = new Parser(new ArrayList<>(Arrays.asList(
                new Exit("bye", this.ui),
                new AddTaskCommand<>("todo", taskList, null, ToDo::new),
                new AddTaskCommand<>("deadline", taskList, " /by ", Deadline::new),
                new AddTaskCommand<>("event", taskList, " /at ", Event::new),
                new ListCommand("list", taskList),
                new MarkCommand("mark", taskList, true),
                new MarkCommand("unmark", taskList, false),
                new DeleteTaskCommand("delete", taskList)
        )));
        this.fileName = fileName;
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }

    public void run() {

        File taskFile = new File(fileName);

        Scanner scanner = null;
        try {
            scanner = new Scanner(taskFile);
        } catch (FileNotFoundException e) {
            ui.showMessage("No tasks found. Creating new...");
            try {
                taskFile.createNewFile();
            } catch (IOException f) {
                ui.showMessage("Unable to create new file for tasks.");
            }
        }

        if (scanner != null) {
            while (scanner.hasNextLine()) {
                String serializedTask = scanner.nextLine();
                Task t = Task.deserialize(serializedTask);
                if (t != null) {
                    taskList.addTask(t);
                }
            }
        }

        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                ui.showMessage(parser.parseInput(fullCommand));
                isExit = ui.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }

            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(fileName);
                fileWriter.write(taskList.serialize());
                fileWriter.close();
            } catch (IOException e) {
                ui.showMessage("Unable to write to file.");
            }
        }
    }
}
