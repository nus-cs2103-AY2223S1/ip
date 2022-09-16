package duke.tasks;

import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidCommandException;
import duke.exceptions.DukeOutOfRangeException;

import duke.massops.AllOperation;
import duke.massops.MassOperation;
import duke.massops.RangeOperation;
import duke.massops.SingleOperation;

import duke.parser.Parser;
import duke.storage.Storage;

import java.util.ArrayList;

/**
 * Encapsulates a list of tasks in Duke
 */
public class TaskList {

    protected ArrayList<Task> tasks;
    protected Storage storage;

    /**
     * Initialises the tasks array
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initialises the tasks array by using existing data stored in
     * the local machine
     *
     * @param data An ArrayList of strings containing the saved user data
     */
    public TaskList(ArrayList<? extends String> data) {
        assert(data != null);
        this.tasks = new ArrayList<>();
        try {
            for (String sentence : data) {
                this.tasks.add(Parser.parseTask(sentence));
            }
        } catch (DukeException e) {
            this.tasks = new ArrayList<>();
        }
    }

    /**
     * Shows the list of tasks currently added to the TaskList
     *
     * @return a string containing the tasks added to the TaskList separated
     *     by a newline character
     */
    public String showList() {
        String str = "";
        assert(tasks != null);
        for (int i = 0; i < tasks.size() - 1; i++) {
            str += ((i + 1) + "." + tasks.get(i) + "\n");
        }
        if (tasks.size() != 0) {
            str += (tasks.size() + "." + tasks.get(tasks.size() - 1));
        }
        return str;
    }

    /**
     * Marks the task in the index-th position of the tasks array
     * as done.
     *
     * @param index the position of the task in the tasks array
     * @throws DukeException if index exceeds the size of the tasks array
     *     or if index is less than or equal to 0
     */
    public void markAsDone(int index) throws DukeException {
        if (index < 1 || index > this.tasks.size()) {
            throw new DukeOutOfRangeException(this.tasks.size());
        }
        assert(index > 1 && index <= this.tasks.size());
        Task task = this.tasks.get(index - 1);
        task.markAsDone();
    }

    /**
     * Marks the task in the index-th position of the tasks array
     * as not done.
     *
     * @param index the position of the task in the tasks array
     * @throws DukeException if index exceeds the size of the tasks array
     *     or if index is less than or equal to 0
     */
    public void markAsNotDone(int index) throws DukeException {
        if (index < 1 || index > this.tasks.size()) {
            throw new DukeOutOfRangeException(this.tasks.size());
        }
        assert(index > 1 && index <= this.tasks.size());
        Task task = this.tasks.get(index - 1);
        task.markAsNotDone();
    }

    /**
     * Adds a task to the ArrayList of tasks
     *
     * @param task the task to be added to the list of tasks
     */
    public void add(Task task) {
        assert(task != null);
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the tasks array
     *
     * @param index the position of the task in the tasks array
     * @throws DukeException if index exceeds the size of the tasks array
     *      or if index is less than or equal to 0
     */
    public void deleteTask(int index) throws DukeException {
        if (index < 1 || index > this.tasks.size()) {
            throw new DukeOutOfRangeException(this.tasks.size());
        }
        assert(index > 1 && index <= this.tasks.size());
        Task task = this.tasks.get(index - 1);;
        this.tasks.remove(index - 1);
    }

    /**
     * Shows the number of tasks in the TaskList
     *
     * @return the number of tasks in the TaskList
     */
    public String showNumberOfTasks() {
        return "\nNow you have " + this.tasks.size() + " tasks in the list.";
    }

    /**
     * Retrieves the task in the index-th position of the tasks array
     *
     * @param index the position of the task in the tasks array
     * @return the task in the index-th position of the tasks array
     * @throws DukeException if index exceeds the size of the tasks array
     *      or if index is less than or equal to 0
     */
    public String getTask(int index) throws DukeException {
        if (index < 1 || index > this.tasks.size()) {
            throw new DukeOutOfRangeException(this.tasks.size());
        }
        assert(index > 1 && index <= this.tasks.size());
        assert(tasks.get(index - 1) != null);
        return this.tasks.get(index - 1).toString();
    }

    /**
     * Returns the tasks ArrayList
     *
     * @return the tasks ArrayList
     */
    public ArrayList<Task> toArrayList() {
        assert(tasks != null);
        return tasks;
    }

    /**
     * Shows a list that is filtered by the keyword
     */
    public String showFilteredList(String keyword) {
        ArrayList<Task> filteredList = filterList(keyword);
        String result = changeListToString(filteredList);
        if (result == "") {
            return String.format("Your %s keyword does not match any entries!", keyword);
        }
        return result;
    }

    private ArrayList<Task> filterList(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).checkIfContains(keyword)) {
                filteredTasks.add(tasks.get(i));
            }
        }
        return filteredTasks;
    }

    private String changeListToString(ArrayList<? extends Task> tasks) {
        String str = "";
        for (int i = 0; i < tasks.size() - 1; i++) {
            str += ((i + 1) + "." + tasks.get(i) + "\n");
        }
        if (tasks.size() != 0) {
            str += (tasks.size() + "." + tasks.get(tasks.size() - 1));
        }
        return str;
    }

    private String showListInRange(RangeOperation op) {
        String str = "";
        int startRange = op.getStartRange();
        int endRange = op.getEndRange();
        int indexStart = startRange - 1;
        int counter = 1;
        for (int i = indexStart; i < endRange - 1; i++) {
            str += (counter + "." + tasks.get(i) + "\n");
            counter++;
        }
        if (tasks.size() != 0) {
            str += (counter + "." + tasks.get(endRange - 1));
        }
        return str;
    }

    public String commandBasedOnMassOperation(String commandWord, MassOperation op) throws DukeException {
        switch (commandWord) {
        case "mark":
            return markBasedOnMassOperation(op);
        case "unmark":
            return unmarkBasedOnMassOperation(op);
        case "delete":
            return deleteBasedOnMassOperation(op);
        default:
            throw new DukeInvalidCommandException();
        }
    }

    public String markBasedOnMassOperation(MassOperation op) throws DukeException {
        if (op instanceof AllOperation) {
            markAll();
            return showList();
        } else if (op instanceof RangeOperation) {
            RangeOperation rangeOp = (RangeOperation) op;
            markRange(rangeOp);
            return showListInRange(rangeOp);
        } else if (op instanceof SingleOperation) {
            SingleOperation singleOp = (SingleOperation) op;
            return markSingle(singleOp);
        } else {
            throw new DukeException("Operation not defined");
        }
    }

    private void markAll() throws DukeException {
        for (int i = 1; i <= tasks.size(); i++) {
            markAsDone(i);
        }
    }

    private void markRange(RangeOperation op) throws DukeException {
        int startRange = op.getStartRange();
        int endRange = op.getEndRange();
        for (int i = startRange; i <= endRange; i++) {
            markAsDone(i);
        }
    }

    private String markSingle(SingleOperation op) throws DukeException {
        int index = op.getIndex();
        markAsDone(index);
        return getTask(index);
    }

    public String unmarkBasedOnMassOperation(MassOperation op) throws DukeException {
        if (op instanceof AllOperation) {
            unmarkAll();
            return showList();
        } else if (op instanceof RangeOperation) {
            RangeOperation rangeOp = (RangeOperation) op;
            unmarkRange(rangeOp);
            return showListInRange(rangeOp);
        } else if (op instanceof SingleOperation) {
            SingleOperation singleOp = (SingleOperation) op;
            return unmarkSingle(singleOp);
        } else {
            throw new DukeException("Operation not defined");
        }
    }

    private void unmarkAll() throws DukeException {
        for (int i = 1; i <= tasks.size(); i++) {
            markAsNotDone(i);
        }
    }

    private void unmarkRange(RangeOperation op) throws DukeException {
        int startRange = op.getStartRange();
        int endRange = op.getEndRange();
        for (int i = startRange; i <= endRange; i++) {
            markAsNotDone(i);
        }
    }

    private String unmarkSingle(SingleOperation op) throws DukeException {
        int index = op.getIndex();
        markAsDone(index);
        return getTask(index);
    }

    public String deleteBasedOnMassOperation(MassOperation op) throws DukeException {
        if (op instanceof AllOperation) {
            return deleteAll();
        } else if (op instanceof RangeOperation) {
            RangeOperation rangeOp = (RangeOperation) op;
            return deleteRange(rangeOp);
        } else if (op instanceof SingleOperation) {
            SingleOperation singleOp = (SingleOperation) op;
            return deleteSingle(singleOp);
        } else {
            throw new DukeException("Operation not defined");
        }
    }

    private String deleteAll() {
        String deletedTasks = showList();
        tasks = new ArrayList<>();
        return deletedTasks;
    }

    private String deleteRange(RangeOperation op) throws DukeException {
        ArrayList<Task> deletedTasks = new ArrayList<>();
        int startRange = op.getStartRange();
        int endRange = op.getEndRange();
        for (int i = startRange; i <= endRange; i++) {
            Task t = tasks.get(startRange - 1);
            deletedTasks.add(t);
            deleteTask(startRange);
        }
        return changeListToString(deletedTasks);
    }

    private String deleteSingle(SingleOperation op) throws DukeException {
        int index = op.getIndex();
        Task t = tasks.get(index - 1);
        deleteTask(index);
        return t.toString();
    }
}
