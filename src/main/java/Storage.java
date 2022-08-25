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
                return new ArrayList<>();
            } catch (IOException e) {
                throw new DukeException("Loading Error");
            }
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
                        continue;
                    } catch (DateTimeParseException e) {
                        continue;
                    }
                }
                return loadedTasks;
            } catch (FileNotFoundException e) {
                throw new DukeException("Loading Error");
            }
        }
    }
}
