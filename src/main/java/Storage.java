import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> readData() {
        ArrayList<Task> taskList = new ArrayList<>();
        File f = new File(this.filePath);
        try {
//            if (!f.createNewFile()) {
//                System.out.println("File already exists.");
//            }
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
                taskList.add(task);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }

    public void writeData(ArrayList<Task> taskList) {
        System.out.println("Saving data...");
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : taskList) {
                String holder;
                if (task instanceof Todo) {
                    holder = String.format("T | %s | %s", task.getStatus(),task.description);
                } else if (task instanceof Deadline) {
                    Deadline deadlineTask = (Deadline) task;
                    holder = String.format("D | %s | %s | %s",
                            deadlineTask.getStatus(), deadlineTask.description, deadlineTask.getDate());
                } else {
                    Event eventTask = (Event) task;
                    holder = String.format("D | %s | %s | %s",
                            eventTask.getStatus(), eventTask.description, eventTask.getDate());
                }
                fileWriter.write(holder + "\n");
            }
            fileWriter.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    }
