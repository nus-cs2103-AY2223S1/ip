import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveTasks {
    private static final int TASKTYPE = 0;
    private static final int ISTASKDONE = 1;
    private static final int TASKDESCRIPTION = 2;
    private static final int TASKDATETIME = 3;
    private static final String DIR = "data";
    private static final String FILENAME = "duke.txt";
    private static final String FILEPATH = String.valueOf(Paths.get(DIR, FILENAME));
    private ArrayList<Task> savedTasks = new ArrayList<>(100);


    public ArrayList<Task> load() {
        try {
            File dir = new File(DIR);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File fileName = new File(FILEPATH);
            if (!fileName.exists()) {
                fileName.createNewFile();
            }

            Scanner fileReader = new Scanner(fileName);
            while (fileReader.hasNextLine()) {
                String entry = fileReader.nextLine();
                readEntry(entry);
            }
            fileReader.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return savedTasks;
    }

    public void readEntry(String entry) {
        String[] fields = entry.split("\\|");
        Task taskToAdd;
        switch (fields[TASKTYPE]) {
            case "T":
                taskToAdd = new Todos(fields[TASKDESCRIPTION]);
                if (Boolean.parseBoolean(fields[ISTASKDONE])) {
                    taskToAdd.markAsDone();
                }
                savedTasks.add(taskToAdd);
                break;
            case "E":
                taskToAdd = new Event(fields[TASKDESCRIPTION], fields[TASKDATETIME]);
                if (Boolean.parseBoolean(fields[ISTASKDONE])) {
                    taskToAdd.markAsDone();
                }
                savedTasks.add(taskToAdd);
                break;
            case "D":
                taskToAdd = new Deadlines(fields[TASKDESCRIPTION], fields[TASKDATETIME]);
                if (Boolean.parseBoolean(fields[ISTASKDONE])) {
                    taskToAdd.markAsDone();
                }
                savedTasks.add(taskToAdd);
                break;
        }
    }

    public static void save(ArrayList<Task> savedTasks) {
        try {
            FileWriter fw = new FileWriter(FILEPATH);
            for (Task task : savedTasks) {
                fw.write(task.savedTaskString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
