package duke;

import duke.command.Command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Represents a storage system that is responsible
 * for the saving and loading of data.
 * @author Justin Cheng.
 */
public class Storage {
    private String fileName;

    /**
     * Constructor for the Storage class.
     * @param filePath The path in String.
     * @throws DukeException if there is an IOException.
     */
    public Storage(String filePath) throws DukeException {
        this.fileName = filePath;
        try {
            File file = new File(this.fileName);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a String representation of the data file.
     * @return the data file in String.
     * @throws IOException if the file does not exist.
     */
    public String read() throws IOException {
        return Files.readString(Path.of(this.fileName));
    }

<<<<<<< HEAD
=======
    /**
     * Saves changes made to the TaskList to the data file.
     * @param list The TaskList being changed.
     * @throws DukeException if the file or directory does not
     * exist.
     */
>>>>>>> branch-A-JavaDoc
    public void save(TaskList list) throws DukeException {
        try {
            File f = new File(this.fileName);
            if (f.exists()) {
                Files.delete(Paths.get(this.fileName));
            }
            FileWriter fw = new FileWriter(this.fileName, true);
            for (Task t : list.getTasks()) {
                fw.write(t.toString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("OOPS! Looks like the file or directory does not exist...");
        }
    }

    /**
     * Returns an ArrayList of Tasks after reading
     * the data from the data file.
     * @param ui The Ui to print outputs.
     * @return an ArrayList of Tasks.
     */
    public ArrayList<Task> load(Ui ui) {
        TaskList tasks = new TaskList(new ArrayList<>());
        String tasksStr = null;
        try {
            tasksStr = this.read();
        } catch (IOException e) {
            System.out.println("File is corrupted. Please delete tasks file and try again.");
            System.exit(0);
        }
        String[] strArray = tasksStr.split(System.lineSeparator());
        try {
            for (String s : strArray) {
                Command command = Parser.parseCommand(s);
                command.execute(tasks, ui, this);
            }
        } catch (DukeException e) {
            ui.showText(e.toString());
        }
        return tasks.getTasks();
    }
}
