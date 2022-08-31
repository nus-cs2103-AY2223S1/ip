package duke;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * TaskList encapsulates a list of tasks and the operations that can be performed on these tasks.
 *
 * @author Eugene Tan
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor of TaskList with saved tasks read in.
     *
     * @param tasks
     */
    public TaskList(ArrayList<String> tasks) {
        this.taskList = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            taskList.add(Task.loadStringToTask(tasks.get(i)));
        }
    }

    /**
     * Gets the size of the list.
     *
     * @return Current size of list.
     */
    public int getSize() {

        return this.taskList.size();
    }

    /**
     * Marks task at given index as completed.
     *
     * @param index The index to mark.
     * @return String representation of marked task.
     */
    public String markTask(int index) {
        this.taskList.get(index - 1).markDone();
        return this.taskList.get(index - 1).toString();
    }

    /**
     * Unmarks task at given index as not completed.
     *
     * @param index The index to unmark.
     * @return String representation of  unmarked task.
     */
    public String unMarkTask(int index) {
        this.taskList.get(index - 1).markUndone();
        return this.taskList.get(index - 1).toString();
    }

    /**
     * Adds a new  ToDo into the list.
     *
     * @param description ToDo description.
     * @return String representation of the ToDo.
     */
    public String addToDo(String description) {
        ToDo todo = new ToDo(description);
        taskList.add(todo);
        return todo.toString();
    }

    /**
     * Converts task in list to data to be saved.
     *
     * @return List contating data (in String format) to be saved.
     */
    public ArrayList<String> getTaskListInString() {
        ArrayList<String> taskListInString = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            taskListInString.add(taskList.get(i).saveStringFormat());
        }
        return taskListInString;
    }

    /**
     * Adds a new  Deadline into the list.
     *
     * @param description Deadline description.
     * @param returnBy The date of deadline.
     * @return String representation of deadline.
     */
    public String addDeadline(String description, LocalDate returnBy) {
        Deadline deadline = new Deadline(description, returnBy);
        taskList.add(deadline);
        return deadline.toString();
    }

    /**
     * Adds a new  Event into the list.
     *
     * @param description Event decscription
     * @param at The date of the event.
     * @return String representation of the Event.
     */
    public String addEvent(String description, LocalDate at) {
        Event event = new Event(description, at);
        taskList.add(event);
        return event.toString();
    }

    /**
     * Deletes the task in the list.
     *
     * @param index The index of the task in the list to be deleted
     * @return String representation of deleted task.
     */
    public String deleteTask(int index) {
        Task toDelete = taskList.remove(index - 1);
        return toDelete.toString();
    }

    /**
     * Finds and returns a list of tasks that contains the given wordToFind.
     *
     * @param wordsToFind The keyword to find in the TaskList.
     * @return String representation of the list containing the tasks found.
     */
    public String findTasks(String ...wordsToFind) {
        ArrayList<String> foundTasks = new ArrayList<>();
        for (Task task : taskList) {
            boolean matchAllKeywords = true;
            for (String keyword : wordsToFind) {
                if (!task.containsWord(keyword)) {
                    matchAllKeywords = false;
                    break;
                }
            }

            if (matchAllKeywords) {
                foundTasks.add(task.toString());
            }
        }

        if (foundTasks.size() == 0) {
            return "Sorry, there is no tasks with the keyword";
        } else {
            StringBuilder returnString = new StringBuilder();
            returnString.append("Here are the tasks found : ");
            for (int i = 1; i <= foundTasks.size(); i++) {
                returnString.append(String.format("%n%d.%s", i, foundTasks.get(i - 1)));
            }
            return returnString.toString();
        }
    }

    /**
     * Returns String representation of TaskList.
     *
     * @return String representation of the TaskList.
     */
    @Override
    public String toString() {
        if (this.taskList.size() == 0) {
            return "There are no tasks in your list";
        } else {
            StringBuilder str = new StringBuilder(String.format("There %s %d Bob task(s) in your list:",
                    this.taskList.size() > 1 ? "are" : "is", this.taskList.size()));
            for (int i = 1; i <= this.taskList.size(); i++) {
                str.append(String.format("%n%d. %s", i, taskList.get(i - 1)));
            }

            return str.toString();
        }
    }


}
