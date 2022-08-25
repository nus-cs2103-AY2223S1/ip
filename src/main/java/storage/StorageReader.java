package storage;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import task.Task;
import exceptions.DukeException;
import utility.StorageParser;
import tasklist.TaskList;

/**
 * Encapsulates all read operations on disk file storage.
 */
public class StorageReader {
    Path path;
    public StorageReader(Path path) {
        this.path = path;
    }


    /**
     * Return contents of file history.
     * @return arraylist containing all the lines in the file.
     */
    public List<String> getAllLines()  {
        List<String> list = new ArrayList<>();
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return list;
    }

    /**
     * Syncs all changes stored in disk to arrayList maintained by program, by:
     * 1. Emptying USERINPUTHISTORY arraylist,
     * 2. Copying all lines on disk to USERINPUTHISTORY
     * @throws DukeException when fileLineToTask() fails
     */
    public TaskList syncArrayList() throws DukeException {
        TaskList userInputHistory = new TaskList();
        List<String> linesInFile = getAllLines();
        Task currTask;
        LocalDate date;
        int n = linesInFile.size(), i = 0;
        for (; i < n; i ++) {
            if (!linesInFile.get(i).equals("")) {
                currTask = StorageParser.fileLineToTask(linesInFile.get(i));
                userInputHistory.addTask(currTask);
                }
            }
        return userInputHistory;
    }
}
