import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

// referenced from
// https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/StorageFile.java
public class Storage {
    private final Path path;

    public Storage(String filepath) {
        path = Paths.get(filepath);
    }


    public void save(TaskList tasks) {
        try {
            Files.write(path, tasks.saveToStorage());
        } catch (IOException e) {
            System.out.println("Oops! Problem encountered! " +
                    "Wanya cannot save the task to hard disk.");
        }
    }

    public List<String> load() throws WanyaException {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new WanyaException("Oops! File not found hehe maybe a spy stole the file awayyy...\n" +
                    "Wanya shall create a new list for you");
        }
    }
}
