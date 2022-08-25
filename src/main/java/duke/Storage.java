package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {
    private File f;
    private String dirPath;
    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
        this.f = new File(filePath);
        String [] pathDirs = filePath.split("/");
        this.dirPath = String.join("/", Arrays.copyOfRange(pathDirs,0,pathDirs.length - 1));
    }

    protected String load() throws DukeException {
        try {
            Scanner s = new Scanner(this.f);
            StringBuilder data = new StringBuilder();
            while (s.hasNext()) {
                if (!data.toString().equals("")) {
                    data.append("\n");
                }
                String input = s.nextLine();
                data.append(input);
            }
            return data.toString();
        } catch (FileNotFoundException e) {
            throw new DukeException("File could not be found");
        }
    }
    public void save(String data) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("File could not be saved");
        }
    }
    protected void createFile() throws DukeException {
        try {
            File newDirectory = new File(dirPath);
            newDirectory.mkdirs();
            f.createNewFile();
        } catch (IOException e) {
            throw new DukeException("File could not be created");
        }
    }
}
