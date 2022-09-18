package candice.task;

import candice.Storage;

import java.io.BufferedReader;
import java.io.IOException;

import java.nio.file.Files;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ArrayList;

/**
 * Represents a list of tasks that is associated to a Storage object.
 * The list of tasks can be updated by parsing the text file in the Storage object.
 */
public class TaskList {
    /** The storage object that will be used for parsing to update the task array list */
    private final Storage storage;
    /** The array task list used to store the tasks */
    private final ArrayList<Task> taskArrayList = new ArrayList<>();

    /**
     * Constructor for a TaskList object with an empty list of tasks that can be filled in by using the Storage object
     * provided.
     *
     * @param storage The storage object encapsulating the task list text needed for editing this instance of TaskList.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
    }

    /**
     * Parses the task list text using the Storage object to add tasks to the task list.
     */
    public void parseTaskListText() {
        try {
            BufferedReader reader = Files.newBufferedReader(this.storage.getPath());
            String nextLine = reader.readLine();
            Task nextTask;

            while (nextLine != null) {
                String[] taskDescription = nextLine.split(", ");
                boolean isFinished = taskDescription[1].equals("finished");
                String taskName = taskDescription[2];

                if (taskDescription[0].equals("[T]")) { // Todo task
                    nextTask = new Task.ToDo(taskName);
                } else { // Timed task
                    String dateAsString = taskDescription[3];
                    LocalDate date = LocalDate.parse(dateAsString);

                    if (taskDescription[0].equals("[D]")) { // Deadline task
                        String deadlineTimeAsString = taskDescription[4];
                        LocalTime deadlineTime = deadlineTimeAsString.equals("no time given")
                                ? null
                                : LocalTime.parse(deadlineTimeAsString);
                        nextTask = new TimedTask.Deadline(taskName, date, deadlineTime);
                    } else { // Event task
                        LocalTime eventStartTime;
                        LocalTime eventEndTime;

                        if (taskDescription[4].equals("no time given")) {
                            eventStartTime = null;
                            eventEndTime = null;
                        } else {
                            eventStartTime = LocalTime.parse(taskDescription[4]);
                            eventEndTime = LocalTime.parse(taskDescription[5]);
                        }

                        nextTask = new TimedTask.Event(taskName, date, eventStartTime, eventEndTime);
                    }
                }

                if (isFinished) {
                    nextTask.setFinished();
                }

                this.taskArrayList.add(nextTask);

                nextLine = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Returns the number of tasks inside the task list encapsulated by this TaskList object.
     *
     * @return The number of tasks inside the task list.
     */
    public int getLength() {
        return this.taskArrayList.size();
    }

    /**
     * Adds a task to the list of tasks in this TaskList object.
     *
     * @param newTask The task to add into the list of tasks.
     */
    public void addTask(Task newTask) {
        taskArrayList.add(newTask);
        String taskDescription = newTask.getStorageDescription();
        this.storage.addTask(taskDescription);
    }

    /**
     * Checks if the task number given exists within the task list.
     *
     * @param taskNumber The task number to check.
     * @throws IllegalArgumentException If the task number does not exist within this task list.
     */
    public void checkValidTaskNumber(int taskNumber) throws IllegalArgumentException {
        if (taskNumber <= 0 || taskNumber > this.getLength()) {
            throw new IllegalArgumentException("The task number you put is wrong bro. The task must exist for " +
                    "you to delete it.");
        }
    }

    /**
     * Removes the task corresponding to the task number given from the task list.
     *
     * @param taskNumber The number of the task to be removed.
     * @return The task that was deleted.
     * @throws IllegalArgumentException If the task number does not exist within this task list.
     */
    public Task deleteTask(int taskNumber) throws IllegalArgumentException {
        checkValidTaskNumber(taskNumber);
        Task removedTask = taskArrayList.remove(taskNumber - 1);
        updateStorage();
        return removedTask;
    }

    /**
     * Marks the task corresponding to the task number given as finished.
     *
     * @param taskNumber The number of the task to be marked as finished.
     * @return The task that was marked as finished.
     * @throws IllegalArgumentException If the task number does not exist within this task list.
     */
    public Task markTask(int taskNumber) throws IllegalArgumentException {
        checkValidTaskNumber(taskNumber);
        Task selectedTask = taskArrayList.get(taskNumber - 1);
        selectedTask.setFinished();
        updateStorage();
        return selectedTask;
    }

    /**
     * Marks the task corresponding to the task number given as unfinished.
     *
     * @param taskNumber The number of the task to be marked as unfinished.
     * @return The task that was marked as unfinished.
     * @throws IllegalArgumentException If the task number does not exist within this task list.
     */
    public Task unmarkTask(int taskNumber) throws IllegalArgumentException {
        checkValidTaskNumber(taskNumber);
        Task selectedTask = this.taskArrayList.get(taskNumber - 1);
        selectedTask.setUnfinished();
        updateStorage();
        return selectedTask;
    }

    /**
     * Returns a String representing all the tasks that contain the keyword in the task name.
     *
     * @param taskKeyword The keyword that is inside the name of the tasks.
     * @return A String containing all the tasks that contain the keyword.
     */
    public String findTasks(String taskKeyword) {
        StringBuilder taskList = new StringBuilder();
        int taskNumber = 1;

        for (int i = 0; i < this.getLength(); i++) {
            Task task = this.taskArrayList.get(i);
            String taskStatus = task.getStatus();

            if (taskStatus.contains(taskKeyword)) {
                taskList.append(taskNumber).append(".").append(taskStatus).append("\n");
                taskNumber++;
            }
        }

        return taskList.length() != 0
               ? taskList.substring(0, taskList.length() - 1) // to remove the last "\n"
               : taskList.toString();
    }

    /**
     * Updates the task list text encapsulated by the Storage object in this instance of TaskList to match the task
     * list in this instance of TaskList.
     */
    private void updateStorage() {
        int taskListLength = this.getLength();
        String[] taskDescriptionArray = new String[taskListLength];
        for (int i = 0; i < taskListLength; i++) {
            taskDescriptionArray[i] = this.taskArrayList.get(i).getStorageDescription();
        }
        this.storage.update(taskDescriptionArray);
    }

    /**
     * Uses the task list in this instance of TaskList to create a String representing the statuses of all the tasks in
     * the task list using the getStatus method.
     *
     * @return A String representation of all the tasks in this task list.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < this.getLength(); i++) {
            Task task = this.taskArrayList.get(i);
            String taskNumber = String.valueOf(i + 1);
            output.append(taskNumber).append(".").append(task.getStatus()).append("\n");
        }

        return output.length() != 0
               ? output.substring(0, output.length() - 1) // to remove the last "\n"
               : output.toString();
    }
}
