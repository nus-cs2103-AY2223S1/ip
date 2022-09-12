package duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.ContentNotFoundException;
import duke.exception.DateNotFoundException;
import duke.exception.TaskNotFoundException;
import duke.task.Task;
import duke.task.TaskList;
import duke.tools.CommandParser;
import duke.tools.Storage;
import duke.tools.Ui;

/**
 * Task Master called Duke to manage those pesky tasks.
 */
public class Duke {

    private TaskList tasks;
    private Storage storage;

    private Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = storage.load();
    }

    public Duke() {
        new Duke("./data/");
    }

    public String getResponse(String input) {
        CommandParser keywordParser = new CommandParser(input);
        if (input.contentEquals("bye")) {
            return Ui.goodbye();
        } else {
            CommandParser.Commands enumCommand;
            try {
                enumCommand = keywordParser.getCommand();
            } catch (TaskNotFoundException e) {
                return Ui.noTaskExceptionToast(e);
            }
            switch (enumCommand) {
            case BYE:
                return Ui.goodbye();
            case LIST:
                return tasks.list();
            case MARK:
                Task doneTask = tasks.markTask(keywordParser.getTaskNo());
                storage.saveTasks(tasks);
                return Ui.congrats(doneTask);
            case UNMARK:
                Task undoneTask = tasks.unmarkTask(keywordParser.getTaskNo());
                storage.saveTasks(tasks);
                return Ui.undoneToast(undoneTask);
            case DELETE:
                Task deleted = tasks.deleteTask(keywordParser.getTaskNo());
                storage.saveTasks(tasks);
                return Ui.deleteTaskToast(deleted, tasks.size());
            case FIND:
                ArrayList<Task> result = tasks.findTasks(keywordParser.getWord());
                return Ui.foundTaskToast() + "\n"
                        + Ui.printList(result);
            default:
                return getTaskResponse(input);
            }
        }
    }

    private String getTaskResponse(String input) {
        try {
            String response = tasks.addTask(input);
            storage.saveTasks(tasks);
            return response;
        } catch (TaskNotFoundException e) {
            return Ui.noTaskExceptionToast(e);
        } catch (ContentNotFoundException e) {
            return Ui.noContentExceptionToast(e);
        } catch (DateNotFoundException e) {
            return Ui.noDateExceptionToast(e);
        } catch (DateTimeParseException e) {
            return Ui.wrongDateFormatToast(e);
        }
    }
}
