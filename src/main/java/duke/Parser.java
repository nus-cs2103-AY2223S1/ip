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
            ui.printSpacer();

        } else if (startsWith(command, "mark")) {

            try {
                Task t = tasks.markTask(command);
                ui.printSuccessfulMark();
                ui.printTask(t);
                ui.printSpacer();
            } catch (TaskNumberException e) {
                ui.printErrorMessage(e, tasks);
            }

        } else if (startsWith(command, "unmark")) {

            try {
                Task t = tasks.unmarkTask(command);
                ui.printSuccessfulUnmark();
                ui.printTask(t);
                ui.printSpacer();
            } catch (TaskNumberException e) {
                ui.printErrorMessage(e, tasks);
            }

        } else if (startsWith(command, "todo")) {

            try {
                Task t = tasks.addTodo(command);
                ui.printSuccessfulAdd();
                ui.printTask(t);
                ui.printNoOfTasks(tasks);
                ui.printSpacer();
            } catch (EmptyTodoException e) {
                ui.printErrorMessage(e, tasks);
            }

        } else if (startsWith(command, "deadline")) {

            try {
                Task t = tasks.addDeadline(command);
                ui.printSuccessfulAdd();
                ui.printTask(t);
                ui.printNoOfTasks(tasks);
                ui.printSpacer();
            } catch (DeadlineFormatException e) {
                ui.printErrorMessage(e, tasks);
            }

        } else if (startsWith(command, "event")) {

            try {
                Task t = tasks.addEvent(command);
                ui.printSuccessfulAdd();
                ui.printTask(t);
                ui.printNoOfTasks(tasks);
                ui.printSpacer();
            } catch (EventFormatException e) {
                ui.printErrorMessage(e, tasks);
            }

        } else if (startsWith(command, "delete")) {

            try {
                Task t = tasks.deleteTask(command);
                ui.printSuccessfulDelete();
                ui.printTask(t);
                ui.printNoOfTasks(tasks);
                ui.printSpacer();
            } catch (TaskNumberException e) {
                ui.printErrorMessage(e, tasks);
            }

        } else if (startsWith(s, "find")) {

            try {
                ArrayList<Task> result = ts.findTasks(s);
                ui.printFoundResults(result);
                ui.printSpacer();
            } catch (EmptyFindException e) {
                ui.printErrorMessage(e, ts);
            }

        } else {
            ui.printErrorMessage(new InvalidCommandException(command), tasks);
        }
        store.updateFile(tasks);
    }
}
