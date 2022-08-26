package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void readData() throws DukeException {
        File f = new File(this.filePath);
        try {
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String[] taskStrArray = sc.nextLine().split(" \\| ");
                Task task = new Task("");
                if (taskStrArray[0].contains("T")) {
                    task = new Todo(taskStrArray[2]);
                } else if (taskStrArray[0].contains("D")) {
                    task = new Deadline(taskStrArray[2], taskStrArray[3]);
                } else if (taskStrArray[0].contains("E")) {
                    task = new Event(taskStrArray[2], taskStrArray[3]);
                }
                if (taskStrArray[1].contains("1")) {
                    task.mark();
                }
                TaskList.taskList.add(task);
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public void writeData() {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : TaskList.taskList) {
                String holder;
                if (task instanceof Todo) {
                    holder = String.format("T | %s | %s", task.getStatus(), task.description);
                } else if (task instanceof Deadline) {
                    Deadline deadlineTask = (Deadline) task;
                    holder = String.format("D | %s | %s | %s",
                            deadlineTask.getStatus(), deadlineTask.description, deadlineTask.getOldDate());
                } else {
                    Event eventTask = (Event) task;
                    holder = String.format("D | %s | %s | %s",
                            eventTask.getStatus(), eventTask.description, eventTask.getOldDate());
                }
                fileWriter.write(holder + "\n");
            }
            fileWriter.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
