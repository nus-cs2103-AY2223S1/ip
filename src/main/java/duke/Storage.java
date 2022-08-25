package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws FileNotFoundException, DukeException {
        ArrayList<Task> userTasks = new ArrayList<>();
        File f = new File(this.filePath);
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

    public void saveTasks(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(taskList.getStorageRepresentation());
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

    }

}
