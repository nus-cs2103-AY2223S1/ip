package duke.main;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileReader;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Represents a storage that is used to load and save tasks upon start up
 * and changes to the tasklist respectively
 */
public class Storage {
    private static String filePath;
    private static ArrayList<Task> taskList;

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
     * loadTasks method that checks and loads all task from a text file
     *
     * @return ArrayList<Task>
     */
    public ArrayList<Task> loadTasks() {
        try {
            int index = 0;
            String[] splitArr = filePath.split("/");

            Boolean isDirectoryExist = Files.isDirectory(Paths.get(splitArr[0] + "/" + splitArr[1]));
            if (!isDirectoryExist) {
                new File(splitArr[1]).mkdir();
            }

            Boolean isFileExist = Files.isRegularFile(Paths.get(this.filePath));
            if (!isFileExist) {
                new File(this.filePath).createNewFile();
            }

            FileReader fr = new FileReader(this.filePath);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            System.out.println("These are the tasks that you had previously!");

            while (line != null) {
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
                }

                if (done.equals("1")) {
                    task.done();
                }
                taskList.add(index, task);
                index++;
                line = br.readLine();
            }

            br.close();
            System.out.println("Please use me to fill in more tasks");
        } catch (IOException e) {
            System.out.println("error" + e);
        }
        return taskList;
    }

    /**
     * saveTasks method that saves changes to the tasklist into a Duke.txt file
     *
     * @param taskList
     */
    public void saveTasks(TaskList taskList) {
        try {
            File myFile = new File("./data/Duke.txt");
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
