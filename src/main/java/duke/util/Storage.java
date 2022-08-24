package duke.util;

import duke.DukeException;
import duke.task.TaskList;
import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public final String dataPath;
    public final String storageName;

    public Storage(String dataPath, String storageName) {
        this.dataPath = dataPath;
        this.storageName = storageName;
    }

    public void writeTaskListToStorage(TaskList taskList) throws DukeException {
        try {
            File directory = new File(dataPath);
            if (!directory.exists() || !directory.isDirectory()) {
                directory.mkdir();
            }
            File file = new File(dataPath + "/" + storageName);
            FileWriter fw = new FileWriter(file);
            StringBuilder toWrite = new StringBuilder();
            for (String taskString : taskList.getAllTasksInStorageFormat()) {
                toWrite.append(taskString + System.lineSeparator());
            }
            fw.write(toWrite.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error writing to storage file");
        }
    }

    public List<Task> load() throws DukeException {
        try {
            List<Task> tasks = new ArrayList<>();
            File file = new File(dataPath + "/" + storageName);
            if (!file.exists()) {
                throw new DukeException("No storage file found");
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String taskString = scanner.nextLine();
                tasks.add(StorageParser.parseTaskString(taskString));
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException("Error reading from storage file");
        }
    }
}
