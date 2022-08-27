package duke;

import duke.command.Command;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Represents a storage system that is responsible for
 *
 */
public class Storage {
    private String fileName;

    public Storage(String filePath) throws DukeException {
        this.fileName = filePath;
        try {
            File file = new File(this.fileName);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read() throws IOException {
        return Files.readString(Path.of(this.fileName));
    }

    public void save(TaskList list) throws DukeException{
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
