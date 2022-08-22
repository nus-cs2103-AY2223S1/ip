package duke;

import duke.exceptions.*;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> input) {
        this.tasks = input;
    }

    public void list() {
        Ui.listPrint(tasks);
    }

    public void listAdd(String type, String item) throws DukeException {
        // atodo creates an empty task if space input after command (unresolved)
        Task currTask;
        String[] args;
        switch (type) {
        case "todo":
            currTask = new Todo(item);
            tasks.add(currTask);
            Ui.addTask("todo", currTask, tasks.size());
            Storage.save(tasks);
            break;
        case "deadline":
            args = item.split("/by ");
            try{
                currTask = new Deadline(args[0], args[1]);
                tasks.add(currTask);
                Ui.addTask("deadline", currTask, tasks.size());
                Storage.save(tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeMissingInputException(type);
            } catch (DateTimeParseException e) {
                throw new DukeUnknownDateException(type);
            }
            break;
        case "event":
            args = item.split("/at ");
            try{
                currTask = new Event(args[0], args[1]);
                tasks.add(currTask);
                Ui.addTask("event", currTask, tasks.size());
                Storage.save(tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeMissingInputException(type);
            } catch (DateTimeParseException e) {
                throw new DukeUnknownDateException(type);
            }
            break;
         }
    }

    public void listDelete(String indexString) throws DukeException {
        int index = 0;
        try {
            index = Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException e) {
            throw new DukeWrongInputException("delete");
        }
        if (index >= tasks.size() || index < 0) {
            throw new DukeListOOBException(index + 1);
        }
        Task currTask = tasks.remove(index);
        Ui.deleteTask(currTask, tasks.size());
        Storage.save(tasks);
    }

    public void listToggle(String indexString) throws DukeException{
        int index = 0;
        try {
            index = Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException e) {
            throw new DukeWrongInputException("mark");
        }
        if (index >= tasks.size() || index < 0) {
            throw new DukeListOOBException(index + 1);
        }
        Task currTask = tasks.get(index);
        currTask.completeToggle();
        Ui.toggleTask(currTask);
        Storage.save(tasks);
    }
}
