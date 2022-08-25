import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.io.FileWriter;

public class FileLoaderSaver {
    private Path filePath;

    FileLoaderSaver(String filePath) {
        this.filePath = Path.of(filePath);
    }

    /*
     * Returns List of inputs
     * 
     * @param filePath path of file needed
     * @return List<String> returns the each line of input in a List after reading the file
     * @Throws IOException
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

    /*
     * Saves file into ToDoList
     * 
     * @param pathName path of file to write to
     * @param txtFile string of text to write to file
     * @Throws IOException
     */
    public void writeToFile(String txtFile) throws IOException {
        try (FileWriter fw = new FileWriter(filePath.toString())) {
            fw.write(txtFile);
        }
    }
}
