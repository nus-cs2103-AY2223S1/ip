import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File saveFile;

    public Storage(String filePath) {
        this.saveFile = new File(filePath);

    }

    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner scanner1 = new Scanner(saveFile);
            while (scanner1.hasNext()) {
                String[] oldTask = scanner1.nextLine().split("///", 4);
                // Task type saved in the third parameter
                switch (oldTask[0]) {
                case "T":
                    taskList.add(new Todo(oldTask[1], oldTask[3]));
                    break;
                case "D":
                    try {
                        taskList.add(new Deadline(oldTask[1], oldTask[2], oldTask[3]));
                    } catch (UncException uncException) {
                        System.out.println("Error creating saved list.");
                    }
                    break;
                case "E":
                    try {
                        taskList.add(new Event(oldTask[1], oldTask[2], oldTask[3]));
                    } catch (UncException uncException) {
                        System.out.println("Error creating saved list.");
                    }
                    break;
                default:

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("here");
            try {
                saveFile.getParentFile().mkdir();
                saveFile.createNewFile();
            } catch (IOException ioException) {
                System.out.println("there");
                System.out.println(ioException);
            } catch (SecurityException securityException) {
                System.out.println("or here");
                System.out.println(securityException);
            }
            System.out.println("Starting new file.");
        }
        return taskList;

    }

    public void save(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(saveFile);
            for (int i = 0; i < taskList.size(); i++) {
                writer.write(taskList.get(i).toStorageString() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving file.");
        }
    }

}
