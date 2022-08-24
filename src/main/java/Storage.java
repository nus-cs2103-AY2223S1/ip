import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final File file;

    public Storage(Path path) throws DukeException {
        this.file = path.toFile();
        if (!this.file.exists()) {
            try {
               this.file.getParentFile().mkdirs();
               this.file.createNewFile();
            } catch (SecurityException e) {
                throw new DukeException("Unable to make directory!");
            } catch (IOException e) {
                throw new DukeException("Error occurred when creating file!");
            }
        }
    }

    public TaskList load() throws DukeException {
        try {
            Scanner sc = new Scanner(this.file);
            TaskList tasks = new TaskList();
            while (sc.hasNextLine()) {
                Task task = Task.fromStorage(sc.nextLine());
                if (task != null) {
                    tasks.addTask(task);
                }
            }
            sc.close();
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("Unable to locate file when trying to read from file!");
        }
    }

    public void save(TaskList tasks) {
        try {
            FileWriter fileWriter = new FileWriter(this.file);
            fileWriter.write(tasks.toStorage());
            fileWriter.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Unable to locate file when trying to write to file!");
        } catch (IOException e) {
            throw new DukeException("Error occurred when writing to file!");
        }
    }

}
