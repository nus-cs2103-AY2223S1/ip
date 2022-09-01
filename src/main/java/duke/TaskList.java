package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDos;

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
        return taskList.get(index).mark();
    }

    /** Sets isComplete status of task at index to be incomplete
     * @param index index of task in the list
     * @throws IndexOutOfBoundsException
     */
    public String incomplete(int index) throws IndexOutOfBoundsException {
        if (index >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        return taskList.get(index).unmark();
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
            Task currentTask = taskList.get(i);
            if (currentTask != null){
                response += String.format("%d. %s\n", i + 1, currentTask);
            }
        }
        return response;
    }

    /**
     * Deletes a duke.task.Task
     * @param index index to delete
     * @Throws IndexOutOfBoundsException
     */
    public String delete(int index) throws IndexOutOfBoundsException {
        if (index >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        else {
            Task tempTask = taskList.get(index);
            taskList.remove(index);
            return "Noted. I've removed this task:/n" + tempTask;
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
            String[] details = line.split(" # ");
            String taskType = details[0];
            boolean isComplete = Boolean.parseBoolean(details[1]);
            String taskName = details[2];
            String eventInfo = details.length > 3 ? details[3] : "";

            Task task = createTaskFromString(taskType, taskName, eventInfo, isComplete);
            if (task == null) {
                throw new Exception("Unable to load task");
            }
            taskList.add(task);
        }
    }

    public Task createTaskFromString(String taskType, String taskName, String eventInfo, boolean isComplete) {
        switch (taskType) {
            case "T":
                return new ToDos(taskName, isComplete);
            case "D":
                return new Deadline(taskName, eventInfo, isComplete);
            case "E":
                return new Event(taskName, eventInfo, isComplete);
        }

        return null;
    }

    
    /** Returns todolist formatted for saving in duke.Duke.txt file in hard disk
     * @return String of tasks in todolist
     */
    public String createTxtFile() {
        String result = "";
        for (Task t : taskList) {
            if (t instanceof ToDos) {
                ToDos td = (ToDos) t;
                result += String.format("T # %b # %s\n", td.getStatus(), td.getName());
            } 
            else if (t instanceof Deadline){
                Deadline d = (Deadline) t;
                result += String.format("D # %b # %s # %s\n", d.getStatus(), d.getName(), d.getDeadline());
            }
            else if (t instanceof Event) {
                Event e = (Event) t;
                result += String.format("E # %b # %s # %s\n", e.getStatus(), e.getName(), e.getTime());
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
     * @param searchString
     */
    public String findTasks(String searchString) {
        int findCount = 0;
        String response = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
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
}
