package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    ArrayList<Task> load() throws DukeException {
        File dataFile = new File(filePath);
        if (!dataFile.exists()) {
            setupDirectory();
            return new ArrayList<>();
        } else {
            ArrayList<Task> loadedTasks = new ArrayList<>();
            try {
                Scanner sc = new Scanner(dataFile);
                while (sc.hasNext()) {
                    String currentRecord = sc.nextLine();
                    String[] split = currentRecord.split("###");
                    try {
                        if (split.length != 3) {
                            throw new DukeException("Error");
                        }
                        Task loadTask = TaskMaker.createTask(split[0], split[1], split[2]);
                        loadedTasks.add(loadTask);
                    } catch (DukeException e) {
                    } catch (DateTimeParseException e) {
                    }
                }
                return loadedTasks;
            } catch (FileNotFoundException e) {
                throw new DukeException("Loading Error");
            }
        }
    }

    void setupDirectory() throws DukeException {
        String[] dataPath = filePath.split("/");
        String pathBuilder = "";
        try {
            for (String path : dataPath) {
                if (path.contains(".")) {
                    new File(pathBuilder + path).createNewFile();
                    break;
                } else {
                    pathBuilder += path + "/";
                    new File(pathBuilder).mkdir();
                }
            }
        } catch (IOException e) {
            throw new DukeException("Loading Error");
        }
    }

    void updateSave(TaskList tasks) throws DukeException {
        try {
            FileWriter writer = new FileWriter(filePath);
            StringBuilder toWrite = new StringBuilder();
            for (int i = 1; i <= tasks.numOfTasks(); i++) {
                toWrite.append(tasks.fetchTask(i).saveFileFormat() + "\n");
            }
            writer.write(toWrite.toString());
            writer.flush();
        } catch (IOException e) {
            setupDirectory();
        }
    }
}
