import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class Storage {
    private final IoHelper ioHelper = new IoHelper();
    private File dukeFile;
    private FileWriter fileWriter;
    private Scanner scanner;

    public Storage(String fileName) {
        dukeFile = new File(fileName);
    }

    private boolean isFilePresent() {
        return dukeFile.isFile();
    }

    public TaskList setUp() {
        if (isFilePresent()) {
            return new TaskList(load());
        }
        try {
            dukeFile.createNewFile();
        } catch (IOException e) {
            ioHelper.printError(e);
        } finally {
            return new TaskList();
        }
    }

    public void update(Object obj) {
        try {
            fileWriter = new FileWriter(dukeFile);
            fileWriter.write(obj.toString());
            ioHelper.print("Storage: duke.txt updated");
            fileWriter.close();
        } catch (IOException e) {
            ioHelper.printError(e);
        }
    }

    private String load() {
        String data = "";
        try {
            scanner = new Scanner(dukeFile);
            while (scanner.hasNextLine()) {
                data += scanner.nextLine() + '\n';
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            ioHelper.printError(e);
        }
        return data;
    }
}
