import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TasksManager {
    private String line = "_______________________________________";

    private File tasklist;
    private FileWriter fileWriter;
    private PrintWriter printWriter;
    private static ArrayList<Task> tasks = new ArrayList<>();

    public TasksManager() {
        try {
            tasklist = new File("data/tasklist.txt");
            if (!tasklist.exists()) {
                tasklist.createNewFile();
            }
            fileWriter = new FileWriter(this.tasklist, true);
            printWriter = new PrintWriter(fileWriter);
            readfile();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public boolean addTask(Task task) {
        //add to the tasks
        tasks.add(task);
        //write to file
        printWriter.println(task.fileForm());
        return true;
    }

    public void showList() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int counter = i + 1;
            System.out.println(counter + ". " + tasks.get(i));
        }
        System.out.println(line);
    }

    public void markTaskAsDone(int n) throws BadTaskOperationException {
        if (n > tasks.size() || n < 1) {
            throw new BadTaskOperationException("Bad input", "Done");
        }
        Task doneTask = this.tasks.get(n - 1);
        doneTask.markAsDone();

        // rewrite file entirely
        rewriteFile();
        System.out.println(doneTask);
    }

    public void deleteTask(int n) throws BadTaskOperationException {
        if (n > tasks.size() || n < 1) {
            throw new BadTaskOperationException("Bad input", "Done");
        }
        Task deleted = this.tasks.remove(n - 1);
        //rewrite file entirely
        rewriteFile();
        System.out.println(deleted);
    }

    public boolean readfile() {
        try {
            Scanner sc = new Scanner(this.tasklist);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                System.out.println(data);
                String[] splitted = data.split(" ");
                boolean isAdded = addFromFile(splitted);
            }
            sc.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("error reading file");
            return false;
        }
    }

    private boolean rewriteFile() {
        //delete all file contents
        printWriter.flush();
        try {
            printWriter = new PrintWriter(new FileWriter(this.tasklist, false));
            printWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        printWriter = new PrintWriter(fileWriter);
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            printWriter.println(task.fileForm());
        }
        return true;
    }

    private boolean addFromFile(String[] strArray) {
        if (strArray.length < 3) {
            return false;
        }

        if (strArray[0].equals("T")) {
            Task newTask = new Todo(strArray[2]);
            if (strArray[1].equals("true")) {
                newTask.markAsDone();
            }
            this.tasks.add(newTask);
            return true;
        }

        if (strArray[0].equals("D") && strArray.length > 3) {
            Task newTask = new Deadline(strArray[2], strArray[3]);
            if (strArray[1].equals("true")) {
                newTask.markAsDone();
            }
            this.tasks.add(newTask);
            return true;
        }

        if (strArray[0].equals("E") && strArray.length > 3) {
            Task newTask = new Event(strArray[2], strArray[3]);
            if (strArray[1].equals("true")) {
                newTask.markAsDone();
            }
            this.tasks.add(newTask);
            return true;
        }

        return false;
    }

    public int numTasks() {
        return tasks.size();
    }

    public void closePW() {
        printWriter.close();
    }
}

