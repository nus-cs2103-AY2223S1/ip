package alpha;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileOperations {
    File f;
    final String FILE_PATH;

    public FileOperations(String FILE_PATH) {
        this.FILE_PATH = FILE_PATH;
        f = new File(FILE_PATH);
    }
    public void createFile() throws IOException {
        f.createNewFile();
    }

    public void writeToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public void rewriteFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task t: taskList.tasks) {
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
                    String date = task.substring(indexOfLastSeparator + 2);
                    Task event = new Event(taskDescription, date, taskType);
                    event.changeStatus(taskStatus);
                    tasksInFile.add(event);
                    break;
                }
                case "D": {
                    int indexOfLastSeparator = task.indexOf("|", 8);
                    String taskDescription = task.substring(8,indexOfLastSeparator);
                    String deadlineDate = task.substring(indexOfLastSeparator + 2);
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
