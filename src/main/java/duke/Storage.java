package duke;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
 * A class that loads saves tasks into a file, as specified by the given file path.
 */
public class Storage {

    private String filePath;

    /**
     * Constructor for the Storage class.
     *
     * @param filePath Provides the file path for the file that the tasks should be loaded from and saved to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the given tasks in the TaskList object into the file, as specified by this.filePath.
     *
     * @param tasks The TaskList object that contain the tasks to be saved into the file.
     */
    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            for (Task t : tasks.getTasks()) {
                pw.println(t.toString());
            }
            pw.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Returns an ArrayList object that contains the tasks loaded from the file.
     *
     * @return An ArrayList object.
     * @throws DukeException If this.filePath does not contain a file.
     */
    public ArrayList<Task> load() throws DukeException {
        boolean pathExists = Files.exists(Path.of(this.filePath));
        if (pathExists) {
            return loadWithValidFilePath();
        } else {
            return loadWithInvalidFilePath();
        }
    }

    /**
     * Returns an ArrayList object using a valid file path, and contains tasks loaded from the file.
     *
     * @return An ArrayList object.
     */
    private ArrayList<Task> loadWithValidFilePath() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            FileReader fr = new FileReader(this.filePath);
            BufferedReader br = new BufferedReader(fr);

            String str;
            while ((str = br.readLine()) != null) {
                if (str.substring(0, 1).equals("T")) {
                    loadTodoTask(str, tasks);
                } else if (str.substring(0, 1).equals("E")) {
                    loadEventTask(str, tasks);
                }  else if (str.substring(0, 1).equals("D")) {
                    loadDeadlineTask(str, tasks);
                }
            }
            br.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return tasks;
    }

    /**
     * Throws DukeException as filePath given does not contain a file.
     *
     * @return Does not return anything actually, method signature states that ArrayList is returned just to fulfil
     * the method signature of load().
     * @throws DukeException Throws DukeException to show that file path is invalid.
     */
    private ArrayList<Task> loadWithInvalidFilePath() throws DukeException {
        try {
            File newFile = new File(this.filePath);
            newFile.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(newFile);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        throw new DukeException("File path does not contain a file");
    }

    /**
     * Converts a todo task from the given file path from String to a Task object,
     * and adds the task into the ArrayList object tasks.
     *
     * @param str The string containing the todo task.
     * @param tasks The ArrayList object to store the todo task.
     */
    private void loadTodoTask(String str, ArrayList<Task> tasks) {
        int firstSeparator = str.indexOf("|");
        int secondSeparator = str.indexOf("|", firstSeparator + 1);
        Todo t = new Todo(str.substring(secondSeparator + 2));

        if (str.substring(firstSeparator + 2, firstSeparator + 3).equals("X")) {
            t.markAsDone();
        }
        tasks.add(t);
    }

    /**
     * Converts an event task from the given file path from String to a Task object,
     * and adds the task into the ArrayList object tasks.
     *
     * @param str The string containing the event task.
     * @param tasks The ArrayList object to store the event task.
     */
    private void loadEventTask(String str, ArrayList<Task> tasks) {
        int firstSeparator = str.indexOf("|");
        int secondSeparator = str.indexOf("|", firstSeparator + 1);
        int thirdSeparator = str.indexOf("|", secondSeparator + 1);
        int fourthSeparator = str.indexOf("|", thirdSeparator + 1);

        String date = str.substring(thirdSeparator + 2, fourthSeparator - 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);

        String time = str.substring(fourthSeparator + 2);
        LocalTime localTime = LocalTime.parse(time);

        Event e = new Event(
                str.substring(secondSeparator + 2, thirdSeparator - 1),
                localDate,
                localTime);

        if (str.substring(firstSeparator + 2, firstSeparator + 3) == "X") {
            e.markAsDone();
        }
        tasks.add(e);
    }

    /**
     * Converts a deadline task from the given file path from String to a Task object,
     * and adds the task into the ArrayList object tasks.
     *
     * @param str The string containing the deadline task.
     * @param tasks The ArrayList object to store the deadline task.
     */
    private void loadDeadlineTask(String str, ArrayList<Task> tasks) {
        int firstSeparator = str.indexOf("|");
        int secondSeparator = str.indexOf("|", firstSeparator + 1);
        int thirdSeparator = str.indexOf("|", secondSeparator + 1);
        int fourthSeparator = str.indexOf("|", thirdSeparator + 1);

        String date = str.substring(thirdSeparator + 2, fourthSeparator - 1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);

        String time = str.substring(fourthSeparator + 2);
        LocalTime localTime = LocalTime.parse(time);

        Deadline d = new Deadline(
                str.substring(secondSeparator + 2, thirdSeparator - 1),
                localDate,
                localTime);

        if (str.substring(firstSeparator + 2, firstSeparator + 3) == "X") {
            d.markAsDone();
        }
        tasks.add(d);
    }
}
