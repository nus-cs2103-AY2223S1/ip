package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * A class that handles loading and saving tasks in the file.
 */
public class Storage {
    private static final String DATA_FILE_PATH = "data";
    private static final String DATA_FILE_NAME = "duke.txt";

    /**
     * Attempts to load saved tasks from the hard disk.
     * Creates the save file and directory if missing.
     *
     * @throws IOException If the save file or file path could not be accessed.
     */
    public static void loadData() throws IOException {
        Path parentDir = Paths.get(DATA_FILE_PATH);
        if (!Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }

        File dataFile = new File(Paths.get(parentDir.toString(), DATA_FILE_NAME).toString());
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }

        TaskList.clearTaskList();

        Scanner scanner = new Scanner(dataFile);
        while (scanner.hasNextLine()) {
            String saveFormatString = scanner.nextLine();
            String[] args = saveFormatString.split("\\|", 4);
            if (args.length < 3) {
                System.out.println("Invalid task save string, task not added");
                continue;
            }

            String taskType = args[0].strip();
            boolean marked = args[1].strip().equals("1") ? true : false;
            String taskName = args[2].strip();
            if (taskName == "") {
                System.out.println("Invalid task save string, task not added");
                continue;
            }

            LocalDate date = null;
            if (args.length >= 4) {
                try {
                    date = LocalDate.parse(args[3].strip());
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date in task save string, task not added");
                }
            }

            switch (taskType) {
            case "T":
                TaskList.addToList(new ToDo(taskName, marked));
                break;
            case "D":
                if (date != null) {
                    TaskList.addToList(new Deadline(taskName, marked, date));
                }
                break;
            case "E":
                if (date != null) {
                    TaskList.addToList(new Event(taskName, marked, date));
                }
                break;
            default:
            }
        }
        scanner.close();


    }

    /**
     * Attempts to save tasks from TaskList to the hard disk.
     * Creates the save file and directory if missing.
     *
     * @throws IOException If the save file or file path could not be accessed.
     */
    public static void saveData() throws IOException {
        Path parentDir = Paths.get(DATA_FILE_PATH);
        if (!Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }

        File dataFile = new File(Paths.get(parentDir.toString(), DATA_FILE_NAME).toString());
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }

        FileWriter dataFileWriter = new FileWriter(dataFile);

        try {
            for (String saveString: TaskList.getTasksSaveStrings()) {
                dataFileWriter.write(saveString + "\n");
            }

            dataFileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: Failed to save tasks");
        }
    }
}
