package storage;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import task.Task;
import exceptions.DukeException;
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
     * Converts line in disk file to corresponding Task.
     * @param line String stored in file.
     * @return Task object.
     */
    public static Task fileLineToTask(String line) throws DukeException{
        String taskType, description;
        boolean marked;
        taskType = line.substring(1,2);
        marked =  line.charAt(4) == ' ';
        switch(taskType) {
        case "T":
            description = line.substring(6);
            Task t = new Task(description);
            if (marked) {
                t.markAsDone();
            }
            return t;
        case "D":
            description = line.substring(6, line.indexOf("("));
            Deadline d = new Deadline(description, getDateFromStorage(line, "by: "));
            if (marked) {
                d.markAsDone();
            }
            return d;
        case "E":
            description = line.substring(6, line.indexOf("("));
            Event e = new Event(description, getDateFromStorage(line, "at: "));
            if (marked) {
                e.markAsDone();
            }
            return e;
        }
        throw new DukeException("file corrupted");
    }

    /**
     * Return LocalDate object stored from the format its stored
     * in file.
     * @param line line retrieved from file.
     * @param compareString command to watch for in line.
     * @return LocalDate object
     * @throws DukeException exception.
     * @throws DateTimeParseException when date stored was invalid.
     */
    public static LocalDate getDateFromStorage(String line, String compareString) throws DukeException, DateTimeParseException {
        try {
            String date;
            int NUM_CHARACTERS_TO_CHECK = compareString.length();
            int DATE_LENGTH = 10;
            int startOfDateIndex = line.indexOf(compareString) + NUM_CHARACTERS_TO_CHECK;
            date = line.substring(startOfDateIndex, startOfDateIndex + DATE_LENGTH).trim();
            return LocalDate.parse(date);
        } catch (DateTimeParseException dtpe) {
            throw new DukeException("Date in storage is invalid\n>>");
        }
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
        Task currTask;
        int n = linesInFile.size(), i = 0;
        for (; i < n; i ++) {
            currTask = StorageReader.fileLineToTask(linesInFile.get(i));
            userInputHistory.add(currTask);
            System.out.println(currTask);
        }
        return userInputHistory;
    }
}
