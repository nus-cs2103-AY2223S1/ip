import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static final String DEFAULT_SAVE_PATH = "SavedData.duke";
    private String filePath;
    private File file;

    Storage(File file) {
        this.file = file;
        filePath = file.getAbsolutePath();
    }

    static Storage createStorage(String path) throws IOException {
        File newFile = new File(path);
        newFile.createNewFile();
        return new Storage(newFile);
    }

    static Storage createStorage() throws IOException {
        File newFile = new File(DEFAULT_SAVE_PATH);
        newFile.createNewFile();
        return new Storage(newFile);
    }

    List<ParsedData> readFile() throws FileNotFoundException {
        List<ParsedData> ret = new ArrayList<>();
            Scanner sc = new Scanner(file);
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                try {
                    ret.add(Parser.parseDataFromLine(line));
                } catch (CorruptedLineException e) {
                    // TODO print err
                }
            }
        return ret;
    }



    
}
