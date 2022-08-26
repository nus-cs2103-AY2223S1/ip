package duke.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import duke.exception.DukeException;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;

public class Storage {
    private final String filePath;
    private File dataFile;
    private FileWriter fw;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> loadData() {
        try {
            File dataFolder = new File(filePath.split("/", 2)[0]);
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }
            File f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
            }
            dataFile = f;
            Scanner s = new Scanner(dataFile);
            List<Task> tasks = new ArrayList<>();
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] tempArr = line.split(" \\| ");
                Task t;
                switch (tempArr[0]) {
                case "T":
                    t = new Todo(tempArr[2]);
                    break;
                case "D":
                    t = new Deadline(tempArr[2], tempArr[3]);
                    break;
                case "E":
                    t = new Event(tempArr[2], tempArr[3]);
                    break;
                default:
                    throw new DukeException("Invalid data in duke.txt");
                }
                if (tempArr[1].equals("1")) {
                    t.mark();
                }
                tasks.add(t);
            }
            fw = new FileWriter(filePath, false);
            return tasks;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateData(TaskList tasks) throws IOException {
        String newText = "";
        for (int i = 0; i < tasks.size(); i++) {
            String s = tasks.get(i).getStringToSave();
            if (i == 0) {
                newText += s;
            } else {
                newText +='\n' + s;
            }
        }
        fw.write(newText);
        fw.close();
    }
}
