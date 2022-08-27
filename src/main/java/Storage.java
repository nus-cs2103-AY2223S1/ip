import java.io.*;
import java.util.ArrayList;

public class Storage {

    String directory;
    String fileName;
    File data;

    Storage(String directory, String fileName) throws DukeException {
        this.directory = directory;
        this.fileName = fileName;
        data = createFileWithDirIfNotExist(directory, fileName);
    }

    private File createFileWithDirIfNotExist(String directory, String fileName) throws DukeException {
        File f = new File(directory + fileName);
        try {
            f.getParentFile().mkdirs();
            f.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Error finding/creating data file");
        }
        return f;
    }

    ArrayList<String> load() throws DukeException {
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
                currString += currTask.isDone ? "1 | " : "0 | ";
                currString += currTask.description;
            } else if (currTask instanceof Deadline) {
                currString += "D | ";
                currString += currTask.isDone ? "1 | " : "0 | ";
                currString += currTask.description + " | ";
                currString += ((Deadline) currTask).by;
            } else if (currTask instanceof Event) {
                currString += "E | ";
                currString += currTask.isDone ? "1 | " : "0 | ";
                currString += currTask.description + " | ";
                currString += ((Event) currTask).at;
            }
            tasksAsStrings.add(currString);
        }

        return tasksAsStrings;
    }

    void saveToFile(TaskList tasks) {
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
