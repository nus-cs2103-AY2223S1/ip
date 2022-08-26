package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {

    private File file;
    private String directoryPath;
    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
        String [] directoryPaths = filePath.split("/");
        this.directoryPath = String.join("/", Arrays.copyOfRange(
                directoryPaths,0,directoryPaths.length - 1));
    }

    protected String load() throws DukeException {
        try {
            Scanner s = new Scanner(this.file);
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
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("File could not be saved");
        }
    }

    protected void createFile() throws DukeException {
        try {
            File newDirectory = new File(directoryPath);
            newDirectory.mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("File could not be created");
        }
    }
}
