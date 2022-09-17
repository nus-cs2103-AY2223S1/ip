package Duke.FileStorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import Duke.TaskList;
import Duke.Task.DeadlineTask;
import Duke.Task.EventTask;
import Duke.Task.Task;
import Duke.Task.ToDoTask;

/**
 * This class handles the writing and retrieval of task list from
 * a given filepath.
 */
public class Storage {

    private File storageFile;
    private File parentDirectory;
    private String storageWarnings;

    /**
     * Constructs the storage for Duke.
     * Assumes the input file path to be of the form "/_dir_/_subdir_/_filename_.txt".
     * 
     * @param filepath The path to the text file that will store the task list.
     */
    public Storage (String filepath) {
        filepath = filepath.replace('/', File.separatorChar);
        storageFile = new File(filepath);
        parentDirectory = storageFile.getParentFile();
        storageWarnings = "";
    }

    /**
     * Loads the task list from the file.
     * 
     * @return The task list retrieved from the file.
     * @throws InvalidStorageException
     */
    public ArrayList<Task> load() 
            throws InvalidStorageException{
        if (!parentDirectory.exists()) {
            parentDirectory.mkdir();
        }
        if (!storageFile.exists()) {
            System.out.println("No previous list found. Creating List ...");
            try {
                storageFile.createNewFile();
            } catch (IOException e) {
                throw new InvalidStorageException("An error occurred while creating the list. "
                        + "List was not created\n" 
                        + "Storage of the list will not be persistent.");
            }
        }

        ArrayList<Task> retreivedTasks = new ArrayList<>();
        Task task = null;
        try {
            Scanner reader = new Scanner(storageFile);
            while (reader.hasNextLine()) {
                try {
                    task = parseLine(reader.nextLine());
                } catch (InvalidFileContentException e) {
                    storageWarnings += e.getMessage();
                }
                if (task != null) {
                    retreivedTasks.add(task);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new InvalidStorageException("The storage for the list is not found.");
        }

        if (!storageWarnings.isEmpty()) {
            storageWarnings = "The following lines were ignored from the file:\n" + storageWarnings;
        }
        return retreivedTasks;
    }

    /**
     * Parses the given line into a task for Duke. The line of data should be
     * given in the format of [_TASK_TYPE_|_marked_|_description_|___], delimited
     * by "|".
     * 
     * @param dataline The task to be parsed.
     * @return The task parsed from the line given.
     * @throws InvalidFileContentException
     */
    private static Task parseLine(String dataline) throws InvalidFileContentException {
        String[] data = dataline.split("\\|");
        try {
            String taskType = data[0];
            boolean isMarked = data[1].equals("Y") ? true : false;
            switch (taskType) {
            case "T":
                return new ToDoTask(data[2], isMarked);
            case "D":
                return new DeadlineTask(data[2], LocalDateTime.parse(data[3]), isMarked);
            case "E":
                return new EventTask(data[2], LocalDateTime.parse(data[3]), 
                        LocalDateTime.parse(data[4]), isMarked);
            default:
                throw new InvalidFileContentException(
                        String.format("    [Line] %s (unknown task type)\n", dataline));
            }
        } catch (DateTimeParseException e) {
            throw new InvalidFileContentException(String.format("    [Line] %s (wrong time format)\n", dataline));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidFileContentException(String.format("    [Line] %s (missing data)\n", dataline));
        }   
    }

    /**
     * Returns the lines skipped during retrieval of tasks from the file.
     * 
     * @return The list of lines skipped.
     */
    public String getWarnings() {
        return this.storageWarnings;
    }

    /**
     * Writes the tasks list to file.
     * 
     * @param tasks The list of tasks to be written to the file.
     */
    public boolean writeListToFile(TaskList tasks) {
        try {
            ArrayList<Task> taskList= tasks.getList();
            assert taskList != null : "taskList should at most be empty and not null";
            FileWriter listWriter = new FileWriter(storageFile);
            for (Task task : taskList) {
                listWriter.write(task.encodeForStorage() + "\n");
            }
            listWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return true;
    }
    
}
