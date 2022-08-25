import TaskTypes.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String path;
    File file;

    public Storage(String path) {
        this.path = path;
        this.file = new File(this.path);

    }

    public void save(ArrayList<Task> tasks) throws IOException {

        file.createNewFile();

        FileWriter fileWriter = new FileWriter(this.path);
        for (Task task : tasks) {
            fileWriter.write(task.toSaveString() + System.lineSeparator());
        }
        fileWriter.close();
    }

    public ArrayList<Task> load() throws Exception {
        ArrayList<Task> tasks = new ArrayList<>();
        Parser parser = new Parser();

        file.createNewFile();

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            try {
                Task parsedTask = parser.parseSave(scanner.nextLine());
                tasks.add(parsedTask);
            } catch (RuntimeException e) {
                throw e;
            }
        }

        return tasks;
    }
}
