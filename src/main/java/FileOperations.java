import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileOperations {
    File f;

    public FileOperations(String filePath) {
        f = new File(filePath);
    }
    public void createFile() throws IOException {
        f.createNewFile();
    }

    public void printFileContents() throws FileNotFoundException {
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public void writeToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public void rewriteFile(String filePath, List<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t: taskList) {
            String textToAdd = t.getTaskType() + " | " + t.getStatus() + " | " + t.getDescription();
            if (t instanceof Todo) {
                textToAdd += "\n";
            }
            if (t instanceof Event) {
                Event e = (Event) t;
                textToAdd += " | " + e.getDate() + "\n";
            } else if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                textToAdd += " | " + d.getDeadline() + "\n";
            }
            fw.write(textToAdd);
        }
        fw.close();
    }

    public List<Task> readFile() throws FileNotFoundException {
        List<Task> tasksInFile = new ArrayList<>();
        //File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String task = s.nextLine();
            String taskType = String.valueOf(task.charAt(0));
            boolean taskStatus = String.valueOf(task.charAt(4)) == "X";
            switch (taskType) {
                case "T": {
                    String taskDescription = task.substring(8);
                    Task todo = new Todo(taskDescription, taskType);
                    todo.changeStatus(taskStatus);
                    tasksInFile.add(todo);
                    break;
                }
                case "E": {
                    int indexOfLastSeparator = task.indexOf("|", 8);
                    String taskDescription = task.substring(8,indexOfLastSeparator);
                    String date = task.substring(indexOfLastSeparator + 1);
                    Task event = new Event(taskDescription, date, taskType);
                    event.changeStatus(taskStatus);
                    tasksInFile.add(event);
                    break;
                }
                case "D": {
                    int indexOfLastSeparator = task.indexOf("|", 8);
                    String taskDescription = task.substring(8,indexOfLastSeparator);
                    String deadlineDate = task.substring(indexOfLastSeparator + 1);
                    Task deadline = new Deadline(taskDescription, deadlineDate, taskType);
                    deadline.changeStatus(taskStatus);
                    tasksInFile.add(deadline);
                    break;
                }
            }
        }
        return tasksInFile;
    }
}
