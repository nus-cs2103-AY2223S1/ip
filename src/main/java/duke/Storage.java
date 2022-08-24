package duke;

import duke.TaskList;
import duke.ToDo;
import duke.Deadline;
import duke.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList readFromDisk() {

        try {

            File file = new File(filePath);
            File parentFolder = file.getParentFile();

            // Ensure that the parent folder exists.
            if (!parentFolder.exists()) {
                file.mkdirs();
            }

            // Ensure that the file exists.
            if (!file.exists()) {
                file.createNewFile();
                return new TaskList();
            }

            // The duke.TaskList to eventually be returned.
            TaskList taskList = new TaskList();

            // Parse each task one by one.
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine(); // e.g. "E|0|New Year's Countdown|2019-12-31T23:59"
                // TODO: Ensure that the user never enters the | character.
                String[] components = line.split("\\|");
                boolean isTaskInitiallyComplete = (Objects.equals(components[1], "1"));
                switch (components[0]) {
                    case "T":
                        taskList.add(new ToDo(components[2], isTaskInitiallyComplete));
                        break;
                    case "D":
                        taskList.add(new Deadline(components[2], isTaskInitiallyComplete, components[3]));
                        break;
                    case "E":
                        taskList.add(new Event(components[2], isTaskInitiallyComplete, components[3]));
                        break;
                }
            }

            return taskList;
        } catch (FileNotFoundException e) {
            // This should not happen because the file is created if it does not exist.
            System.out.println("An error occurred while loading the tasks.");
            e.printStackTrace();
            return new TaskList();
        } catch (IOException e) {
            System.out.println("An error occurred while creating a new .txt file.");
        }

        return new TaskList();
    }

    public void saveToDisk(TaskList taskList) {
        try {
            // Note: This conveniently deletes the existing contents of data/duke.txt.
            PrintWriter printWriter = new PrintWriter(filePath);

            for (int i = 0; i < taskList.size(); i++) {
                printWriter.println(taskList.get(i).toFileRepresentation());
            }

            printWriter.close();

        } catch (FileNotFoundException e) {
            // This should not happen because the file is created if it does not exist.
            System.out.println("An error occurred when saving the tasks.");
            e.getStackTrace();
        }
    }

}
