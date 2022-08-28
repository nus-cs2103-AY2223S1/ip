package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String saveFilePath;
    private String saveDirectoryPath;

    public Storage(String saveDirectoryPath, String saveFilePath) {
        this.saveDirectoryPath = saveDirectoryPath;
        this.saveFilePath = saveFilePath;
    }
    public void writeToFile(String textToSave) throws IOException {
        FileWriter fw = new FileWriter(this.saveFilePath);
        fw.write(textToSave);
        fw.close();
    }

    public void checkExistsOrCreateNewFile(TaskList tasklist) throws IOException, FileNotFoundException {
        File f = new File(this.saveFilePath);
        if (f.exists()) {
            readFile(f, tasklist);
        } else {
            File dir = new File(this.saveDirectoryPath);
            dir.mkdir();
            f.createNewFile();
        }
    }

    private void readFile(File f, TaskList tasklist) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        String tempLine = "";
        while (s.hasNext()) {
            tempLine = s.nextLine();
            String[] tempWords = tempLine.split(" , ");
            boolean isCompleted = tempWords[1].contains("true");
            if (tempWords[0].equals("T")) {
                tasklist.appendToDoFromFile(tempWords[2], isCompleted);
            } else if (tempWords[0].equals("E")) {
                tasklist.appendEventFromFile(tempWords[2], tempWords[3], isCompleted);
            } else if (tempWords[0].equals("D")) {
                tasklist.appendDeadlineFromFile(tempWords[2], tempWords[3], isCompleted);
            }
        }
    }
}
