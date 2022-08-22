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

    public List<Task> load() throws TextNoMeaningException {
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
            System.out.println("  Unable to detect file: " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("  Error parsing data from data file.");
        }
        throw new TextNoMeaningException("  Unable to load storage.");
    }

    public void append(String textToAdd) {
        File file = new File(this.filePath);
        this.createFileIfNecessary(file);

        // Append to the file
        try {
            FileWriter fw = new FileWriter(this.filePath, true);
            fw.write(textToAdd + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private void write(String textToWrite) {
        File file = new File(this.filePath);
        this.createFileIfNecessary(file);

        // Write to the file
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(textToWrite + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    // If the file does not yet exist, we create all the necessary parent directories and file.
    private void createFileIfNecessary(File file) {
        if (!file.exists()) {
            // If the file doesn't exist yet, mkdirs() will assume everything specified is a
            // directory and creates it as such. By using getParentFile(), we leave the creation of
            // the file itself to createNewFile().
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error with creating a file at specified path name: " + e.getMessage());
            }
        }
    }

    // Re-write the entire data file
    public void reWriteDataFile(TaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            if (i == 0) {
                this.write(taskList.getTask(i).toString());
            } else {
                this.append(taskList.getTask(i).toString());
            }
        }
    }
}
