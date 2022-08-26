package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.exception.ReadFileException;
import duke.exception.WriteFileException;

/**
 * The IO part of Duke.
 */
public class Storage {
    /**
     * The Path of the file.
     */
    private final Path path;

    /**
     * The constructor of the Class.
     * @param path
     */
    public Storage(String path) {
        this.path = Paths.get(path);
        initialize();
    }

    /**
     * To check whether directory or file exists and create if not exist.
     */
    private void initialize() {
        checkDirectory();
        checkFile();
    }

    /**
     * Return the path of the Storage.
     * @return The path of the Storage.
     */
    public Path getPath() {
        return path;
    }

    /**
     * Write given content the Path.
     * @param content The given content.
     */
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

    /**
     * Read given content from the Path.
     * @return The content in the Path. Returns empty String if file does not exist.
     */
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

    /**
     * Check whether directory exists. Create if does not exist.
     */
    private void checkDirectory() {
        File temp = this.path.getParent().toFile();
        if (!temp.exists()) {
            temp.mkdirs();
        }
    }

    /**
     * Check whether file exists. Create if does not exist.
     */
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

    /**
     * Return boolean indicating whether this object
     * is equivalent to another object.
     *
     * @param obj The object to be checked.
     * @return The boolean whether the given object is equivalent to this object.
     */
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
