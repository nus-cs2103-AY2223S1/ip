package spongebob;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Scanner;

import spongebob.task.Task;

/**
 * Represents a storage class for I/O operations.
 */
public class Storage {
    private final String filepath = "data/spongebob.txt";

    /**
     * Creates spongebob.txt at specified file path if file not available.
     */
    private void createFile() {
        try {
            Path file = Path.of(filepath);
            boolean isFileExists = Files.exists(file) && Files.isRegularFile(file);
            if (!isFileExists) {
                Path parentDir = file.getParent();
                if (parentDir != null) {
                    Files.createDirectories(parentDir);
                }
                Files.createFile(file);
            }
        } catch (InvalidPathException ipe) {
            System.out.println("Invalid file path.");
        } catch (IOException e) {
            System.out.println("Unable to create file.");
        }
    }

    /**
     * Reads file at filePath.
     *
     * @return Scanner sc
     */
    public Scanner load() {
        try {
            createFile();
            File f = new File(filepath);
            Scanner sc = new Scanner(f);
            return sc;
        } catch (FileNotFoundException fileNotFoundError) {
            System.out.println("Error in loading data. File not found.");
            return new Scanner("");
        }
    }

    /**
     * Writes content to file at filePath.
     *
     * @param it Iterator of the arrayList from TaskList.
     */
    public String save(Iterator<Task> it) {
        try {
            FileWriter fw = new FileWriter(filepath, false);
            while (it.hasNext()) {
                fw.write(it.next().toStringSaveFormat());
            }
            fw.close();
            return "Successfully saved contents into spongebob.txt";
        } catch (IOException e) {
            return "Error in saving data.";
        }
    }
}
