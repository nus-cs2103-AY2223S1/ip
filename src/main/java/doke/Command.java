package doke;

import java.io.IOException;
import java.util.Collections;

import javafx.util.Pair;


/**
 * A command class to contain all command
 *
 * @author Stevan Gerard Gunawan
 */
public class Command {

    static Pair<Boolean, String> byeCommand() {
        return new Pair<>(false, "bye");
    }

    static Pair<Boolean, String> listCommand(TaskList taskList, Ui ui) {
        taskList.listOut(ui);
        return new Pair<>(true, taskList.toString());
    }

    static Pair<Boolean, String> sortCommand(Storage storage, Ui ui, TaskList taskList) {
        Collections.sort(taskList.getList());
        ui.printOut("Here's the sorted task");
        taskList.listOut(ui);
        String message = "Here's the sorted task : " + taskList.toString();
        storage.updateFile(taskList.getList(), ui);
        return new Pair<>(true, message);
    }

    static Pair<Boolean, String> addToDoCommand(TaskList taskList, Ui ui, Storage storage, String string) {
        if (string.equals(null)) {
            throw new DokeException("todo");
        }
        ToDo newTask = new ToDo(string);

        try {
            storage.writeToFile(Storage.createWordForFile(newTask));
        } catch (IOException e) {
            ui.printMiscErrorMessage();
            return new Pair<>(true, "Something went wrong, try again");
        }

        taskList.addTask(newTask);
        ui.printAddMessage(newTask, taskList.getSize());
        return new Pair<>(true, "I've added the To Do!");
    }

    static Pair<Boolean, String> addDeadlineCommand(
            TaskList taskList, Ui ui, Storage storage, Pair<String, String> descTimePair) {
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
            return new Pair<>(true, "Something went wrong, try again");
        }

        taskList.addTask(newTask);
        ui.printAddMessage(newTask, taskList.getSize());
        return new Pair<>(true, "I've added the Deadline!");
    }

    static Pair<Boolean, String> addEventCommand(
            TaskList taskList, Ui ui, Storage storage, Pair<String, String> descTimePair) {
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
            return new Pair<>(true, "Something went wrong, try again");
        }
        taskList.addTask(newTask);
        ui.printAddMessage(newTask, taskList.getSize());
        return new Pair<>(true, "I've added the Event!");
    }

    static Pair<Boolean, String> markCommand(TaskList taskList, Ui ui, Storage storage, int i) {
        Task current = taskList.getTask(i);
        try {
            current.markDone();
            storage.updateFile(taskList.getList(), ui);
            ui.printMarkMessage(false, current);
            return new Pair<>(true, "I've marked the task!");
        } catch (DokeException a) {
            ui.printMarkMessage(true, current);
            return new Pair<>(true, "The task is already marked as so");
        }
    }

    static Pair<Boolean, String> unmarkCommand(TaskList taskList, Ui ui, Storage storage, int i) {
        Task current = taskList.getTask(i);
        try {
            current.markNotDone();
            storage.updateFile(taskList.getList(), ui);
            ui.printMarkMessage(false, current);
            return new Pair<>(true, "I've unmarked the task!");
        } catch (DokeException a) {
            ui.printMarkMessage(true, current);
            return new Pair<>(true, "The task is not marked yet");
        }
    }

    static Pair<Boolean, String> deleteCommand(TaskList taskList, Ui ui, Storage storage, int num) {
        Task toDelete = taskList.getTask(num);
        taskList.deleteTask(num);
        storage.updateFile(taskList.getList(), ui);
        ui.printDeleteMessage(toDelete, taskList.getSize());
        return new Pair<>(true, "I've deleted that task");
    }

    static Pair<Boolean, String> findCommand(TaskList taskList, Ui ui, String string) {
        String search = string.substring(5);
        String temp = taskList.searchString(search, ui);
        return new Pair<>(true, "temp");
    }
}
