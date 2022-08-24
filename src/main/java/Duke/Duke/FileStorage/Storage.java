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

public class Storage {

    private File storageFile;
    private File parentDirectory;
    private String storageWarnings;

    /**
     * Constructs the storage for Duke.
     * Assumes the input file path to be of the form "/_dir_/_subdir_/_filename_.txt".
     * 
     * @param filepath The path to the text file that will store the todo list.
     */
    public Storage (String filepath) {
        filepath = filepath.replace('/', File.separatorChar);
        storageFile = new File(filepath);
        parentDirectory = storageFile.getParentFile();
        storageWarnings = "";
    }

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
        System.out.println(retreivedTasks);
        return retreivedTasks;
    }

    private static Task parseLine(String dataline) throws InvalidFileContentException {
        String[] data = dataline.split("\\|");
        try {
            String taskType = data[0];
            boolean isMarked = data[1].equals("Y") ? true : false;
            System.out.println(data[1].equals("Y"));
            switch (taskType) {
            case "T":
                return new ToDoTask(data[2], isMarked);
            case "D":
                return new DeadlineTask(data[2], LocalDateTime.parse(data[3]), isMarked);
            case "E":
                return new EventTask(data[2], LocalDateTime.parse(data[3]), 
                        LocalDateTime.parse(data[4]), isMarked);
            default:
                throw new InvalidFileContentException(String.format("    [Line] %s (unknown task type)\n", dataline));
            }
        } catch (DateTimeParseException e){
            throw new InvalidFileContentException(String.format("    [Line] %s (wrong time format)\n", dataline));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidFileContentException(String.format("    [Line] %s (missing data)\n", dataline));
        }   
    }

    public String getWarnings() {
        return this.storageWarnings;
    }

    public void writeListToFile(TaskList tasks) {
        try {
            ArrayList<Task> taskList= tasks.getList();
            FileWriter listWriter = new FileWriter(storageFile);
            for (Task task : taskList) {
                listWriter.write(task.encodeForStorage() + "\n");
            }
            listWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
}
