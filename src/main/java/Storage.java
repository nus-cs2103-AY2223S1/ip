import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String path;

    public Storage(String path) {
        this.path = path;
    }
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<Task>();
        try {
            File dir = new File("./data");
            dir.mkdir();

            File file = new File(this.path);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileReader fileReader = new FileReader(this.path);
            BufferedReader reader = new BufferedReader(fileReader);

            String next = reader.readLine();

            while (next != null) {
                String[] section = next.split(" \\| ");
                boolean isDone = section[1].equals("1");
                String type = section[0];

                if(type.equals("T")) {
                    try {
                        Todo todo = new Todo(section[2], isDone);
                        tasks.add(todo);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (type.equals("D")) {
                    Deadline deadline = new Deadline(section[2], isDone, section[3]);
                    tasks.add(deadline);
                } else {
                    Event event = new Event(section[2], isDone, section[3]);
                    tasks.add(event);
                }
                next = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    public void updateTasks(List<Task> list) {
        try {
            FileWriter fileWriter = new FileWriter(this.path, false);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            list.forEach((task) -> {
                try {
                    writer.write(task.getStorageString());
                    writer.newLine();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
