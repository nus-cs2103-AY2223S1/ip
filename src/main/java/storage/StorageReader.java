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
import task.Deadline;
import task.Event;
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
    public TaskList  syncArrayList() throws DukeException {
        TaskList userInputHistory = new TaskList();
        List<String> linesInFile = getAllLines();
        Task currTask; Event currEvent; Deadline currDeadline;
        ArrayList<String> a;
        LocalDate date;
        int n = linesInFile.size(), i = 0;
        for (; i < n; i ++) {
            if (!linesInFile.get(i).equals("")) {
                a = StorageParser.fileLineToTask(linesInFile.get(i));
                if (a.get(0).equals("D")) {
                    date = LocalDate.parse(a.get(3));
                    currDeadline = new Deadline(a.get(2), date);
                    if (a.get(1).equals("done")) {
                        currDeadline.markAsDone();
                    }
                    userInputHistory.addDeadline(currDeadline);
                } else if (a.get(0).equals("E")) {
                    date = LocalDate.parse(a.get(3));
                    currEvent = new Event(a.get(2), date);
                    if (a.get(1).equals("done")) {
                        currEvent.markAsDone();
                    }
                    userInputHistory.addEvent(currEvent);
                } else if (a.get(0).equals("T")) {
                    currTask = new Task(a.get(2));
                    if (a.get(1).equals("done")) {
                        currTask.markAsDone();
                    }
                    userInputHistory.addTask(currTask);
                }
            }
        }
        return userInputHistory;
    }
}
