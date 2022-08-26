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
public class ToDoList {
    ArrayList<Task> toDoList;

    /**
     * Creates empty duke.ToDoList
     */
    public ToDoList() {
        toDoList = new ArrayList<>();
    }

    /**
     * Creates duke.ToDoList based on saved file
     * @param txtFile List of Strings representing the tasks saved
     * @throws Exception when adding task from file fails
     */
    ToDoList(List<String> txtFile) throws Exception {
        toDoList = new ArrayList<>();
        addTaskFromFile(txtFile);
    }

    /** 
     * Sets isComplete status of task at index to be complete
     * @param index index of task in the list
     * @throws IndexOutOfBoundsException
     */
    public void complete(int index) throws IndexOutOfBoundsException {
        if (index >= toDoList.size()) {
            throw new IndexOutOfBoundsException();
        }
        toDoList.get(index).mark();
    }

    /** Sets isComplete status of task at index to be incomplete
     * @param index index of task in the list
     * @throws IndexOutOfBoundsException
     */
    public void incomplete(int index) throws IndexOutOfBoundsException {
        if (index >= toDoList.size()) {
            throw new IndexOutOfBoundsException();
        }
        toDoList.get(index).unmark();
    }

    /**
     *  Adds task to todoList
     * @param task task to be added to toDoList
     */
    public void addTask(Task task) {
        toDoList.add(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
    }

    /**
     * Displays all tasks in toDoList
     */
    public void listTasks() {
        if (toDoList.size() < 1) {
            System.out.println("\tYou don't have any pending tasks.");
            return;
        }

        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < toDoList.size(); i++) {
            if (toDoList.get(i) != null){
                System.out.printf("\t%d. %s\n", i + 1, toDoList.get(i));
            }
        }
    }

    /**
     * Deletes a duke.task.Task
     * @param index index to delete
     * @Throws IndexOutOfBoundsException
     */
    public void delete(int index) throws IndexOutOfBoundsException {
        if (index >= toDoList.size()) {
            throw new IndexOutOfBoundsException();
        }
        else {
            Task tempTask = toDoList.get(index);
            toDoList.remove(index);
            System.out.println("\tNoted. I've removed this task:");
            System.out.println("\t  " + tempTask);
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
            toDoList.add(task);
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
        for (Task t : toDoList) {
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
    public void displayListSize() {
        System.out.printf("\tNow you have %d tasks in the list.\n", toDoList.size());
    }
}
