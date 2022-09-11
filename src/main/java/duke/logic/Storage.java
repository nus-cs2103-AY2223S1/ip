package duke.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Storage deals with writing task history to a file.
 *
 * @author totsukatomofumi
 */
public class Storage {
    /**
     * HashMap to contain set chars representing task types and function to return a new task of that
     * corresponding type pairs.
     */
    private static final HashMap<Character,
            BiFunction<Integer, Integer, Function<String, Task>>> taskMap = new HashMap<>();

    static {
        Storage.taskMap.put('T', (index, length) -> line -> new ToDo(line.substring(index)));
        Storage.taskMap.put('D', (index, length) -> line -> new Deadline(line.substring(index, index + length),
                LocalDate.parse(line.substring(index + length))));
        Storage.taskMap.put('E', (index, length) -> line -> new Event(line.substring(index, index + length),
                LocalDate.parse(line.substring(index + length))));
    }

    /** Directory where task history file is. */
    private File historyDir;

    /** Task history file. */
    private File history;

    /**
     * Constructs a storage.
     *
     * @param historyFilePath the file path of the stored task history.
     */
    public Storage(String historyFilePath) {
        this.history = new File(historyFilePath);
        this.historyDir = history.getParentFile();
        //when new storage object made, create the storage data files
        this.createRequiredFiles();
    }

    /**
     * Creates the directory and the file.
     */
    public void createRequiredFiles() {
        //will not create if files and directory already exist
        this.historyDir.mkdirs();
        try {
            this.history.createNewFile();
        } catch (IOException e) {
            //file or directory was modified during runtime of this program
            throw new RuntimeException("An important file or directory was modified.");
        }
    }

    /**
     * Updates the file with this task list.
     *
     * @param taskList the task list to update the file with.
     */
    public void update(TaskList taskList) {
        FileWriter fileWriter;
        assert this.history.exists();
        try {
            fileWriter = new FileWriter(history);
            //prepare what to overwrite
            String overwrite = "";
            for (Task task : taskList) {
                overwrite += task.toStorageFormat() + "\n";
            }
            fileWriter.write(overwrite);
            fileWriter.close();
        } catch (IOException e) {
            //made directory and/or made file could be deleted by user during runtime of this program
            throw new RuntimeException(e);
        }
    }

    /**
     * Clears the file.
     */
    public void clear() {
        assert this.history.exists();
        try {
            FileWriter fileWriter = new FileWriter(this.history);
            fileWriter.write("");
            fileWriter.close();
        } catch (IOException e) {
            //file or directory was modified during runtime of this program
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the file task history is stored.
     *
     * @return the file task history is stored.
     */
    public File getHistory() {
        return this.history;
    }

    /**
     * Retrieves list of task from the task history file via storage.
     *
     * @throws IOException If the file contains invalid contents that cannot be parsed.
     */
    public void retrieveFromStorage(TaskList taskList) throws IOException {
        //initialize scanner with task history file
        Scanner retriever;
        try {
            retriever = new Scanner(this.getHistory());
        } catch (FileNotFoundException e) {
            //file or directory was modified during runtime of this program
            throw new RuntimeException(e);
        }
        //iterate through each line
        while (retriever.hasNextLine()) {
            String line = retriever.nextLine();
            StringBuilder descLengthStr = new StringBuilder();
            //starting at first digit of length of task description
            int index = 2;
            while (index < line.length() && line.charAt(index) != '_') {
                descLengthStr.append(line.charAt(index));
                ++index;
            }
            //throw bad file exception
            //no '_' encountered
            if (index == line.length()) {
                retriever.close();
                throw new IOException("Text file containing history has invalid formatting for parsing.");
            }
            //now index is index of first '_' encountered
            int descLength;
            try {
                descLength = Integer.parseInt(descLengthStr.toString());
            } catch (NumberFormatException e) { //formatting is all messed up
                retriever.close();
                throw new IOException("Text file containing history has invalid formatting for parsing.");
            }
            //increment to first index of task description
            index++;
            //retrieve task according to char
            Task toAdd = Storage.taskMap.get(line.charAt(0)).apply(index, descLength).apply(line);
            if (toAdd != null) {
                if (line.charAt(1) == '1') {
                    toAdd.mark();
                }
                taskList.add(toAdd);
            } else { //if null means no task category was identified
                retriever.close();
                throw new IOException("Unable to identify task type as type found in file was invalid.");
            }
        }
        retriever.close();
    }
}
