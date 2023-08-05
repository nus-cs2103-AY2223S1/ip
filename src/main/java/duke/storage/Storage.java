package duke.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import duke.exception.DukeException;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;

/**
 * Storage class which deals with loading tasks from the file and saving tasks in the file.
 *
 * @author Shawn Chew
 * @version CS2103T AY 22/23 Sem 1
 */
public class Storage {
    /**
     * The file path of the file.
     */
    private String filePath;
    /**
     * Represents the file.
     */
    private File dataFile;
    /**
     * Represents the file writer.
     */
    private FileWriter fw;

    /**
     * A constructor to initialize the Storage object.
     *
     * @param filePath The file path of the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data in the file to a List object.
     *
     * @return List object containing the data of the file.
     */
    public List<Task> loadData() {
        try {
            dataFile = getFile();
            assert dataFile.exists() : "File should exist";
            Scanner s = new Scanner(dataFile);
            List<Task> tasks = new ArrayList<>();
            copyDataToList(s, tasks);
            fw = new FileWriter(filePath, false);
            return tasks;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void copyDataToList(Scanner s, List<Task> tasks) {
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] tempArr = line.split(" \\| ");
            Task t;
            switch (tempArr[0]) {
            case "T":
                t = new Todo(tempArr[3]);
                break;
            case "D":
                t = new Deadline(tempArr[3], tempArr[4]);
                break;
            case "E":
                t = new Event(tempArr[3], tempArr[4]);
                break;
            default:
                throw new DukeException("Invalid data in duke.txt");
            }
            if (tempArr[1].equals("1")) {
                t.mark();
            }
            t.setPriority(tempArr[2]);
            tasks.add(t);
        }
    }

    private File getFile() throws IOException {
        File dataFolder = new File(filePath.split("/", 2)[0]);
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    /**
     * Updates the data in the file.
     *
     * @param tasks The data which will overwrite the file.
     * @throws IOException If an I/O error occurs.
     */
    public void updateData(TaskList tasks) throws IOException {
        String newText = "";
        for (int i = 0; i < tasks.size(); i++) {
            String s = tasks.get(i).getStringToSave();
            if (i == 0) {
                newText += s;
            } else {
                newText += '\n' + s;
            }
        }
        fw.write(newText);
        fw.close();
    }
}
