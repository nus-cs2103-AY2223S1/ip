package duke;

import duke.tasks.Task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Storage {
    Path path;
    File f;

    public Storage(String pathname) {
        this.f = new File(pathname);
        this.path = f.toPath();

    }
    public File load() {
        if (f.exists()) {
            this.path = f.toPath();
            return f;
        } else {
            throw new DukeException("No File Loaded");
        }

    }

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
