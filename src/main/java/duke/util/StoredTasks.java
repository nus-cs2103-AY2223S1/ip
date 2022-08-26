package duke.util;

import duke.DukeException;
import duke.task.TaskList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StoredTasks {
    private String fileDir;
    private String filePath;

    public StoredTasks(String fileDir, String filePath) {
        this.fileDir = fileDir;
        this.filePath = filePath;
    }

    public BufferedReader load() throws DukeException {
        BufferedReader br;
        try {
            File dir = new File(this.fileDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File fileName = new File(this.filePath);
            if (!fileName.exists()) {
                fileName.createNewFile();
            }
            br = new BufferedReader(new FileReader(fileName));
        } catch (IOException e) {
            throw new DukeException("Failure in reading file, creating new save file");
        }
        return br;
    }

    public void save(TaskList storedTasks) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(storedTasks.taskListToSaveString());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
