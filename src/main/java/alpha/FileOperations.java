package alpha;

import alpha.task.Deadline;
import alpha.task.Event;
import alpha.task.Task;
import alpha.task.Todo;

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
    public void createFile() throws AlphaException {
        try {
            f.createNewFile();
        } catch (IOException e) {
            throw new AlphaException(e.getMessage());
        }
    }

    public void writeToFile(String textToAppend) throws AlphaException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true); // create a FileWriter in append mode
            fw.write(textToAppend);
            fw.close();
        } catch (IOException e) {
            throw new AlphaException(e.getMessage());
        }
    }

    public void rewriteFile(TaskList taskList) throws AlphaException {
        try {
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
        } catch (IOException e) {
            throw new AlphaException(e.getMessage());
        }

    }

    public List<Task> readFile() throws AlphaException {
        List<Task> tasksInFile = new ArrayList<>();
        //File f = new File(filePath); // create a File for the given file path
        Scanner s;
        try {
            s = new Scanner(f); // create a Scanner using the File as the source
        } catch (FileNotFoundException e) {
            throw new AlphaException(e.getMessage());
        }
        while (s.hasNext()) {
            String task = s.nextLine();
            String taskType = String.valueOf(task.charAt(1));
            boolean taskStatus = String.valueOf(task.charAt(5)) == "X";
            switch (taskType) {
                case "T": {
                    String taskDescription = task.substring(8);
                    Task todo = new Todo(taskDescription, taskType);
                    todo.changeStatus(taskStatus);
                    tasksInFile.add(todo);
                    break;
                }
                case "E": {
                    String taskDescription = task.substring(8, task.length()-15);
                    String date = task.substring(task.length()-12, task.length()-1);
                    Task event = new Event(taskDescription, date, taskType);
                    event.changeStatus(taskStatus);
                    tasksInFile.add(event);
                    break;
                }
                case "D": {
                    String taskDescription = task.substring(8, task.length()-15);
                    String deadlineDate = task.substring(task.length()-12, task.length()-1);
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
