package duke;

import java.io.File;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.io.FileWriter;

/**
 * duke.FileLoaderSaver class loads and saves a given String of code into a txt file in path
 * @author Shaune Ang
 */
public class  FileLoaderSaver {
    private File filePath;
    private Path path;

    /**
     * Constructs duke.FileLoaderSaver object
     * @param filePath path to saving and loading location
     */
    public FileLoaderSaver(String filePath) {
        this.filePath = new File(filePath);
        path = Path.of(filePath);
    }

    /**
     * Returns list of saved tasks in String to be decoded
     * @return list of saved tasks in String
     * @throws IOException
     */
    public List<String> loadFile() throws IOException {
        //Find existing toDoList
        checkFileExists();

        List<String> txtFile = Files.readAllLines(path);
        return txtFile;
    }

    /**
     * Checks if file at end of filepath exists, else creates a new file
     * @throws IOException
     */
    private void checkFileExists() throws IOException{
        if (new File("data").mkdir()) {
            new File("data/duke.txt").createNewFile();
        } else if (!filePath.exists()) {
            filePath.createNewFile();
        }
    }

    /**
     * Saves file into a new txtFile at a path
     * @param txtFile String of text to write into the text file
     * @throws IOException
     */
    public void writeToFile(String txtFile) throws IOException {
        try (FileWriter fw = new FileWriter(filePath.toString())) {
            fw.write(txtFile);
        }
    }
}
