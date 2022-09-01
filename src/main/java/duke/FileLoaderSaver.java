package duke;

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
    private Path filePath;

    /**
     * Constructs duke.FileLoaderSaver object
     * @param filePath path to saving and loading location
     */
    public FileLoaderSaver(String filePath) {
        this.filePath = Path.of(filePath);
    }

    /**
     * Returns list of saved tasks in String to be decoded
     * @return list of saved tasks in String
     * @throws IOException
     */
    public List<String> loadFile() throws IOException{
        //Find existing toDoList
        if (!Files.exists(filePath))
        {
            Files.createFile(filePath);
        }
        List<String> txtFile = Files.readAllLines(filePath);
        return txtFile;
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
