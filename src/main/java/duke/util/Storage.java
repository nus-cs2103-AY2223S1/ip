package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exceptions.CorruptedLineException;
import duke.task.Task;

/**
 * Class to handle writing and reading info from files.
 */
public class Storage {
    private static final String DEFAULT_SAVE_PATH = "data/SavedData.duke";
    private static final String DEFAULT_SAVE_FOLDER = "data";
    private File file;

    private Storage(File file) {
        this.file = file;
    }

    /**
     * Factory to create use a path as save file if possible
     * @param path Path to save file
     * @return Storage
     * @throws IOException Throws if pathing cannot exist.
     */
    public static Storage createStorage(String path) throws IOException {
        File newFile = new File(path);
        newFile.createNewFile();
        return new Storage(newFile);
    }

    /**
     * Factory to use the default save path.
     * @return Storage
     * @throws IOException
     */
    public static Storage createStorage() throws IOException {
        File newFile = new File(DEFAULT_SAVE_PATH);
        new File(DEFAULT_SAVE_FOLDER).mkdir();
        newFile.createNewFile();
        return new Storage(newFile);
    }

    /**
     * Read the save file and convert it to a list of Task.
     * @return List of Tasks
     * @throws FileNotFoundException Throws when save file does not exist
     */
    public List<Task> readFile() throws FileNotFoundException {
        List<Task> ret = new ArrayList<>();
        List<Integer> corruptedLines = new ArrayList<>();
        Scanner sc = new Scanner(file);
        String line;
        int lineNum = 0;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            lineNum++;
            try {
                ret.add(Parser.parseDataFromLine(line));
            } catch (CorruptedLineException e) {
                corruptedLines.add(lineNum);
            }
        }
        sc.close();
        return ret;
    }

    /**
     * Takes in ParsedData and saves them into the save file.
     * @param dataList Data to be saved
     * @throws IOException Throws when save file doesn't exist
     */
    public void saveData(ParsedData[] dataList) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        StringBuilder sb = new StringBuilder();
        for (ParsedData pd : dataList) {
            sb.append(pd.getSavedString());
            sb.append('\n');
        }
        FileWriter fw = new FileWriter(file);
        fw.write(sb.toString());
        fw.close();
    }

    /**
     * Saves all entries in a {@code TaskList}
     * @param tl Task to save
     * @throws IOException Throws when save file is missing
     */
    public void saveTasks(TaskList tl) throws IOException {
        saveData(tl.getParsedData());
    }

    /**
     * Saves an individual task by appending to the save file.
     * @param task Task to append
     * @throws IOException Throws when save file is missing
     */
    public void saveTask(Task task) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file, true);
        fw.write(String.format("%s%n", task.convertToParseData().getSavedString()));
        fw.close();
    }
}
