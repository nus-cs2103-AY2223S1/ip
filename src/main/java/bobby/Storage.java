package bobby;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import bobby.task.Deadline;
import bobby.task.Event;
import bobby.task.Task;
import bobby.task.Todo;

/**
 * Storage class for writing and reading form data file
 */
public class Storage {
    private String path;

    /**
     * Constructor to create storage object
     * @param filename the filename of the data file
     */
    public Storage(String filename) {
        String absolutePath = new File("").getAbsolutePath() + "/data/" + filename;
        File file = new File( absolutePath );
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        this.path = absolutePath;

    }


    public void changeFile(String filename) {

        String absolutePath = new File("").getAbsolutePath() + "/data/" + filename;
        this.path = absolutePath;
    }
    /**
     * Reads the file
     * @return a list of task that is in the file
     */
    public ArrayList<Task> readFile() {
        ArrayList<Task> data = new ArrayList<>();
        File file = new File(this.path);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String[] taskInfo = reader.nextLine().split("\\|");

                if (taskInfo[0].equals("T")) {
                    data.add(new Todo(taskInfo[1], Boolean.parseBoolean(taskInfo[2])));
                } else if (taskInfo[0].equals("E")) {
                    data.add(new Event(taskInfo[1],
                            Boolean.parseBoolean(taskInfo[2]),
                            LocalDateTime.parse(taskInfo[3]),
                            LocalDateTime.parse(taskInfo[4])));

                } else if (taskInfo[0].equals("D")) {
                    data.add(new Deadline(taskInfo[1],
                            Boolean.parseBoolean(taskInfo[2]),
                            LocalDateTime.parse(taskInfo[3])));
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        return data;
    }


    /**
     * Updates the content of the file
     * @param updatedData the updated list of task
     */
    public void updateFile(ArrayList<Task> updatedData) {
        String dataString = "";
        for (Task task : updatedData) {
            dataString += String.format("%s\n", task.formatTaskString());
        }
        try {
            FileWriter fileWriter = new FileWriter(this.path);
            fileWriter.write(dataString);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
