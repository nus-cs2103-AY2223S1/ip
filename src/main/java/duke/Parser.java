package duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Encapsulates the parser for Duke.
 */
public class Parser {

    private static boolean startsWith(String command, String prefix) {
        String[] stuff = command.split(" ");
        return stuff[0].equals(prefix);
    }

    private void markTaskAndDisplay(String command, TaskList tasks, Ui ui) {
        try {
            Task t = tasks.markTask(command);
            ui.printSuccessfulMark();
            ui.printTask(t);
        } catch (TaskNumberException e) {
            ui.printErrorMessage(e, tasks);
        }
    }

    private void unmarkTaskAndDisplay(String command, TaskList tasks, Ui ui) {
        try {
            Task t = tasks.unmarkTask(command);
            ui.printSuccessfulUnmark();
            ui.printTask(t);
        } catch (TaskNumberException e) {
            ui.printErrorMessage(e, tasks);
        }
    }

    private void addTodoAndDisplay(String command, TaskList tasks, Ui ui) {
        try {
            Task t = tasks.addTodo(command);
            ui.printSuccessfulAdd();
            ui.printTask(t);
            ui.printNoOfTasks(tasks);
        } catch (EmptyFieldException e) {
            ui.printErrorMessage(e, tasks);
        }
    }

    private void addDeadlineAndDisplay(String command, TaskList tasks, Ui ui) {
        try {
            Task t = tasks.addDeadline(command);
            ui.printSuccessfulAdd();
            ui.printTask(t);
            ui.printNoOfTasks(tasks);
        } catch (DeadlineFormatException e) {
            ui.printErrorMessage(e, tasks);
        }
    }

    private void addEventAndDisplay(String command, TaskList tasks, Ui ui) {
        try {
            Task t = tasks.addEvent(command);
            ui.printSuccessfulAdd();
            ui.printTask(t);
            ui.printNoOfTasks(tasks);
        } catch (EventFormatException e) {
            ui.printErrorMessage(e, tasks);
        }
    }

    private void deleteTaskAndDisplay(String command, TaskList tasks, Ui ui) {
        try {
            Task t = tasks.deleteTask(command);
            ui.printSuccessfulDelete();
            ui.printTask(t);
            ui.printNoOfTasks(tasks);
        } catch (TaskNumberException e) {
            ui.printErrorMessage(e, tasks);
        }
    }

    private void findTaskAndDisplay(String command, TaskList tasks, Ui ui) {
        try {
            ArrayList<Task> result = tasks.findTasks(command);
            ui.printFoundResults(result);
        } catch (EmptyFieldException e) {
            ui.printErrorMessage(e, tasks);
        }
    }

    private void changeTaskDescAndDisplay(String command, TaskList tasks, Ui ui) {
        try {
            Task t = tasks.updateTaskDesc(command);
            ui.printSuccessfulUpdate();
            ui.printTask(t);
        } catch (TaskNumberException | EmptyFieldException e) {
            ui.printErrorMessage(e, tasks);
        }
    }

    private void changeTaskTimeAndDisplay(String command, TaskList tasks, Ui ui) {
        try {
            Task t = tasks.updateTaskTime(command);
            ui.printSuccessfulUpdate();
            ui.printTask(t);
        } catch (TaskNumberException | EmptyFieldException | TaskTypeException e) {
            ui.printErrorMessage(e, tasks);
        }
    }

    /**
     * Parses a given command and pass relevant instructions to the TaskList, Ui and Storage.
     *
     * @param command user input string
     * @param tasks TaskList to be modified if necessary
     * @param ui Ui to display response
     * @param store Storage to be modified if necessary
     */
    public void parseCommand(String command, TaskList tasks, Ui ui, Storage store) throws IOException {

        if (command.equals("list")) {
            ui.printTaskList(tasks);
        } else if (command.equals("bye")) {
            ui.goodbye();
        } else if (command.equals("clear")) {
            tasks.clear();
            ui.printSuccessfulClear();
        } else if (startsWith(command, "mark")) {
            markTaskAndDisplay(command, tasks, ui);
        } else if (startsWith(command, "unmark")) {
            unmarkTaskAndDisplay(command, tasks, ui);
        } else if (startsWith(command, "todo")) {
            addTodoAndDisplay(command, tasks, ui);
        } else if (startsWith(command, "deadline")) {
            addDeadlineAndDisplay(command, tasks, ui);
        } else if (startsWith(command, "event")) {
            addEventAndDisplay(command, tasks, ui);
        } else if (startsWith(command, "delete")) {
            deleteTaskAndDisplay(command, tasks, ui);
        } else if (startsWith(command, "find")) {
            findTaskAndDisplay(command, tasks, ui);
        } else if (startsWith(command, "changedesc")) {
            changeTaskDescAndDisplay(command, tasks, ui);
        } else if (startsWith(command, "changetime")) {
            changeTaskTimeAndDisplay(command, tasks, ui);
        } else {
            ui.printErrorMessage(new InvalidCommandException(command), tasks);
        }
        store.updateFile(tasks);
    }
}
