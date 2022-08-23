package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String folderPath;
    private final String filePath;

    public Storage(String folderPath, String filePath) {
        this.folderPath = folderPath;
        this.filePath = filePath;
    }

    public void saveTaskList(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskList.size(); i++) {
                fw.write(taskList.get(i).toSaveString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void startUpPullStorage(UI ui, TaskList taskList) {
        createFolder();
        createFile();
        pullSavedTaskList(ui, taskList);
    }

    private void createFolder() {
        new File(folderPath).mkdir();
    }

    private void createFile() {
        try {
            new File(filePath).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pullSavedTaskList(UI ui, TaskList taskList) {
        File save = new File(filePath);
        try {
            Scanner s = new Scanner(save);
            while (s.hasNextLine()) {
                Parser.parseSavedInput(s.nextLine()).execute(taskList, ui, this);
            }
        } catch (FileNotFoundException | DukeException e) {
            throw new RuntimeException(e);
        }
    }
}
