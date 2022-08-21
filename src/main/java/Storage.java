import java.io.*;
import java.util.List;

public class Storage {
    private static String path = "./data/duke.txt";
    public static void loadTasks() {
        try {
            File dir = new File("./data");
            dir.mkdir();

            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileReader fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader);

            String next = reader.readLine();

            while (next != null) {
                String[] section = next.split(" \\| ");
                boolean isDone = section[1].equals("1");
                String type = section[0];

                if(type.equals("T")) {
                    try {
                        Todo todo = new Todo(section[2], isDone);
                        todo.add();
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (type.equals("D")) {
                    Deadline deadline = new Deadline(section[2], isDone, section[3]);
                    deadline.add();
                } else {
                    Event event = new Event(section[2], isDone, section[3]);
                    event.add();
                }
                next = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateTasks(List<Task> list) {
        try {
            FileWriter fileWriter = new FileWriter(path, false);
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
