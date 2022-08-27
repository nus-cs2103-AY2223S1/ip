package duke.storage;

import duke.DukeException;
import duke.task.*;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    private final String directory;
    private final String fileName;
    File data;

    public Storage(String directory, String fileName) throws DukeException {
        this.directory = directory;
        this.fileName = fileName;
        data = createFileWithDirIfNotExist(directory, fileName);
    }

    private File createFileWithDirIfNotExist(String directory, String fileName) throws DukeException {
        File f = new File(directory + fileName);
        try {
            if (f.getParentFile().mkdirs()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("Error finding/creating data file");
        }
        return f;
    }

    public ArrayList<String> load() throws DukeException {
        ArrayList<String> toReturn = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(data));
            String line = reader.readLine();
            while (line != null) {
                toReturn.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException("Error reading from data file");
        }
        return toReturn;
    }

    private ArrayList<String> tasksToString(TaskList tasks) {
        ArrayList<String> tasksAsStrings = new ArrayList<>();
        String currString;

        for (Task currTask : tasks.get()) {
            currString = "";
            if (currTask instanceof Todo) {
                currString += "T | ";
                currString += currTask.isDone() ? "1 | " : "0 | ";
                currString += currTask.getDescription();
            } else if (currTask instanceof Deadline) {
                currString += "D | ";
                currString += currTask.isDone() ? "1 | " : "0 | ";
                currString += currTask.getDescription() + " | ";
                currString += currTask.getDate();
            } else if (currTask instanceof Event) {
                currString += "E | ";
                currString += currTask.isDone() ? "1 | " : "0 | ";
                currString += currTask.getDescription() + " | ";
                currString += currTask.getDate();
            }
            tasksAsStrings.add(currString);
        }

        return tasksAsStrings;
    }

    public void saveToFile(TaskList tasks) {
        try {
            data.delete();
            data = createFileWithDirIfNotExist(directory, fileName);

            BufferedWriter writer;
            writer = new BufferedWriter(new FileWriter(data));
            ArrayList<String> toWrite = tasksToString(tasks);

            for (String str : toWrite) {
                writer.write(str + System.lineSeparator());
            }

            writer.close();
        } catch (Exception e) {
            throw new DukeException("Failed to save to file");
        }
    }
}
