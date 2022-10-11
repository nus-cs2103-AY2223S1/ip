package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading and saving of data in file,
 * where data represents the list of tasks
 * to be done
 */


public class Storage {
    private static String filePath;
    private static ArrayList<Task> tasks;
    private Scanner reader;
    private File folder;
    private File file;



    Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.folder = new File("data");
        this.file = new File(filePath);

        if(!folder.exists()) {
            folder.mkdir();
        } if(!file.exists()) {
            file.createNewFile();
        }

        this.tasks = new ArrayList<>();
    }

    /**
     * Checks and loads the tasks in file
     * @return ArrayList of tasks already in file
     * @throws DukeException Thrown if file is empty
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            reader = new Scanner(file);
            fileLoader(reader);
        } catch (FileNotFoundException e) {
            this.file = new File(filePath);
        } catch (DukeException e) {
           throw new DukeException("File empty");
        }
        return tasks;
    }

    public void fileLoader(Scanner reader) throws DukeException {
        if (file.length() != 0) {
            while (reader.hasNextLine()) {
                String task = reader.nextLine();
                taskLoader(task);
            }
        } else {
            throw new DukeException("File empty");
        }

    }

    public void taskLoader(String task) {
        String type = task.split("")[3];
        if (type.equals("E")) {
            eventLoader(task);
        } else if (type.equals("T")) {
            todoLoader(task);
        } else if (type.equals("D")) {
            deadlineLoader(task);
        }
    }

    public void eventLoader(String task) {
        String mark = task.split("")[6];
        String currentTask = task.substring(9, task.indexOf(" (at: "));
        String eventTime = task.substring(task.indexOf(" (at: ") + 6, task.indexOf(")"));
        Task curr = new Events(currentTask, eventTime);
        tasks.add(curr);
        if (mark.equals("X")) {
            curr.mark();
        }
    }

    public void todoLoader(String task) {
        String mark = task.split("")[6];
        String currentTask = task.substring(9);
        Task curr = new ToDos(currentTask);
        tasks.add(curr);
        if (mark.equals("X")) {
            curr.mark();
        }
    }

    public void deadlineLoader(String task) throws DateTimeException {
        String mark = task.split("")[6];
        String currentTask = task.substring(9, task.indexOf(" (by: "));
        String eventTime = task.substring(task.indexOf(" (by: ") + 5, task.indexOf(")")).substring(1);
        LocalDate d1 = LocalDate.parse(eventTime, DateTimeFormatter.ofPattern("MMM d yyyy"));
        String deadline = d1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Task curr = new Deadline(currentTask, deadline);
        tasks.add(curr);
        if (mark.equals("X")) {
            curr.mark();
        }

    }

    /**
     * Saves tasks in file
     * @param tasks
     * @throws IOException
     */
    public static void writeToFile(ArrayList<Task> tasks) throws IOException {
        File f = new File(filePath);
        if (f.exists()) {
           write();
        } else {
            throw new IOException("File does not exist");
        }
    }

    public static void write() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String textToAdd = "";
        for (Task item : tasks) {
            if (item != null)
                textToAdd += tasks.indexOf(item) + 1 + "." + item + "\n";
        }
        fw.write(textToAdd);
        fw.close();

    }
}
