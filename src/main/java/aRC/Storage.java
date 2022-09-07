package arc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates a Storage that saves tasks
 */
public class Storage {
    private File dataFile;

    /**
     * Constructor for Storage
     * @param relativePath Relative path of txt datafile
     */
    public Storage(String relativePath) {
        this.dataFile = new File(relativePath);
    }

    /**
     * Loads tasks from the datafile to TaskList
     * @return An ArrayList containing tasks read from the datafile
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            readFile(tasks);
        } catch (FileNotFoundException e) {
            createFile();
        }

        return tasks;
    }

    private void createFile() {
        this.dataFile.getParentFile().mkdir();
        try {
            if (this.dataFile.createNewFile()) {
                System.out.println("File created: " + this.dataFile.getPath());
            }
        } catch (IOException e) {
            System.out.println("File not successfully created");
        }
    }

    private void readFile(ArrayList<Task> tasks) throws FileNotFoundException {
        Scanner sc = new Scanner(this.dataFile);

        while (sc.hasNextLine()) {
            String[] currTask = sc.nextLine().split("\\|");
            String taskType = currTask[0];
            String isDone = currTask[1];
            String title = currTask[2];

            switch (taskType) {
            case "T":
                tasks.add(new Todo(title, isDoneToBoolean(isDone)));
                break;
            case "D":
                String deadline = currTask[3];
                LocalDate ldt = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                tasks.add(new Deadline(title, isDoneToBoolean(isDone), ldt));
                break;
            case "E":
                String time = currTask[3];
                tasks.add(new Event(title, isDoneToBoolean(isDone), time));
                break;
            default:
                System.out.println("Undefined task type: " + taskType);
            }
        }

        sc.close();
    }

    private boolean isDoneToBoolean(String isDone) {
        return isDone.equals("1");
    }

    /**
     * Saves the tasks in TaskList on the datafile
     * @param taskList The TaskList whose data is to be saved
     * @throws DukeException Throws a aRC.DukeException specific to this program
     */
    public void save(TaskList taskList) throws DukeException {
        ArrayList<String> newData = new ArrayList<>();

        for (int i = 0; i < taskList.numTasks(); i++) {
            newData.add(taskList.getTask(i).toFileFormat());
        }

        try {
            FileWriter fw = new FileWriter(this.dataFile);
            fw.write(String.join("\n", newData));

            fw.close();
        } catch (IOException e) {
            throw new DukeException("File not saved properly");
        }
    }
}
