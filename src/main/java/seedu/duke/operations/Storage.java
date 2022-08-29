package seedu.duke.operations;

import seedu.duke.DukeException;
import seedu.duke.task.DeadlineTask;
import seedu.duke.task.Task;
import seedu.duke.task.EventTask;
import seedu.duke.task.TodoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
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
                        Task loadTask = createTask(split[0], split[1], split[2]);
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

    public void updateSave(TaskList tasks) throws DukeException {
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

    static Task createTask(String taskType, String check, String description) throws DukeException {
        Task newTask;
        switch (taskType) {
            case "T":
                newTask = new TodoTask(description);
                break;
            case "D":
                String[] splitD = timeSpliter(description);
                newTask = new DeadlineTask(splitD[0], splitD[1]);
                break;
            case "E":
                String[] splitE = timeSpliter(description);
                newTask = new EventTask(splitE[0], splitE[1]);
                break;
            default:
                throw new DukeException("No such task label");
        }
        if (check.equals("1")) {
            newTask.check();
        }
        return newTask;
    }

    static String[] timeSpliter(String description) {
        int index = description.lastIndexOf('/');
        String[] split = new String[2];
        split[0] = description.substring(0, index);
        split[1] = description.substring(index + 1);
        return split;
    }
}
