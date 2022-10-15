package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    File file;
    public Storage() {
        try {
            Files.createDirectories(Paths.get("data"));
            this.file = new File("data/tasks.txt");
            file.createNewFile();
        } catch (IOException e) {
        }
    }

    public TaskList loadFile() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        TaskList tasks = new TaskList();
        while (sc.hasNextLine()) {

            String taskType = sc.nextLine();
            if (taskType.isBlank()) {
                continue;
            }

            String taskName = sc.nextLine();
            String doneIndicator = sc.nextLine();
            boolean isDone = Integer.parseInt(doneIndicator) == 1;

            Task task;
            if (taskType.equals("T")) {
                task = new Todo(taskName);

            } else if (taskType.equals("D")) {
                String deadline = sc.nextLine();
                task = new Deadline(taskName, deadline);
            } else if (taskType.equals("E")){
                String eventTime = sc.nextLine();
                task = new Event(taskName, eventTime);
            } else {
                throw new RuntimeException("Duke cannot understand the input file.");
            }

            if (isDone) {
                task.mark();
            }
            tasks.add(task);
        }
        return tasks;
    }

    public void saveFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(file);
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            fw.write(task.saveStringToFile());
        }
        fw.close();
    }
}
