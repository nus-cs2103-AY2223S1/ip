package anya;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the storage for the list task managed by the ChatBot.
 */
public class Storage {

    private String fileLocation;

    /**
     * Create storage with this filePath.
     * @param filePath the path to the file.
     */
    Storage(String filePath) {
        this.fileLocation = filePath;
    }

    /**
     * Check whether the file in the filePath exists or not.
     * @return true or false.
     */
    //@@author xhphoong-reused
    //Reused from https://www.sghill.net/2014/how-do-i-make-cross-platform-file-paths-in-java/
    //with minor modifications
    boolean checkFile() {
        String base = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(base, fileLocation);
        boolean hasFile = java.nio.file.Files.exists(path);
        return hasFile;
    }
    //@@author

    /**
     * Create new file.
     * @throws Exception.
     */
    void createFile() throws Exception {
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
    List<Task> readFile() throws Exception {
        List<Task> existingTasks = new ArrayList<Task>();

        if (this.checkFile() == false) {
            this.createFile();
            return existingTasks;
        }
        FileReader file = new FileReader(fileLocation);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String task = sc.nextLine();
            readTask(task, existingTasks);
        }
        return existingTasks;
    }

    private void readTask(String task, List<Task> existingTasks) {
        String[] strarr = task.split(":");
        boolean haveDate = strarr.length == 5;
        String typeOfTask = strarr[1];
        assert typeOfTask != null : "typeOfTask should not be null";
        String statusOfTask = strarr[2];
        assert statusOfTask.equals(" ") || statusOfTask.equals("X") : "statusOfTask should be null or X";
        String taskDescription = strarr[3];
        assert taskDescription != null : "typeDescription should not be null";
        String dateOfTask = "";
        if (haveDate) {
            dateOfTask = strarr[4];
            assert dateOfTask != null : "dateOfTask should not be null";
        }

        if (typeOfTask.equals("T")) {
            Task pastTask = new ToDo(taskDescription);
            existingTasks.add(pastTask);

        } else if (typeOfTask.equals("D")) {
            Task pastTask = new Deadline(taskDescription, dateOfTask);
            existingTasks.add(pastTask);

        } else if (typeOfTask.equals("E")) {
            Task pastTask = new Event(taskDescription, dateOfTask);
            existingTasks.add(pastTask);
        }

        if (statusOfTask.equals("X")) {
            int currIndex = existingTasks.size() - 1;
            existingTasks.get(currIndex).markAsDone();
        }
    }

    /**
     * Rewrite the taskList into the existing taskList file.
     * @param newList taskList that has been modified.
     * @throws IOException.
     */
    //@@author xhphoong-reused
    //Reused from https://github.com/nus-cs2103-AY2223S1/ip/pull/364/commits/039486f33c5fc0a4f3a560793102540c6c374d6e
    //eugenetayyj: Level-7 commit : Duke.java's saveTasks() method
    //with minor modifications
    void saveNewChanges(TaskList newList) throws IOException {
        File taskFile = new File(this.fileLocation);
        PrintWriter pw = new PrintWriter(taskFile);
        List<Task> newTasks = newList.getList();

        for (int i = 0; i < newTasks.size(); i++) {
            pw.println((i + 1) + ":" + newTasks.get(i).write());
        }

        pw.close();
    }
    //@@author

}
