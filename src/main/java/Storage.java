import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FILE_LOCATION = "./data/TaskFile.txt";
    private static final int ISDONE_MARKER = 4;
    private static final int DESC_MARKER = 8;

    public static ArrayList<Task> loadTaskFile() {
        File taskFile = new File(FILE_LOCATION);
        ArrayList<Task> tasks = new ArrayList<Task>();

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
                    tasks.add(new ToDo(taskDesc, taskIsDone));
                    break;

                case 'D':
                    taskDesc = line.substring(DESC_MARKER, line.lastIndexOf('|')).trim();
                    taskTime = line.substring(line.lastIndexOf('|') + 1).trim();
                    tasks.add(new Deadline(taskDesc, taskIsDone, taskTime));
                    break;

                case 'E':
                    taskDesc = line.substring(DESC_MARKER, line.lastIndexOf('|')).trim();
                    taskTime = line.substring(line.lastIndexOf('|') + 1).trim();
                    tasks.add(new Event(taskDesc, taskIsDone, taskTime));
                    break;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            BotResponse.separationLine();
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    public static void saveTaskFile(ArrayList<Task> tasks) {
        try {
            FileWriter taskFile = new FileWriter(FILE_LOCATION);
            String textToSave = " ";
            for (int i = 0; i < tasks.size(); i++) {
                textToSave += tasks.get(i).toStringData() + "\n";
            }
            taskFile.write(textToSave.trim());
            taskFile.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
