package duke;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import duke.tasks.Task;

/**
 * Represents the storage component of the Duke chatbot. The Storage class handles then reading and writing
 * of the file that stores the content of the Duke chatbot. Each <code>Storage</code> object contains a
 * <code>Path</code> and a <code>File</code>
 */
public class Storage {
    private Path path;
    private File f;

    /**
     * Constructor method of Storage
     * @param pathname
     */
    public Storage(String pathname) {
        this.f = new File(pathname);
        this.path = f.toPath();

    }

    /**
     * loads the file that is contained in the storage object
     * @return File object that is contained in the Storage object
     */
    public File load() {
        if (f.exists()) {
            this.path = f.toPath();
            return f;
        } else {
            throw new DukeException("No File Loaded");
        }

    }

    /**
     * Writes the contents in the tasklist into the save file
     * @param tasks list of tasks
     */
    public void write(TaskList tasks) {
        String s = "";
        for (Task t : tasks.getList()) {
            s += t.toFile() + "\n";
        }
        try {
            Files.writeString(path, s, StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
