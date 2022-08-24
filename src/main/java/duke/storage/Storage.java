package duke.storage;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.tasklist.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents storage that handles loading and saving data.
 */
public class Storage {
    private String filePath;

    /**
     * Creates an instance of a data storage.
     *
     * @param filePath Creates storage at that specific location
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load data from the file path and stores the data in an ArrayList.
     *
     * @return ArrayList containing the tasks from the file
     */
    public ArrayList<Task> loadData() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();

        File f = new File(this.filePath);
        try {
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            Scanner fileReader = new Scanner(f);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                String[] dataArr = data.split("\\s\\|\\s");
                Task task;
                if (dataArr[0].equals("T")) {
                    task = new Todo(dataArr[2]);
                } else if (dataArr[0].equals("D")) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(dataArr[3], formatter);
                    task = new Deadline(dataArr[2], date);
                } else {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(dataArr[3], formatter);
                    task = new Event(dataArr[2], date);
                }
                if (dataArr[1].equals("1")) {
                    task.setAsDone();
                }
                list.add(task);
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        return list;
    }

    /**
     * Save data from the to-do list into the file specified by filepath.
     *
     * @param list TaskList to be saved
     */
    public void saveData(TaskList list) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (int i = 0; i < list.getSize(); i++) {
                Task t = list.getTask(i);
                StringBuilder taskString = new StringBuilder();
                taskString.append(t.getTaskType() + " | ");
                if (t.getStatus()) {
                    taskString.append("1 | ");
                } else {
                    taskString.append("0 | ");
                }
                if (t instanceof Todo) {
                    taskString.append(((Todo) t).getDescription() + "\n");
                } else if (t instanceof Event) {
                    taskString.append(((Event) t).getDescription() + " | " + ((Event) t).getEventAt() + "\n");
                } else {
                    taskString.append(((Deadline) t).getDescription() + " | " + ((Deadline) t).getDeadlineBy() + "\n");
                }
                fw.write(taskString.toString());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
