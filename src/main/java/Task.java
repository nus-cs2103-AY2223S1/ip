import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task {
    private static final String FILE_LOCATION = "./data/TaskFile.txt";
    private static final int ISDONE_MARKER = 4;
    private static final int DESC_MARKER = 8;


    private static ArrayList<Task> tasks = new ArrayList<Task>();

    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        addTask(this);
    }

    public static void addTask(Task task) {
        Task.tasks.add(task);
    }

    public static void removeTask(int index) {
        BotResponse.removeTaskLog(Task.tasks.get(index));
        Task.tasks.remove(index);
    }

    public static int length() {
        return Task.tasks.size();
    }

    private String getStatus() {
        return (this.isDone ? "X" : " ");
    }

    private String getStatusData() {
        return (this.isDone ? "1" : "0");
    }

    public String toString() {
        return "[" + this.getStatus() + "] " + this.description;
    }

    public String toStringData() {
        return getStatusData() + " | " + this.description;
    }

    public static void markDone(int index, boolean done) {
        Task.tasks.get(index).isDone = done;
        BotResponse.markLog(Task.tasks.get(index), done);
    }

    public static void printTasks() {
        BotResponse.separationLine();
        for (int i = 0; i < Task.tasks.size(); i++) {
            System.out.println((i + 1) + "." + Task.tasks.get(i));
        }
        BotResponse.separationLine();
    }

    public static void loadTasks() {
        File taskFile = new File(FILE_LOCATION);

        if (!taskFile.exists()) {
            File directory = new File(taskFile.getParent());
            directory.mkdir();
            try {
                taskFile.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            Scanner sc = new Scanner(taskFile);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                char TaskType = line.charAt(0);
                boolean taskIsDone = line.charAt(ISDONE_MARKER) == '1';
                String taskDesc;
                String taskTime;

                switch (TaskType) {
                case 'T':
                    taskDesc = line.substring(DESC_MARKER).trim();
                    new ToDo(taskDesc, taskIsDone);
                    break;

                case 'D':
                    taskDesc = line.substring(DESC_MARKER, line.lastIndexOf('|')).trim();
                    taskTime = line.substring(line.lastIndexOf('|') + 1).trim();
                    new Deadline(taskDesc, taskIsDone, taskTime);
                    break;

                case 'E':
                    taskDesc = line.substring(DESC_MARKER, line.lastIndexOf('|')).trim();
                    taskTime = line.substring(line.lastIndexOf('|') + 1).trim();
                    new Event(taskDesc, taskIsDone, taskTime);
                    break;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            BotResponse.separationLine();
            System.out.println(e.getMessage());
        }

    }

    public static void saveTasks() {
        try {
            FileWriter taskFile = new FileWriter(FILE_LOCATION);
            String textToSave = " ";
            for (int i = 0; i < Task.tasks.size(); i++) {
                textToSave += Task.tasks.get(i).toStringData() + "\n";
            }
            taskFile.write(textToSave.trim());
            taskFile.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
