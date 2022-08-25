import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Belly {
    public static final String fileDirectory = "../data/";
    public static final String fileName = "duke.txt";
    public static final String filePath = fileDirectory + fileName;

    public ArrayList<Task> puke() throws DukeException {
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            ArrayList<Task> taskList = new ArrayList<>();

            String[] taskDetails;
            Task task;
            while (sc.hasNextLine()) {
                taskDetails = sc.nextLine().split(" @@ ");

                String taskCode = taskDetails[0];
                String taskIsDone = taskDetails[1];
                String taskName = taskDetails[2];
                String taskDatetimeString;

                switch (taskCode) {
                case "T":
                    task = new Todo(taskIsDone, taskName);
                    break;
                case "D":
                    taskDatetimeString = taskDetails[3];
                    task = new Deadline(taskIsDone, taskName, TaskDatetimeFormatter.stringToDatetime(taskDatetimeString));
                    break;
                default:
                    taskDatetimeString = taskDetails[3];
                    task = new Event(taskIsDone, taskName, TaskDatetimeFormatter.stringToDatetime(taskDatetimeString));
                }

                taskList.add(task);
            }
            sc.close();

            return taskList;
        } catch (FileNotFoundException e) {
            throw DukeException.fileNotFoundError(filePath);
        }
    }

    public void saveToHardDisk(String txt) {
        try {
            Files.createDirectories(Paths.get(fileDirectory));
            File file = new File(filePath);
            file.createNewFile();

            PrintWriter out = new PrintWriter(filePath);
            out.println(txt);
            out.close();
        } catch (IOException e) {
            ;
        }
    }
}
