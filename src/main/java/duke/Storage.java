package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents a Storage to store TaskList.
 * @author Tan Wen Cong
 */
public class Storage {
    private String filepath;

    /**
     * Constructor for Storage
     *
     * @param filepath absolute or relative filepath to file for Duke to save data to or load data from
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        String[] temp = filepath.split("/");
        new File(String.join("/", Arrays.copyOf(temp, temp.length - 1))).mkdirs();
        try {
            new File(filepath).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves TaskList to be loaded in the next program run
     *
     */
    public void save(TaskList taskList) {
        save(taskList, filepath);
    }

    /**
     * Saves TaskList to be loaded in the next program run
     *
     * @param taskList TaskList to be saved
     */
    public void save(TaskList taskList, String filepath) {
        try {
            // write over
            FileWriter fileWriter = new FileWriter(filepath);
            fileWriter.append(taskList.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads Tasks that are previously saved
     *
     * @return ArrayList of Tasks previously saved, new ArrayList if no tasks were previously saved
     */
    public ArrayList<Task> load() {
        File file = new File(filepath);
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                tasks.add(Parser.parseFromFile(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Archives Tasks to a txt file to be used on another occasion
     *
     * @param taskList TaskList to be archived
     */
    public void archive(TaskList taskList) {
        String[] temp = filepath.split("/");
        String archiveFilePath = String.join("/", Arrays.copyOf(temp, temp.length - 1));

        // generate file name using current time
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        archiveFilePath = archiveFilePath + "/" + dtf.format(now) + ".txt";
        try {
            new File(archiveFilePath).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        save(taskList, archiveFilePath);
        taskList.clear();
        save(taskList);
    }
}
