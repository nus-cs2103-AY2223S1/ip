package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    public String load() throws DukeException {
        Scanner sc = null;
        StringBuilder res = new StringBuilder();
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                res.append(sc.nextLine()).append("\n");
            }
            return res.toString();
        } catch (FileNotFoundException e) {
            this.createFile();
            return this.load();
        } finally {
            if (sc != null) sc.close();
        }
    }

    public void createFile() throws DukeException {
        try {
            if (!file.createNewFile()) {
                throw new DukeException("File already exist.");
            }
        } catch (IOException e) {
            throw new DukeException("An error occurred when creating new file.");
        }
    }

    public void overwriteFile(String textToReplace) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToReplace);
            fw.close();
        } catch (IOException e) {
            // handle exception
            System.out.println("An error occurred when saving the changes to file.");
        }
    }
}
