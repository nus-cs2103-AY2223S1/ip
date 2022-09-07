package blob.storage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import blob.exception.ErrorLoadingTaskException;
import blob.exception.InvalidDateFormatException;
import blob.exception.InvalidPriorityException;
import blob.tasks.Deadline;
import blob.tasks.Event;
import blob.tasks.Task;
import blob.tasks.TaskList;
import blob.tasks.ToDo;

/**
 * The Storage class deals with loading and saving tasks from a text file.
 */
public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Returns a TaskList based on parsing task data in the specified file path.
     *
     * @return A TaskList based on parsing task data in the specified file path
     * @throws ErrorLoadingTaskException
     */
    public TaskList loadTaskList() throws ErrorLoadingTaskException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Files.createDirectories(filePath.getParent());
            File taskFile = filePath.toFile();
            if (!taskFile.createNewFile()) {
                Scanner sc = new Scanner(new FileReader(taskFile));
                while (sc.hasNextLine()) {
                    String[] deconstructedDetails = sc.nextLine().trim().split("\\s+\\|\\s+");
                    String priority = deconstructedDetails[0];
                    String taskType = deconstructedDetails[1];
                    String done = deconstructedDetails[2];
                    String description = deconstructedDetails[3];
                    Task task = null;

                    if (Objects.equals(taskType, "T")) {
                        task = new ToDo(description, "//" + priority);
                    } else if (Objects.equals(taskType, "D")) {
                        task = new Deadline(description, deconstructedDetails[4], "//" + priority);
                    } else if (Objects.equals(taskType, "E")) {
                        task = new Event(description, deconstructedDetails[4], "//" + priority);
                    }

                    if (Objects.equals(done, "1")) {
                        assert task != null;
                        task.markAsDone();
                    }

                    taskList.add(task);
                }
            }
        } catch (IOException e) {
            throw new ErrorLoadingTaskException();
        } catch (InvalidDateFormatException e) {
            // Improper datetime, do nothing (don't add to task list)
        } catch (InvalidPriorityException e) {
            // Improper priority, do nothing (don't add to task list)
        }

        return new TaskList(taskList);
    }

    /**
     * Saves the task information of the given task list into a separate file for storage.
     *
     * @param taskList The task list that contains the tasks to be stored.
     */
    public void saveTaskList(TaskList taskList) {
        try {
            FileWriter taskFileWriter = new FileWriter(filePath.toFile());
            for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
                taskFileWriter.write(taskList.getTask(i).toFileString());
                taskFileWriter.write(System.lineSeparator());
            }
            taskFileWriter.close();
        } catch (IOException e) {
            // Do nothing
        }
    }
}
