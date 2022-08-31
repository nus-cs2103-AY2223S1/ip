import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Storage {
    private final Path storagePath;

    public Storage(Path storagePath) {
        this.storagePath = storagePath;
    }

    public Path getPath() {
        return this.storagePath;
    }

    public void addTask(String taskDescription) {
        try {
            Files.writeString(storagePath, taskDescription, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void update(String[] taskDescriptionArray) {
        StringBuilder newTaskListText = new StringBuilder();

        for (String task: taskDescriptionArray) {
            newTaskListText.append(task);
        }
        try {
            Files.writeString(this.storagePath, newTaskListText.toString());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
