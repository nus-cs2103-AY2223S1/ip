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

public class Storage {
    private static final String DEFAULT_SAVE_PATH = "data/SavedData.duke";
    private static final String DEFAULT_SAVE_FOLDER = "data";
    private File file;

    private Storage(File file) {
        this.file = file;
    }

    public static Storage createStorage(String path) throws IOException {
        File newFile = new File(path);
        newFile.createNewFile();
        return new Storage(newFile);
    }

    public static Storage createStorage() throws IOException {
        File newFile = new File(DEFAULT_SAVE_PATH);
        new File(DEFAULT_SAVE_FOLDER).mkdir();
        newFile.createNewFile();
        return new Storage(newFile);
    }

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

    public void saveData(ParsedData[] dataList) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (ParsedData pd : dataList) {
            sb.append(pd.getSavedString());
            sb.append('\n');
        }
        FileWriter fw = new FileWriter(file);
        fw.write(sb.toString());
        fw.close();
    }

    public void saveTasks(TaskList tl) throws IOException {
        saveData(tl.getParsedData());
    }

    public void saveTask(Task task) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        fw.write(String.format("%s%n", task.convertToParseData().getSavedString()));
        fw.close();
    }
}
