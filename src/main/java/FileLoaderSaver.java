import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.io.FileWriter;

public class FileLoaderSaver {
    /*
     * Returns List of inputs
     * 
     * @param filePath path of file needed
     * @return List<String> returns the each line of input in a List after reading the file
     * @Throws IOException
     */
    public static List<String> loadFile(String file) throws IOException{
        Path filePath = Path.of(file);
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
    public static void writeToFile(String filePath, String txtFile) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write(txtFile);
        }
    }
}
