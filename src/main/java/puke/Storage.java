package puke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Deals with storing on Hard Disk
 */
public class Storage {

    private static String file_path = "data/pukeData.txt";

    /**
     * File that is created on hard Disk
     */
    public static File savedTasks = new File(file_path);

    /**
     * Creates a Storage object
     */
    public Storage() {

        try {
            savedTasks.getParentFile().mkdir();
            savedTasks.createNewFile();
            File file = new File(file_path);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * @@24Donovan24-reused
     * Reused from https://github.com/24Donovan24/ip/blob/master/src/main/java/duke/Storage.java
     * with minor modifications
     *
     * Loads existing data from saved file in HardDrive
     * @return ArrayList of tasks that has been read from Hard Disk
     */
    public ArrayList<Task> load() {
        try {
            ArrayList<Task> temp = new ArrayList<>();
            Scanner s = new Scanner(savedTasks);
            while (s.hasNextLine()) {
                String taskInString = s.nextLine();
                String[] taskInArray = taskInString.split(" \\| ");

                if (taskInArray[0].equals("T")) {
                    Task task = new ToDo(taskInArray[2]);
                    temp.add(task);
                    if (taskInArray[1].equals("1")) {
                        task.markAsDone();
                    }
                } else if (taskInArray[0].equals("D")) {
                    Task task = new Deadline(taskInArray[2], LocalDateTime.parse(taskInArray[3]));
                    temp.add(task);
                    if (taskInArray[1].equals("1")) {
                        task.markAsDone();
                    }
                } else if (taskInArray[0].equals("E")) {
                    Task task = new Event(taskInArray[2], LocalDateTime.parse(taskInArray[3]));
                    temp.add(task);
                    if (taskInArray[1].equals("1")) {
                        task.markAsDone();
                    }
                }
            }
            return temp;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Saves the tasks in current List into HardDrive
     * @param tasks current List
     */
    public static void saveTasks(List<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(Duke.d.storage.savedTasks.getPath());
            for (Task task: tasks) {
                writer.write(task.saveFormat() + System.lineSeparator());
            }
            writer.close();
        } catch(IOException e) {
            System.out.println("Aiya, cant save it la");
        };
    }


}
