import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String fileLocation;
    private static final int ISDONE_MARKER = 4;
    private static final int DESC_MARKER = 8;

    public Storage(String filePath) {
        this.fileLocation = filePath;
    }

    public ArrayList<Task> loadTaskFile() {
        File taskFile = new File(fileLocation);
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
            Ui.separationLine();
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    public void saveTaskFile(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        try {
            FileWriter taskFile = new FileWriter(fileLocation);
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
