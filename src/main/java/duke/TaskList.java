package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.List;

/**
 * duke.ToDoList class manages all the tasks created by user
 * @author Shaune Ang
 */
public class TaskList {
    ArrayList<Task> taskList;

    /**
     * Creates empty duke.ToDoList
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Creates duke.ToDoList based on saved file
     * @param txtFile List of Strings representing the tasks saved
     * @throws Exception when adding task from file fails
     */
    TaskList(List<String> txtFile) throws Exception {
        taskList = new ArrayList<>();
        addTaskFromFile(txtFile);
    }

    /** 
     * Sets isComplete status of task at index to be complete
     * @param index index of task in the list
     * @throws IndexOutOfBoundsException
     */
    public String complete(int index) throws IndexOutOfBoundsException {
        if (index >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        return getTask(index).mark();
    }

    /** Sets isComplete status of task at index to be incomplete
     * @param index index of task in the list
     * @throws IndexOutOfBoundsException
     */
    public String incomplete(int index) throws IndexOutOfBoundsException {
        if (index >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        return getTask(index).unmark();
    }

    /**
     *  Adds task to todoList
     * @param task task to be added to toDoList
     */
    public String addTask(Task task) {
        taskList.add(task);
        return "Got it. I've added this task:\n" + task;
    }

    /**
     * Displays all tasks in toDoList
     */
    public String listTasks() {
        if (taskList.size() < 1) {
            return "You don't have any pending tasks.";
        }

        String response = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = getTask(i);
            if (currentTask != null){
                response += String.format("%d. %s\n", i + 1, currentTask);
            }
        }
        assert taskList.size() >= 0 : "Task list size is negative";
        return response;
    }

    /**
     * Deletes a duke.task.Task
     * @param index index to delete
     * @Throws IndexOutOfBoundsException
     */
    public String delete(int index) throws IndexOutOfBoundsException {
        assert (index < taskList.size() && index >=0): "index needs to be within bounds of list";
        if (index >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        } else {
            Task tempTask = getTask(index);
            taskList.remove(index);
            return "Noted. I've removed this task:\n" + tempTask;
        }
    }

    /**
     * Loads all the saved tasks into list
     * @param txtFile list of tasks in String 
     */
    private void addTaskFromFile(List<String> txtFile) throws Exception {
        if (txtFile.isEmpty()) {
            return;
        }

        for (String line : txtFile) {
            Task task = createTaskFromString(line);
            if (task == null) {
                throw new Exception("Unable to load task");
            }
            taskList.add(task);
        }
    }

    /**
     *
     * @param task
     * @return
     */
    public Task createTaskFromString(String task) {

        String[] details = task.split(" # ");
        String taskType = details[0];
        boolean isComplete = Boolean.parseBoolean(details[1]);
        String taskName = details[2];
        Task.PriorityLevel priority = Task.symbolToPriority(details[3]);
        String eventInfo = details.length > 4 ? details[4] : "";

        switch (taskType) {
            case "T":
                return new ToDo(taskName, isComplete, priority);
            case "D":
                return new Deadline(taskName, eventInfo, isComplete, priority);
            case "E":
                return new Event(taskName, eventInfo, isComplete, priority);
            default:
                return null;
        }
    }

    
    /** Returns todolist formatted for saving in duke.Duke.txt file in hard disk
     * @return String of tasks in todolist
     */
    public String createTxtFile() {
        String result = "";
        for (Task t : taskList) {
            if (t instanceof ToDo) {
                ToDo td = (ToDo) t;
                result += String.format("T # %b # %s # %s\n", td.getStatus(), td.getName(), td.getPriority());
            } else if (t instanceof Deadline){
                Deadline d = (Deadline) t;
                result += String.format("D # %b # %s # %s # %s\n", d.getStatus(), d.getName(), d.getPriority(), d.getDeadline());
            } else if (t instanceof Event) {
                Event e = (Event) t;
                result += String.format("E # %b # %s # %s # %s\n", e.getStatus(), e.getName(), e.getPriority(), e.getTime());
            }
        }
        return result;
    }

    /**
     * Prints number of tasks in the list
     */
    public String displayListSize() {
        return String.format("Now you have %d tasks in the list.\n", taskList.size());
    }

    /**
     * Searches for searchString by iterating through all the names of the tasks in toDoList
     * @param searchString substring of names user is interested in
     */
    public String findTaskName(String searchString) {
        int findCount = 0;
        String response = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = getTask(i);
            if (currentTask.nameContains(searchString)){
                findCount++;
                response += String.format("%d. %s\n", findCount, currentTask);
            }
        }

        if (findCount == 0) {
            response = String.format("No tasks were found matching: %s\n", searchString);
        }
        return response;
    }

    /**
     * Searches for priority by iterating through all the prioriteis of the tasks in toDoList
     * @param priorityString priorities users are interested in
     * @return list of tasks with the same priority as specified
     */
    public String findTaskPriority(String priorityString) throws Exception {
        int findCount = 0;
        Task.PriorityLevel priority = Task.commandToPriorityLevel(priorityString);
        String response = String.format("Here are the tasks of priority %s in your list:\n", priorityString);
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = getTask(i);
            if (currentTask.hasPriority(priority)){
                findCount++;
                response += String.format("%d. %s\n", findCount, currentTask);
            }
        }

        if (findCount == 0) {
            response = String.format("No tasks were with priority: %s\n", priorityString);
        }
        return response;
    }

    /**
     * Sets priority of existing task
     * @param index index of task in list to change
     * @param priorityLevel priority level to set the task to
     * @return Response for duke to reply
     * @throws IndexOutOfBoundsException
     */
    public String setTaskPriority(int index, Task.PriorityLevel priorityLevel) throws IndexOutOfBoundsException {
        Task task = getTask(index);
        return task.setPriority(priorityLevel);
    }

    /**
     * Get Task from taskList by index
     * @param index
     * @return
     */
    public Task getTask(int index) {
        assert (index < taskList.size() && index >=0): "index needs to be within bounds of list";
        if (index >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        return taskList.get(index);
    }
}
