import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> loadTasks() throws JarvisException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(this.filePath);

        if (file.exists()) {
            try {
                Scanner sc = new Scanner(file);
                Task task;

                while (sc.hasNextLine()) {
                    String[] split = sc.nextLine().split("\\|");
                    switch (split[0]) {
                    case "T":
                        task = new ToDo(split[2]);
                        break;
                    case "D":
                        task = new Deadline(split[2], split[3]);
                        break;
                    case "E":
                        task = new Event(split[2], split[3]);
                        break;
                    default:
                        throw new JarvisException("Failed to load task\n");
                    }

                    if (split[1].equals("1")) {
                        task.setIsDone(true);
                    }

                    tasks.add(task);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        return tasks;
    }

    public void saveTasks(TaskList tasks) {
        File file = new File(this.filePath);
        file.getParentFile().mkdirs();

        try {
            FileWriter fileWriter = new FileWriter(file);
            for (Task task: tasks.getTasks()) {
                StringBuilder taskString = new StringBuilder();
                if (task instanceof ToDo) {
                    taskString.append("T|");
                } else if (task instanceof Deadline) {
                    taskString.append("D|");
                } else if (task instanceof Event) {
                    taskString.append("E|");
                }

                if (task.isDone) {
                    taskString.append("1|");
                } else {
                    taskString.append("0|");
                }

                taskString.append(task.getDescription());

                if (task instanceof Deadline) {
                    taskString.append("|");
                    taskString.append(((Deadline) task).getBy());
                } else if (task instanceof Event) {
                    taskString.append("|");
                    taskString.append(((Event) task).getAt());
                }

                taskString.append(System.lineSeparator());
                fileWriter.write(taskString.toString());
            }

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
