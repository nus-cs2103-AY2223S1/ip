package duke.util;

//import io
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

//import util
import java.util.Scanner;

//import iohelper
import iohelper.IoHelper;


public class Storage {
    private File dukeFile;
    private FileWriter fileWriter;
    private Scanner scanner;

    public Storage(String fileName) {
        dukeFile = new File(fileName);
    }

    private boolean isFilePresent() {
        return dukeFile.isFile();
    }

    public TaskList setUp(Ui ui) {
        if (isFilePresent()) {
            return new TaskList(load(ui));
        }
        try {
            dukeFile.createNewFile();
        } catch (IOException e) {
            ui.show(e);
        } finally {
            return new TaskList();
        }
    }

    public void update(Object obj, Ui ui) {
        try {
            fileWriter = new FileWriter(dukeFile);
            fileWriter.write(obj.toString());
            ui.show("Storage: duke.txt updated");
            fileWriter.close();
        } catch (IOException e) {
            ui.show(e);
        }
    }

    private String load(Ui ui) {
        String data = "";
        try {
            scanner = new Scanner(dukeFile);
            while (scanner.hasNextLine()) {
                data += scanner.nextLine() + '\n';
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            ui.show(e);
        }
        return data;
    }
}
