package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Storage class allows the user to load/save
 * all its tasks and their respective states in a txt file.
 * The txt file will be created if it does not exist in the filePath
 *
 * @author Gerald Teo Jin Wei
 * @version 0.1
 * @since 2022-08-28
 */
public class Storage {

    private String filePath;

    /**
     * Constructor for Storage class
     * @param filePath Path to txt file that stores task list data
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * This method gets the existing txt file in the
     * file path and creates the txt file if it
     * does not exist
     * @return File This is the txtfile that stores all the user's task information
     */
    public File getFile() {
        File file = new File(filePath);
        try {
            if (file.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * This method gets the existing text file and
     * converts the text into a list of the user's tasks
     * @return List This is the current list of the user's tasks
     */
    public List<Task> load() {
        File file = this.getFile();
        List<Task> taskList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] splitStr = line.split("\\|");
                String taskType = splitStr[0];
                boolean isDone = splitStr[1].equals("1") ? true : false;
                String description = splitStr[2];
                String date = "";
                if (splitStr.length > 3) {
                    date = splitStr[3];
                }
                switch (taskType) {
                case "T":
                    taskList.add(new ToDo(description, isDone));
                    break;
                case "D":
                    taskList.add(new Deadline(description, date, isDone));
                    break;
                case "E":
                    taskList.add(new Event(description, date, isDone));
                    break;
                default:
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return taskList;
    }

    /**
     * This method saves the latest version of the user's task
     * list to the txt file
     * @param taskList This is the latest version of user's task list
     */
    public void save(List<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                String isDone = task.isDone ? "1" : "0";
                if (task instanceof ToDo) {
                    fw.write("T|" + isDone + "|" + task.description + "\n");
                } else if (task instanceof Deadline) {
                    fw.write("D|" + isDone + "|" + task.description + "|" + ((Deadline) task).by + "\n");
                } else if (task instanceof Event) {
                    fw.write("E|" + isDone + "|" + task.description + "|" + ((Event) task).at + "\n");
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to save: " + e.getMessage() + e.getStackTrace());
        }
    }
}
