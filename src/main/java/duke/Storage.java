package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            System.out.println(s.nextLine());
        }
    }

    public static void formatToList(File f, ArrayList<Task> taskList) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        try {
            while (s.hasNextLine()) {
                String[] taskDescription = s.nextLine().split(" \\| ");
                String taskType = taskDescription[0];
                boolean isDone = taskDescription[1].equals("1");
                String description = taskDescription[2] + " ";
                String date = " ";
                Task task = null;
                if (taskDescription.length == 4) {
                    date += taskDescription[3];
                }
                if (taskType.equals("T")) {
                    task = new ToDo(description, isDone);
                }
                if (taskType.equals("D")) {
                    task = new Deadline(description, isDone, date);
                }
                if (taskType.equals("E")) {
                    task = new Event(description, isDone, date);
                }
                if (isDone) {
                    task.markAsDone();
                }
                taskList.add(task);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                Path path = Paths.get("./data/");
                Files.createDirectories(path);
                file.createNewFile();
            }
            printFileContents(this.filePath);
            formatToList(file, taskList);
        } catch (FileNotFoundException e) {
            throw new DukeException("Error: Cannot load file!");
        } catch (IOException e) {
            throw new DukeException("Error: File does not exist");
        }
        return taskList;
    }

    public static String formatToFile(TaskList taskList) {
        String result = "";
        for (Task t : taskList.getTaskList()) {
            result += t.fileStatus() + "\n";
        }
        return result;
    }

    public void save(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(formatToFile(taskList));
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
