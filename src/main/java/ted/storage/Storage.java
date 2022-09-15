package ted.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ted.task.Deadline;
import ted.task.Event;
import ted.task.Task;
import ted.task.TaskList;
import ted.task.Todo;

/**
 * Represents storage of task list. A <code>Storage</code> object corresponds
 * to a saved file with the specific filePath.
 */
public class Storage {
    private String filePath;
    private String folderPath;
    private File dukeFile;

    /**
     * Creates Storage object with filePath of file with saved tasks.
     * @param filePath filePath of file.
     * @param fileName fileName of file.
     */
    public Storage(String filePath, String fileName) {
        this.filePath = filePath;
        assert filePath.contains(fileName);
        this.folderPath = filePath.replace("/" + fileName, "");
        this.dukeFile = new File(filePath);
    }

    /**
     * Returns TaskList after loading task data from file.
     *
     * @return TaskList with tasks saved in file.
     */
    public TaskList loadFile() {
        ArrayList<Task> temp = new ArrayList<>();

        if (dukeFile.exists()) {
            getFromFile(temp);
        } else {
            createFile();
        }
        return new TaskList(temp);
    }

    /**
     * Loads task data from file into ArrayList.
     *
     * @param temp ArrayList to load data into.
     */
    private void getFromFile(ArrayList<Task> temp) {
        try {
            Scanner sc = new Scanner(dukeFile);

            while (sc.hasNextLine()) {
                String[] st = sc.nextLine().split(" \\| ");
                boolean isTaskDone;

                if (st[1].equals("1")) {
                    isTaskDone = true;
                } else {
                    assert st[1].equals("0");
                    isTaskDone = false;
                }

                switch (st[0]) {
                case "T":
                    temp.add(new Todo(st[2], isTaskDone));
                    break;
                case "D":
                    temp.add(new Deadline(st[2], isTaskDone, st[3]));
                    break;
                case "E":
                    temp.add(new Event(st[2], isTaskDone, st[3]));
                    break;
                default:
                    break;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates new file and directory for data to be stored.
     */
    private void createFile() {
        try {
            File dataFolder = new File(folderPath);
            dataFolder.mkdirs();
            dukeFile.createNewFile();
        } catch (SecurityException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates file when changes occur to tasks in the file.
     *
     * @param tasks updated TaskList.
     */
    public void updateFile(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            tasks.writeToFile(fw);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
