package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a storage system for tasks given to Duke chatbot.
 *
 * @author Conrad
 */
public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks locally from the previous session.
     *
     * @return An <code>ArrayList</code> of tasks read from the input file.
     * @throws IOException If the file does not exist.
     * @throws DukeException If the file does not have the correct format.
     */
    public List<Task> loadTasks() throws IOException, DukeException {
        List<Task> userTasks = new ArrayList<>();
        File f = new File(this.filePath);
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String taskTextRepresentation = s.nextLine();
            String[] parsedTaskTextRepresentation = taskTextRepresentation.split("\\|");
            String taskType = parsedTaskTextRepresentation[0];
            boolean isTaskDone = Integer.parseInt(parsedTaskTextRepresentation[1]) == 1;
            String taskDescription = parsedTaskTextRepresentation[2];
            switch (taskType) {
            case "T":
                userTasks.add(this.loadTodo(taskDescription, isTaskDone));
                break;
            case "D":
                LocalDate taskDeadline = LocalDate.parse(parsedTaskTextRepresentation[3]);
                userTasks.add(this.loadDeadline(taskDescription, isTaskDone, taskDeadline));
                break;
            case "E":
                String taskEventTime = parsedTaskTextRepresentation[3];
                userTasks.add(this.loadEvent(taskDescription, isTaskDone, taskEventTime));
                break;
            default:
                throw new DukeException("Corrupted task file");
            }
        }
        return userTasks;
    }

    /**
     * Loads an event task from the local storage.
     *
     * @param taskDescription The description of the event task.
     * @param isTaskDone The boolean representing whether the task is marked as done.
     * @param taskEventTime The String representing the time of occurrence of the event.
     * @return An <code>Event</code> instance.
     */
    private Task loadEvent(String taskDescription, boolean isTaskDone, String taskEventTime) {
        Task savedEvent = new Event(taskDescription, taskEventTime);
        if (isTaskDone) {
            savedEvent.setCompleted();
        }
        return savedEvent;
    }

    /**
     * Loads a deadline task from the local storage.
     *
     * @param taskDescription The description of the task.
     * @param isTaskDone The boolean representing whether the task is marked as done.
     * @param taskDeadline The LocalDate object representing the deadline of the task.
     * @return A <code>Deadline</code> instance.
     */
    private Task loadDeadline(String taskDescription, boolean isTaskDone, LocalDate taskDeadline) {
        Task savedDeadline = new Deadline(taskDescription, taskDeadline);
        if (isTaskDone) {
            savedDeadline.setCompleted();
        }
        return savedDeadline;
    }

    /**
     * Loads a task from the local storage.
     *
     * @param taskDescription The description of the task.
     * @param isTaskDone The boolean representing whether the task is marked as done.
     * @return A <code>Todo</code> instance.
     */
    private Task loadTodo(String taskDescription, boolean isTaskDone) {
        Task savedTodo = new Todo(taskDescription);
        if (isTaskDone) {
            savedTodo.setCompleted();
        }
        return savedTodo;
    }

    /**
     * Saves the tasks locally from the current session.
     *
     * @param tasks A list of tasks to be saved.
     * @throws DukeException If there is an error while accessing the file information.
     */
    public void saveTasks(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(tasks.getStorageRepresentation());
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

    }

}
