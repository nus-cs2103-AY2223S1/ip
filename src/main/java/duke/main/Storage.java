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

    public String getFilePath() {
        return filePath;
    }

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
     * checks if a file exists
     *
     * @return boolean
     */
    public boolean isExistingFile() {
        return Files.isRegularFile(Paths.get(this.filePath));
    }

    /**
     * checks if a directory exists
     *
     * @return boolean
     */
    public boolean isExistingDir() {
        String[] splitArr = filePath.split("/");
        return Files.isDirectory(
                Paths.get(splitArr[0] + "/" + splitArr[1]));
    }

    /**
     * taskParser that decides what kind of tasks are initialised
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
     * loadTasks method that checks and loads all task from a text file
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
            System.out.println("These are the tasks that you had previously!");

            while (line != null) {
                Task task = taskParser(line);
                taskList.add(index, task);
                index++;
                line = br.readLine();
            }

            br.close();
            System.out.println("Please use me to fill in more tasks");
        } catch (IOException e) {
            System.out.println("error" + e);
        }
        assert Files.exists(Paths.get(filePath)) : "The File in the path: "
            + filePath + " does not exist";
        return taskList;
    }

    /**
     * saveTasks method that saves changes to the tasklist into a duke.main.Duke.txt file
     *
     * @param taskList
     */
    public void saveTasks(TaskList taskList) {
        try {
            System.out.println(this.filePath);
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
