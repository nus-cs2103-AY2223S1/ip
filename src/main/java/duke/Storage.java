package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from text file and saving tasks in the file in Duke.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for new instance.
     *
     * @param filePath path for text file to read and write from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads text file and obtain new array of tasks from it.
     *
     * @return new ArrayList of tasks.
     * @throws DukeException error reading file.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        if (createFile()) {
            try {
                File f = new File(filePath);
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    String data = s.nextLine();
                    Task task = createTask(data);
                    tasks.add(task);
                }
            } catch (FileNotFoundException e) {
                throw new DukeException("File does not exist");
            }
        }

        return tasks;
    }

    /**
     * Creates Task instance based on data from text file.
     *
     * @param data line of data in text file.
     * @return Task instance created.
     * @throws DukeException missing inputs or invalid arguments.
     */
    private Task createTask(String data) throws DukeException {
        String[] info = data.split(Task.FIELD_DIVIDER);
        String type = info[0];
        String done = info[1];
        String name = info[2];
        String priorityLevel = info[3];
        Task task;

        switch (type) {
        case "T":
            task = new Todo(name);
            break;

        case "D":
            task = new Deadline(name, LocalDate.parse(info[4]));
            break;

        case "E":
            String date = info[4];
            String time = info[5];
            String dateTime = date + " " + time;
            task = new Event(name, LocalDateTime.parse(dateTime,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            break;

        default:
            throw new DukeException("Unknown Task type in text file");
        }

        if (done.equals("1")) {
            task.mark();
        }

        task.setPriority(priorityLevel.toLowerCase());

        return task;
    }

    /**
     * Writes the tasks from current program into text file.
     *
     * @param tasks TaskList containing current list of tasks.
     * @throws DukeException error writing tasks.
     */
    public String save(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            String text = tasks.toData();
            fw.write(text);
            fw.close();
            return "Your tasks have been saved.\nBye. Hope to see you again soon!";
        } catch (IOException e) {
            throw new DukeException("Error saving output file");
        }
    }

    /**
     * Creates the directory and text file in the path if not created.
     *
     * @return true if file created, false otherwise.
     * @throws DukeException error when creating file.
     */
    private boolean createFile() throws DukeException {
        boolean isCreated = false;
        File data = new File("data");
        File f = new File(filePath);
        try {
            data.mkdir();
            if (!f.createNewFile()) {
                isCreated = true;
            }
        } catch (IOException e) {
            throw new DukeException("Error creating text file");
        }
        return isCreated;

    }
}
