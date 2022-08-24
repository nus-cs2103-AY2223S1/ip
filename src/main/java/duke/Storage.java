package duke;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Storage {
    private final File file;

    public Storage(String path) {
        this.file = new File(path);
        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            try {
                this.file.createNewFile();
            } catch (IOException exception) {
                System.out.println("Error creating data file.");
            }
        }
    }

    public String read() {
        try {
            Scanner scanner = new Scanner(this.file);
            StringBuilder fileTasks = new StringBuilder();
            if (!scanner.hasNextLine()) {
                return null;
            }
            fileTasks.append(scanner.nextLine());
            while (scanner.hasNextLine()) {
                fileTasks.append(System.lineSeparator());
                fileTasks.append(scanner.nextLine());
            }
            scanner.close();
            return fileTasks.toString();
        } catch (FileNotFoundException exception) {
            throw new RuntimeException("File not found when trying to read data file.");
        }
    }

    public void write(String contents) {
        try {
            FileWriter fileWriter = new FileWriter(this.file);
            fileWriter.write(contents);
            fileWriter.close();
        } catch (FileNotFoundException exception) {
            throw new RuntimeException("File not found when trying to write to data file.");
        } catch (IOException exception) {
            throw new RuntimeException("Error when trying to write to data file");
        }
    }
}