package duke;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
/**
 * Represents the storage for the list task managed by the ChatBot.
 */
public class Storage {

    private String fileLocation;

    /**
     * Create storage with this filePath.
     * @param filePath.
     */
    Storage(String filePath) {
        this.fileLocation = filePath;
    }

    /**
     * Check whether the file in the filePath exists or not.
     * @return true or false.
     */
    boolean checkFile() {
        String base = System.getProperty("user.dir");
        java.nio.file.Path path =  java.nio.file.Paths.get(base, fileLocation);
        boolean fileExists = java.nio.file.Files.exists(path);
        return fileExists;
    }

    /**
     * Create new file.
     * @throws Exception.
     */
    void createFiles() throws Exception {
        File taskFile = new File(fileLocation);
        taskFile.getParentFile().mkdirs();
        taskFile.createNewFile();
    }

    /**
     * Access the file.
     * If file does not exist, create new file at the fileLocation.
     * If file exists, read the file and add all the existing tasks into a taskList.
     * @return taskList.
     * @throws Exception.
     */
    List<Task> readFiles() throws Exception {
        List<Task> existingTaskList = new ArrayList<Task>();
        if (this.checkFile() == true) {
            FileReader file = new FileReader(fileLocation);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine() ) {
                String task = sc.nextLine();
                String[] strarr = task.split(":");
                String typeOfTask = strarr[1];
                String statusOfTask = strarr[2];
                String taskDescription = strarr[3];
                if (typeOfTask.equals("T")) {
                    Task pastTask = new ToDo(taskDescription);
                    if (statusOfTask.equals("X")) {
                        pastTask.markAsDone();
                    }
                    existingTaskList.add(pastTask);
                } else if (typeOfTask.equals("D")) {
                    String dateOfTask = strarr[4];
                    Task pastTask = new Deadline(taskDescription, dateOfTask);
                    if (statusOfTask.equals("X")) {
                        pastTask.markAsDone();
                    }
                    existingTaskList.add(pastTask);
                } else if (typeOfTask.equals("E")) {
                    String dateOfTask = strarr[4];
                    Task pastTask = new Event(taskDescription, dateOfTask);
                    if (statusOfTask.equals("X")) {
                        pastTask.markAsDone();
                    }
                    existingTaskList.add(pastTask);
                }
            }
        } else {
            this.createFiles();
        }
        return existingTaskList;
    }

    /**
     * Rewrite the taskList into the existing taskList file.
     * @param newList taskList that has been modified.
     * @throws IOException.
     */
    void saveNewChanges(TaskList newList) throws IOException {
        File taskFile = new File(this.fileLocation);
        PrintWriter pw = new PrintWriter(taskFile);
        List<Task> newTaskList = newList.getList();
        for(int i = 0; i < newTaskList.size(); i++) {
            pw.println( (i+1) + ":" + newTaskList.get(i).write());
        }
        pw.close();
    }

}
