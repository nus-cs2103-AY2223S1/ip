package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.Task.Deadline;
import duke.Task.Event;
import duke.Task.Task;
import duke.Task.Todo;

public class Storage {
    private java.nio.file.Path path;

    public Storage(String filename) {
        String home = System.getProperty("user.dir");
        this.path = java.nio.file.Paths.get(home, "src", "data", filename);

    }

    /**
     * Reads the file
     * @return a list of task that is in the file
     */
    public ArrayList<Task> readFile() {
        ArrayList<Task> data = new ArrayList<>();
        System.out.println();
        File file = new File(String.valueOf(this.path));
        checkFileExist();
        try {
            if (!checkFileExist()) {
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
                            LocalDateTime.parse(taskInfo[3])));

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

    private boolean checkFileExist() {
        return java.nio.file.Files.exists(this.path);
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
            FileWriter fileWriter = new FileWriter(String.valueOf(this.path));
            fileWriter.write(dataString);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
