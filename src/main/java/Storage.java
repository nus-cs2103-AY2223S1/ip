import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    protected String filePath;
    protected File taskFile;
    FileWriter fileWriter;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(String taskList) {
        try {
            fileWriter = new FileWriter("data/taskList.txt");
            fileWriter.write(taskList);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TaskList load() {
        TaskList result = new TaskList();

        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }

            File taskFile = new File(filePath);
            if (!taskFile.exists()) {
                taskFile.createNewFile();
            }

            Scanner scanner = new Scanner(taskFile);
            while (scanner.hasNextLine()) {
                String task = scanner.nextLine();
                String[] taskData = task.split(",");

                switch (taskData[0]) {
                    case "T":
                        ToDos todo = new ToDos(taskData[2]);
                        todo.setIsDone(taskData[1].equals("1"));
                        result.add(todo);
                        System.out.println("adds todo");
                        break;
                    case "D":
                        Deadline deadline = new Deadline(taskData[2],taskData[3]);
                        deadline.setIsDone(taskData[1].equals("1"));
                        result.add(deadline);
                        System.out.println("adds deadline");
                        break;
                    case "E":
                        Event event = new Event(taskData[2], taskData[3]);
                        event.setIsDone(taskData[1].equals("1"));
                        result.add(event);
                        System.out.println("adds event");
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
