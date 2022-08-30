package duke;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    private static boolean startsWith(String cmd, String prefix) {
        String[] stuff = cmd.split(" ");
        return stuff[0].equals(prefix);
    }

    public void parseCommand(String s, TaskList ts, Ui ui, Storage store) throws IOException {

        if (s.equals("list")) {

            ui.printTaskList(ts);

        } else if (s.equals("bye")) {

            ui.goodbye();

        } else if (s.equals("clear")) {

            ts.clear();
            ui.printSuccessfulClear();
            ui.printSpacer();

        } else if (startsWith(s, "mark")) {

            try {
                Task t = ts.markTask(s);
                ui.printSuccessfulMark();
                ui.printTask(t);
                ui.printSpacer();
            } catch (TaskNumberException e) {
                ui.printErrorMessage(e, ts);
            }

        } else if (startsWith(s, "unmark")) {

            try {
                Task t = ts.unmarkTask(s);
                ui.printSuccessfulUnmark();
                ui.printTask(t);
                ui.printSpacer();
            } catch (TaskNumberException e) {
                ui.printErrorMessage(e, ts);
            }

        } else if (startsWith(s, "todo")) {

            try {
                Task t = ts.addTodo(s);
                ui.printSuccessfulAdd();
                ui.printTask(t);
                ui.printNoOfTasks(ts);
                ui.printSpacer();
            } catch (EmptyTodoException e) {
                ui.printErrorMessage(e, ts);
            }

        } else if (startsWith(s, "deadline")) {

            try {
                Task t = ts.addDeadline(s);
                ui.printSuccessfulAdd();
                ui.printTask(t);
                ui.printNoOfTasks(ts);
                ui.printSpacer();
            } catch (DeadlineFormatException e) {
                ui.printErrorMessage(e, ts);
            }

        } else if (startsWith(s, "event")) {

            try {
                Task t = ts.addEvent(s);
                ui.printSuccessfulAdd();
                ui.printTask(t);
                ui.printNoOfTasks(ts);
                ui.printSpacer();
            } catch (EventFormatException e) {
                ui.printErrorMessage(e, ts);
            }

        } else if (startsWith(s, "delete")) {

            try {
                Task t = ts.deleteTask(s);
                ui.printSuccessfulDelete();
                ui.printTask(t);
                ui.printNoOfTasks(ts);
                ui.printSpacer();
            } catch (TaskNumberException e) {
                ui.printErrorMessage(e, ts);
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
            ui.printErrorMessage(new InvalidCommandException(s), ts);
        }
        store.updateFile(ts);
    }
}
