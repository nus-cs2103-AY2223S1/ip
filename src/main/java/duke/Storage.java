package duke;

import duke.exception.ReadFileException;
import duke.exception.WriteFileException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    private final Path path;

    public Storage(String path) {
        this.path = Paths.get(path);
        initialize();
    }

    private void initialize() {
        checkDirectory();
        checkFile();
    }

    public Path getPath() {
        return path;
    }

    public void write(String content) {
        initialize();
        try {
            FileWriter fileWriter = new FileWriter(this.path.toFile());
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ioException) {
            throw new WriteFileException(this.path, "Unable to write the file.");
        }
    }

    public String read() {
        String result = "";
        initialize();
        try {
            Scanner scanner = new Scanner(this.path);
            while (scanner.hasNextLine()) {
                result = result + scanner.nextLine() + System.lineSeparator();
            }
        } catch (IOException ioException) {
            throw new ReadFileException(this.path, "Unable to read the file.");
        }
        return result;
    }

    private void checkDirectory() {
        File temp = this.path.getParent().toFile();
        if (!temp.exists()) {
            temp.mkdirs();
        }
    }

    private void checkFile() {
        File temp = this.path.toFile();
        try {
            if (!temp.exists()) {
                temp.createNewFile();
            }
        } catch (IOException ioException) {
            throw new ReadFileException(this.path, "Unable to create new files.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Storage) {
            Storage s = (Storage) obj;
            if (this.path == s.getPath()) {
                return true;
            }
            if (this.path == null || s.getPath() == null) {
                return false;
            }
            return this.path.equals(s.path);
        }
        return false;
    }
}
