package duke;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

/**
 * The Storage class manages write and read operations into the data file "duke.txt".
 */
class Storage {
    private static final String currentDir = System.getProperty("user.dir");
    private static final Path savedDataPath = Paths.get(Storage.currentDir, "/data");
    private static final Path dataFilePath = Paths.get(Storage.savedDataPath.toString(), "duke.txt");
    private static final Path archiveFilePath = Paths.get(Storage.savedDataPath.toString(), "archive.txt");
    private static final String filePath = currentDir + "/data/duke.txt";
    private static final String archivePath = currentDir + "/data/archive.txt";

    /**
     * Creates the data folder if it does not exist.
     */
    public static void createDataFolder() {
        boolean directoryExists = Files.exists(Storage.savedDataPath);

        if (!directoryExists) {
            try {
                Files.createDirectory(Storage.savedDataPath);
            } catch (IOException ex) {
                System.out.println("IOException in creating data folder!");
            }
        }
    }

    /**
     * Creates the data file if it does not exist.
     */
    public static void createDataFile() {
        boolean dataFileExists = Files.exists(Storage.dataFilePath);

        if(!dataFileExists) {
            try {
                Files.createFile(Storage.dataFilePath);
            } catch (IOException ex) {
                System.out.println("IOException in creating data file!");
            }
        }
    }

    /**
     * Updates the data of the data file with the current Tasks.
     *
     * @param tasks the current list of Tasks.
     */
    public static void updateData(TaskList tasks) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            fileWriter = new FileWriter(Storage.filePath, false);
            bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < tasks.size(); i++) {
                String entry = tasks.get(i).printTask();

                try {
                    bufferedWriter.write(entry);
                    bufferedWriter.newLine();
                } catch (IOException ex) {
                    System.out.println("Error in updating data file!");
                }
            }

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println("Error in updating data file!");
        }
    }

    /**
     * Archives all the tasks into an archive file.
     *
     * @param tasks all recorded tasks.
     */
    public static void archive(TaskList tasks) {
        // create archive file if not exists
        boolean dataFileExists = Files.exists(Storage.dataFilePath);

        if(!dataFileExists) {
            try {
                Files.createFile(Storage.dataFilePath);
            } catch (IOException ex) {
                System.out.println("IOException in creating data file!");
            }
        }

        // writing to archive file
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            fileWriter = new FileWriter(Storage.archivePath, false);
            bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < tasks.size(); i++) {
                String entry = tasks.get(i).printTask();

                try {
                    bufferedWriter.write(entry);
                    bufferedWriter.newLine();
                } catch (IOException ex) {
                    System.out.println("Error in archiving!");
                }
            }

            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println("Error in archiving!");
        }
    }

    /**
     * Initializes Duke with recorded Tasks from a previous session.
     *
     * @return the TaskList of previously recorded Tasks.
     * @throws DukeException if there is no data or if unable to initialize the data.
     */
    public static TaskList initializeData() throws DukeException {
        TaskList tasks = new TaskList();

        try {
            FileReader fileReader = new FileReader(Storage.filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String entry = bufferedReader.readLine();

            while (entry != null) {
                tasks.add(Task.parseEntry(entry, Task.getTaskTypeChar(entry)));
                entry = bufferedReader.readLine();
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException ex) {
            throw new DukeException("Error initializing data!");
        }

        return tasks;
    }
}
