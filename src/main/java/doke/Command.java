package doke;

import java.io.IOException;

import javafx.util.Pair;


/**
 * A command class to contain all command
 *
 * @author Stevan Gerard Gunawan
 */
public class Command {

    static boolean byeCommand() {
        return false;
    }

    static boolean listCommand(TaskList taskList, Ui ui) {
        taskList.listOut(ui);
        return true;
    }

    static boolean addToDoCommand(TaskList taskList, Ui ui, Storage storage, String string) {
        if (string.equals(null)) {
            throw new DokeException("todo");
        }
        ToDo newTask = new ToDo(string);

        try {
            storage.writeToFile(Storage.createWordForFile(newTask));
        } catch (IOException e) {
            ui.printMiscErrorMessage();
            return true;
        }

        taskList.addTask(newTask);
        ui.printAddMessage(newTask, taskList.getSize());
        return true;
    }

    static boolean addDeadlineCommand(TaskList taskList, Ui ui, Storage storage, Pair<String, String> descTimePair) {
        String desc = descTimePair.getKey();
        String time = descTimePair.getValue();
        if (desc.equals(null) || time.equals(null)) {
            throw new DokeException("deadline");
        }
        Deadline newTask = new Deadline(desc, time);

        try {
            storage.writeToFile(Storage.createWordForFile(newTask));
        } catch (IOException e) {
            ui.printMiscErrorMessage();
            return true;
        }

        taskList.addTask(newTask);
        ui.printAddMessage(newTask, taskList.getSize());
        return true;
    }

    static boolean addEventCommand(TaskList taskList, Ui ui, Storage storage, Pair<String, String> descTimePair) {
        String desc = descTimePair.getKey();
        String time = descTimePair.getValue();
        if (desc.equals(null)) {
            throw new DokeException("event");
        }
        Event newTask = new Event(desc, time);

        try {
            storage.writeToFile(Storage.createWordForFile(newTask));
        } catch (IOException e) {
            ui.printMiscErrorMessage();
            return true;
        }
        taskList.addTask(newTask);
        ui.printAddMessage(newTask, taskList.getSize());
        return true;
    }

    static boolean markCommand(TaskList taskList, Ui ui, Storage storage, int i) {
        Task current = taskList.getTask(i);
        String message;
        try {
            current.markDone();
            storage.updateFile(taskList.getList(), ui);
            ui.printMarkMessage(false, current);
        } catch (DokeException a) {
            ui.printMarkMessage(true, current);
        }
        return true;
    }

    static boolean unmarkCommand(TaskList taskList, Ui ui, Storage storage, int i) {
        Task current = taskList.getTask(i);
        String message;
        try {
            current.markNotDone();
            storage.updateFile(taskList.getList(), ui);
            ui.printMarkMessage(false, current);
        } catch (DokeException a) {
            ui.printMarkMessage(true, current);
        }
        return true;
    }

    static boolean deleteCommand(TaskList taskList, Ui ui, Storage storage, int i) {
        Task toDelete = taskList.getTask(i);
        taskList.deleteTask(i);
        storage.updateFile(taskList.getList(), ui);
        ui.printDeleteMessage(toDelete, taskList.getSize());
        return true;
    }

    static boolean findCommand(TaskList taskList, Ui ui, String string) {
        String search = string.substring(5);
        taskList.searchString(search, ui);
        return true;
    }
}
