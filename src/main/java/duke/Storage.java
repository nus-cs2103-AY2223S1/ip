package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
     * @throws DukeException         If the file does not have the correct format.
     */
    public ArrayList<Task> loadTasks() throws IOException, DukeException {
        ArrayList<Task> userTasks = new ArrayList<>();
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
                    Task savedTodo = new Todo(taskDescription);
                    if (isTaskDone) {
                        savedTodo.setCompleted();
                    }
                    userTasks.add(savedTodo);
                    break;
                case "D":
                    LocalDate taskDeadline = LocalDate.parse(parsedTaskTextRepresentation[3]);
                    Task savedDeadline = new Deadline(taskDescription, taskDeadline);
                    if (isTaskDone) {
                        savedDeadline.setCompleted();
                    }
                    userTasks.add(savedDeadline);
                    break;
                case "E":
                    String taskEventTime = parsedTaskTextRepresentation[3];
                    Task savedEvent = new Event(taskDescription, taskEventTime);
                    if (isTaskDone) {
                        savedEvent.setCompleted();
                    }
                    userTasks.add(savedEvent);
                    break;
                default:
                    throw new DukeException("Corrupted task file");
            }
        }
        return userTasks;
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
