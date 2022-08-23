package duke;

import duke.exception.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private File directory;
    private String filepath;

    public Storage(String directoryPath, String filename) {
        this.directory = new File(directoryPath);
        this.filepath = directoryPath + "/" + filename;
    }

    public File retrieveFile() {
        return new File(this.filepath);
    }

    public void writeToFile(String fileData) throws DukeException {
        if (!this.directory.exists()) {
            this.directory.mkdir();
        }

        try {
            FileWriter fw = new FileWriter(this.filepath);
            fw.write(fileData);
            fw.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Duke could not create a file to save your tasks :(");
        } catch (IOException e) {
            throw new DukeException(String.format("Duke had some trouble saving your file: %s", e.getMessage()));
        }
    }
}
