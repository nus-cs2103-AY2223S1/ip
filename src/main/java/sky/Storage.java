package sky;

import sky.exception.TextNoMeaningException;
import sky.task.Deadline;
import sky.task.Event;
import sky.task.Task;
import sky.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Storage class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a list of tasks stored in the task file in user's hard disk.
     *
     * @return A list of tasks.
     * @throws TextNoMeaningException If unable to read from file.
     */
    public List<Task> load() throws IOException {
        File file = new File(this.filePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try {
            List<Task> taskList = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String textLine = scanner.nextLine();
                char taskType = textLine.charAt(1);
                boolean isMarked = textLine.charAt(4) == 'X';
                String description = textLine.substring(7);

                if (taskType == 'T') {
                    Task task = new ToDo(description);
                    taskList.add(task);
                } else if (taskType == 'D') {
                    int indexOfBracket = -1;
                    for (int i = 0; i < description.length(); i++) {
                        if (description.charAt(i) == '(') {
                            indexOfBracket = i;
                        }
                    }
                    String taskDescription = description.substring(0, indexOfBracket - 1);
                    String taskBy = description.substring(indexOfBracket + 5, description.length() - 1);
                    Task task = new Deadline(taskDescription, taskBy);
                    taskList.add(task);
                } else {
                    int indexOfBracket = -1;
                    for (int i = 0; i < description.length(); i++) {
                        if (description.charAt(i) == '(') {
                            indexOfBracket = i;
                        }
                    }
                    String taskDescription = description.substring(0, indexOfBracket - 1);
                    String taskDuration = description.substring(indexOfBracket + 5, description.length() - 1);
                    Task task = new Event(taskDescription, taskDuration);
                    taskList.add(task);
                }
                if (isMarked) {
                    taskList.get(taskList.size() - 1).markAsDone();
                }
            }
            return taskList;
        } catch (FileNotFoundException e) {
            throw new IOException("Unable to detect file: " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new IOException("Error parsing data from data file.");
        }
    }

    /**
     * Adds the provided text into the task file in user's hard disk.
     *
     * @param textToAdd Text to be added.
     */
    public void append(String textToAdd) throws IOException {
        File file = new File(this.filePath);
        this.createFileIfNecessary(file);

        // Append to the file
        FileWriter fw = new FileWriter(this.filePath, true);
        fw.write(textToAdd + "\n");
        fw.close();
    }

    private void write(String textToWrite) throws IOException {
        File file = new File(this.filePath);
        this.createFileIfNecessary(file);

        // Write to the file
        FileWriter fw = new FileWriter(this.filePath);
        fw.write(textToWrite + "\n");
        fw.close();
    }

    // If the file does not yet exist, we create all the necessary parent directories and file.
    private void createFileIfNecessary(File file) throws IOException {
        if (!file.exists()) {
            // If the file doesn't exist yet, mkdirs() will assume everything specified is a
            // directory and creates it as such. By using getParentFile(), we leave the creation of
            // the file itself to createNewFile().
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    /**
     * Re-writes the task file in user's hard disk with specified tasks.
     *
     * @param taskList A list of tasks that will replace the tasks in user's hard disk.
     */
    public void reWriteDataFile(TaskList taskList) throws IOException {
        for (int i = 0; i < taskList.getSize(); i++) {
            if (i == 0) {
                this.write(taskList.getTask(i).toString());
            } else {
                this.append(taskList.getTask(i).toString());
            }
        }
    }
}
