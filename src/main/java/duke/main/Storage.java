package duke.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a storage that is used to load and save tasks upon start up
 * and changes to the tasklist respectively
 */
public class Storage {
    private static ArrayList<Task> taskList;
    private String filePath;
    /**
     * Constructor for Storage
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns a True if object is an existing file.
     * Otherwise, False
     *
     * @return boolean
     */
    public boolean isExistingFile() {
        return Files.isRegularFile(Paths.get(this.filePath));
    }

    /**
     * Returns a True if object is an existing directory.
     * Otherwise, False
     *
     * @return boolean
     */
    public boolean isExistingDir() {
        String[] splitArr = filePath.split("/");
        return Files.isDirectory(
                Paths.get(splitArr[0] + "/" + splitArr[1]));
    }

    /**
     * Returns task that has been initialised
     *
     * @param line
     * @return Task
     * @throws IOException
     */
    public Task taskParser(String line) throws IOException {
        String[] split = line.split("#");
        String type = split[0];
        String done = split[1];
        String name = split[2];
        LocalDate date;
        Task task = null;
        switch (type) {
        case ("T"):
            task = new Todo(name);
            break;
        case ("D"):
            date = LocalDate.parse(split[3]);
            task = new Deadlines(name, date);
            break;
        case ("E"):
            date = LocalDate.parse(split[3]);
            task = new Events(name, date);
            break;
        default:
            throw new IOException("Loading Error");
        }

        if (done.equals("1")) {
            task.setDone();
        }
        return task;
    }

    /**
     * Returns an Arraylist of tasks
     *
     * @return ArrayList of Tasks
     */
    public ArrayList<Task> loadTasks() {
        try {
            int index = 0;
            String[] splitArr = filePath.split("/");
            if (!isExistingDir()) {
                new File(splitArr[1]).mkdir();
            }
            if (!isExistingFile()) {
                new File(this.filePath).createNewFile();
            }
            FileReader fr = new FileReader(this.filePath);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();

            while (line != null) {
                Task task = taskParser(line);
                taskList.add(index, task);
                index++;
                line = br.readLine();
            }

            br.close();
        } catch (IOException e) {
            System.out.println("error" + e);
        }
        assert Files.exists(Paths.get(filePath)) : "The File in the path: "
            + filePath + " does not exist";
        return taskList;
    }

    /**
     * Saves tasks into a file
     *
     * @param taskList
     */
    public void saveTasks(TaskList taskList) {
        try {
            File myFile = new File(this.filePath);
            OutputStream os = new FileOutputStream(myFile);
            PrintWriter pw = new PrintWriter(os);

            for (int i = 0; i < taskList.length(); i++) {
                pw.println(taskList.getTask(i).writeData());
            }

            pw.close();
        } catch (IOException e) {
            System.out.println("error" + e);
        }
    }
}
