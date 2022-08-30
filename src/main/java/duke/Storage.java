package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    
    private final File file;
    
    public Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Load tasks from storage or create new file.
     * @return ArrayList of tasks
     */
    public ArrayList<Task> load() {
        if (file.exists()) {
            return loadFromFile();
        } else {
            return createFile();
        }
    }

    /**
     * Save all tasks to storage
     * @param taskList
     */
    public void save(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTaskList();
        try {
            FileWriter fw = new FileWriter(file);
            for (Task t : tasks) {
                fw.write(t.encodeToString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private ArrayList<Task> createFile() {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return new ArrayList<Task>();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Read all task stored in storage
     * @return ArrayList of tasks
     */
    private ArrayList<Task> loadFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                Task task = Parser.parseTask(line);
                tasks.add(task);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}
