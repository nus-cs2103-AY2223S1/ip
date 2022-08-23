package storage;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import task.Task;
import exceptions.DukeException;
import utility.StorageParser;
import task.Deadline;
import task.Event;

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
    public ArrayList<Task>  syncArrayList() throws DukeException {
        ArrayList<Task> userInputHistory = new ArrayList<>();
        List<String> linesInFile = getAllLines();
        Task currTask; Event currEvent; Deadline currDeadline;
        ArrayList<String> a;
        int n = linesInFile.size(), i = 0;
        for (; i < n; i ++) {
            a = StorageParser.fileLineToTask(linesInFile.get(i));
            if (a.size() == 3) {
                currDeadline = new Deadline(a.get(0), a.get(2));
                if (a.get(1) == "done") {
                    currDeadline.markAsDone();
                }
                userInputHistory.add(currDeadline);
            } else {

            }
        }
        return userInputHistory;
    }
}
