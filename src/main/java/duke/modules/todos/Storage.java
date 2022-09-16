package duke.modules.todos;

import duke.MessagefulException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Task list saving and loading functionalities.
 */
public class Storage {
    private final String fileDir;
    private final String filePath;

    /**
     * Constructor
     *
     * @param fileDir  The path, relative to the current directory, of the save file.
     * @param fileName The name of the save file.
     */
    public Storage(String fileDir, String fileName) {
        this.fileDir = fileDir;
        this.filePath = fileDir + "/" + fileName;
    }

    /**
     * Constructor using the default savefile location.
     */
    public Storage() {
        this("data", "tasks.csv");
    }

    /**
     * Saves the given TaskList.
     *
     * @param todos The TaskList to save.
     * @throws MessagefulException when saving fails.
     */
    public void saveList(TaskList todos) throws MessagefulException {
        try {
            File fileDir = new File(this.fileDir);
            if (!fileDir.isDirectory() && !fileDir.mkdirs()) {
                throw new MessagefulException("cannot create task save dir", "Uh oh! I cannot save the task list.");
            }

            FileWriter fw = new FileWriter(filePath);
            for (Task task : todos) {
                fw.write(String.join(",", task.flatPack()) + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new MessagefulException(
                    "file writing error",
                    "Uh oh! I cannot save the task list. This might help: " + e);
        }
    }

    /**
     * Loads the TaskList from the file.
     *
     * @return The loaded TaskList.
     * @throws MessagefulException when loading fails.
     */
    public ArrayList<Task> loadList() throws MessagefulException {
        try {
            ArrayList<Task> todos = new ArrayList<>();
            Scanner file = new Scanner(new File(filePath));
            while (file.hasNextLine()) {
                List<String> line = Arrays.asList(file.nextLine().split(",", -1));
                assert !line.isEmpty() : "Loaded an empty line from file";

                todos.add(Task.fromFlatpack(line));
            }
            return todos;
        } catch (FileNotFoundException e) {
            throw new MessagefulException(
                    "tasks file missing",
                    "I have gotten you started with an empty task list. Welcome!");
        }
    }
}
