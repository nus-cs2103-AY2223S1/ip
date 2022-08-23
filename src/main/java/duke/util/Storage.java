package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class Storage {

    private final File dataFile;

    public Storage(Path path) {
        this.dataFile = new File(path.toUri());
    }

    private void ensureDataFile() {
        if (dataFile.exists()) {
            return;
        }
        // Ref: https://stackoverflow.com/a/4040667
        File parent = dataFile.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            throw new DukeException("Could not create directory: " + parent);
        }
        try {
            if (!dataFile.createNewFile()) {
                throw new DukeException("Could not create file: " + dataFile);
            }
        } catch (IOException e) {
            throw new DukeException("An I/O error occurred creating the file: " + dataFile);
        }
    }

    public ArrayList<Task> load() {
        ensureDataFile();
        Scanner scanner;
        try {
            scanner = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            throw new DukeException("Could not open datafile for reading: " + dataFile);
        }

        ArrayList<Task> tasks = new ArrayList<>();
        try {
            while (scanner.hasNextLine()) {
                tasks.add(Parser.parseTask(scanner.nextLine()));
            }
        } catch (ParseException e) {
            throw new DataFileCorruptedException();
        }
        return tasks;
    }

    public void save(TaskList tasks) {
        ensureDataFile();
        try {
            FileWriter fw = new FileWriter(dataFile);
            for (Task task : tasks) {
                fw.write(task.toSaveString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Could not write to file: " + dataFile);
        }
    }
}
