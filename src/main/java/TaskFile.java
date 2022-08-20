import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskFile {
    protected String filePath = "data/tasklist.txt";

    private void createFile(File file) throws DukeException {
        new File("data").mkdir();
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Something went wrong! Please try again.");
        }
    }

    public void saveToFile(TaskList taskList) throws DukeException {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : taskList.tasks) {
                writer.write(task + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Something went wrong! Please try again.");
        }
    }

    public void readFile(TaskList taskList) throws DukeException {
        File file = new File(filePath);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String type = line.substring(1, 2);
                String details = line.substring(7);

                Task task;
                if (type.equals("T")) {
                    task = new ToDo(details);
                } else {
                    boolean isDeadline = type.equals("D");
                    int pos = details.indexOf(isDeadline ? " (by: " : " (at: ");
                    String description = details.substring(0, pos);
                    String when = details.substring(pos + 6, details.length() - 1);
                    task = isDeadline ? new Deadline(description, when) : new Event(description, when);
                }
                taskList.addTaskFromFile(task);

                String status = line.substring(4, 5);
                task.changeStatus(status.equals("X"), false);
            }
        } catch (IOException e) {
            createFile(file);
        }
    }
}
