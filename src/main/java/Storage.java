import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private File DIRECTORY;
    private String FILEPATH;

    public Storage(String directoryPath, String filename) {
        this.DIRECTORY = new File(directoryPath);
        this.FILEPATH = directoryPath + "/" + filename;
    }

    public File retrieveFile() {
        return new File(this.FILEPATH);
    }

    public void writeToFile(String fileData) throws DukeException {
        if (!this.DIRECTORY.exists()) {
            this.DIRECTORY.mkdir();
        }

        try {
            FileWriter fw = new FileWriter(this.FILEPATH);
            fw.write(fileData);
            fw.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Duke could not create a file to save your tasks :(");
        } catch (IOException e) {
            throw new DukeException(String.format("Duke had some trouble saving your file: %s", e.getMessage()));
        }
    }
}
