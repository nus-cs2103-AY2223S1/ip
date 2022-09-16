package duke;

import duke.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String path;

    public Storage(String path) {
        this.path = path;
    }

    public TaskList load() {
        File file = new File(path);
        TaskList tasks = new TaskList();
        Scanner sc;
        try {
            file.createNewFile();
            sc = new Scanner(file);
        } catch (IOException e) {
            return tasks;
        }
        while (sc.hasNext()) {
            tasks.add(Parser.parseStorageTask(sc.nextLine()));
        }
        return tasks;
    }

    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter("./duke.txt");
            fw.write(tasks.data());
            fw.close();
        } catch (IOException e) {

        }
    }
}
