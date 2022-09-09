package seedu.duke;

import java.time.LocalDate;

import java.util.ArrayList;

/**
 * Represents a list of tasks for Duke.
 */
public class TaskList {

    public static ArrayList<Task> taskList;

    /**
     * Creates a list of tasks for Duke.
     *
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Represents a list of tasks for Duke.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns ArrayList<Task> of tasks of TaskList object.
     *
     * @return task list.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }


    /**
     * Adds Task to this.tasklist.
     *
     * @param taskType Either a "todo", "deadline" or "event"
     * @param input User input
     * @throws DukeException
     */
    public static String addTask(String taskType, String input) throws DukeException {
        String addLine = "Got it. I've added this task:";
        switch (taskType) {
            case "todo":
                addToDo(input, addLine);
                break;
            case "deadline":
                addDeadline(input, addLine);
                break;
            case "event":
                addEvent(input, addLine);
                break;

        }
        // edge case of 1 task
        addLine += String.format("\nNow you have %s tasks in the list.", taskList.size());
        return addLine;

    }


    private static void addToDo(String input, String addLine) throws DukeException {
        String[] removeTaskType = input.split("todo ");
        String description = String.join("", removeTaskType);
        if (description.equals("todo")) {
            throw new DukeException("");
        }
        Task todo = new ToDo(description);
        taskList.add(todo);
        addLine += ("\n  " + todo);
    }

    private static void addDeadline(String input, String addLine) {
        String[] removeTaskType2 = input.split("deadline ");
        String desAndBy = String.join("", removeTaskType2);
        String[] sliceByDesAndBy = desAndBy.split(" /by ");
        String description2 = sliceByDesAndBy[0];
        String dueDateAndTime = sliceByDesAndBy[1];
        String[] dateAndTime = dueDateAndTime.split(" ");
        System.out.println(dateAndTime.length);
        if (dateAndTime.length == 2) {
            String dueDate = dateAndTime[0];
            String dueTime = dateAndTime[1];
            LocalDate localDate = Storage.getLocalDate(dueDate);
            Task deadline = new Deadline(description2, localDate, dueTime);
            taskList.add(deadline);
            addLine += ("\n  " + deadline);
        } else {
            String dueDate = dateAndTime[0];
            LocalDate localDate = Storage.getLocalDate(dueDate);
            Task deadline = new Deadline(description2, localDate);
            taskList.add(deadline);
            addLine += ("\n  " + deadline);
        }
    }

    private static void addEvent(String input, String addLine) {
        String[] removeTaskType3 = input.split("event ");
        String desAndBy2 = String.join("", removeTaskType3);
        String[] sliceByDesAndBy2 = desAndBy2.split(" /at ");
        String description3 = sliceByDesAndBy2[0];
        String dueTime2 = sliceByDesAndBy2[1];
        Task event = new Event(description3, dueTime2);
        taskList.add(event);
        addLine += ("\n  " + event);
    }

    /**
     * Removes tasks at ith index of this.tasklist, assuming the this.tasklist starts from index 1.
     *
     * @param num index of task in this.tasklist.
     */
    public String deleteTask(int num) {
        String deleteLine = "Noted. I've removed this task:";
        deleteLine += "\n  " + this.taskList.get(num - 1);
        this.taskList.remove(num - 1);
        deleteLine += String.format("\nNow you have %s tasks in the list.", this.taskList.size());
        return deleteLine;
    }

    /**
     * Marks Task at ith index of this.tasklist as done, assuming the this.tasklist starts from index 1.
     *
     * @param num index of task in this.tasklist.
     */
    public void markTask(int num) {
        Task task = this.taskList.get(num - 1);
        task.mark();
    }

    /**
     * Marks Task at ith index of this.tasklist as undone, assuming the this.tasklist starts from index 1.
     *
     * @param num index of task in this.tasklist.
     */
    public void unmarkTask(int num) {
        Task task = this.taskList.get(num - 1);
        task.unmark();
    }

    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.getTaskList().get(i);
            String taskDescription = task.getDescription();
            if (taskDescription.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Prints tasks in data/duke.txt when Duke is initialized.
     */
    public String printList() {
        String list = "Here are the tasks in your list:";
        for (int i = 0; i < this.taskList.size(); i++) {
            list += String.format("\n%s. %s", i + 1, this.taskList.get(i));
        }
        return list;
    }
}
