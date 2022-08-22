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

    // atodo, deadline and event breaks if no input is entered after each command (1 for atodo, 2 for others)
    // atodo creates an empty task if no input after command (unresolved)
    public void listAdd(String type, String item) throws DukeException {
        Task currTask;
        String[] args;
        switch(type) {
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

    // breaks if no input is entered after mark, or input isn't int, or index out of range
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

    // breaks if no input is entered after mark, or input isn't int, or index out of range
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

    /**
     * Calls Ui to print tasks containing regex in pretty UI
     * @param regex
     */
    public void find(String regex) {
        Ui.find(tasks, regex);
    }
}
