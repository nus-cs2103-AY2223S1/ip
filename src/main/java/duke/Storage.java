package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;

/**
 * Represents the storage of program data and all interactions with the data file.
 */
public class Storage {
    private final String filePath;
    
    public Storage(String filePath, String directory) throws DukeException {
        this.filePath = filePath;
        try {
            // if data/duke.txt does not already exist
            // such as when launching from a Jar file
            // create the directory and file
            new File(directory).mkdir();
            new File(filePath).createNewFile();
        } catch (Exception e) {
            throw new DukeException("Error creating data directory and/or file");
        }
    }
    
    /**
     * Retrieves information from the data file to populate an ArrayList.
     *
     * @return ArrayList of Tasks represented in the data file.
     * @throws DukeException If Scanner throws an IOException.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(filePath));
            while (sc.hasNext()) {
                String s = sc.nextLine();
                Task task = getTask(s);
                if (s.charAt(6) == 'X') {
                    task.mark(true);
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        return tasks;
    }

    /**
     * Converts a String in the file into the Task object it represents.
     *
     * @param s The String to be converted.
     * @throws DukeException If the String does not represent a recognised Task type.
     */
    private Task getTask(String s) throws DukeException {
        String fullTaskDescription = s.substring(9);
        switch (s.charAt(3)) {
        case 'T':
            assert s.length() >= 8 : "Loaded todo task is not long enough";
            return convertToToDoTask(fullTaskDescription);
        case 'D':
            return convertToDeadlineTask(fullTaskDescription);
        case 'E':
            return convertToEventTask(fullTaskDescription);
        default:
            throw new DukeException("Unrecognised Task Type");
        }
    }

    /**
     * Converts the task description into a ToDoTask.
     *
     * @param fullTaskDescription Description for the ToDoTask.
     * @return ToDoTask represented by the specified task description.
     */
    private Task convertToToDoTask(String fullTaskDescription) {
        return new ToDoTask(fullTaskDescription);
    }

    /**
     * Converts the task description into an EventTask.
     *
     * @param fullTaskDescription Description for the EventTask.
     * @return EventTask represented by the specified task description.
     */
    private Task convertToEventTask(String fullTaskDescription) {
        String[] sections = fullTaskDescription.split(" \\(at: ");
        assert sections.length > 0 : "Loaded event task has no description";
        String eventDescription = sections[0];
        String eventDate = sections[1].substring(0, sections[1].length() - 1); // removes last bracket
        return new EventTask(eventDescription, eventDate);
    }

    /**
     * Converts the task description into a DeadlineTask.
     *
     * @param fullTaskDescription Description for the DeadlineTask.
     * @return DeadlineTask represented by the specified task description.
     */
    private Task convertToDeadlineTask(String fullTaskDescription) {
        String[] parts = fullTaskDescription.split(" \\(by: ");
        assert parts.length > 0 : "Loaded deadline task has no description";
        String deadlineDescription = parts[0];
        String deadlineDate = parts[1].substring(0, parts[1].length() - 1); // removes last bracket
        DateTimeFormatter pattern = new DateTimeFormatterBuilder()
                // case-insensitive to parse JAN and FEB
                .parseCaseInsensitive()
                // add pattern
                .appendPattern("MMM dd yyyy")
                // create formatter (use English Locale to parse month names)
                .toFormatter(Locale.ENGLISH);
        LocalDate date = LocalDate.parse(deadlineDate, pattern);
        return new DeadlineTask(deadlineDescription, date);
    }

    /**
     * Appends the string representation of specified task to file.
     *
     * @param task The task to include in the file.
     * @param pos The numbering of the task in the file's list.
     * @throws DukeException If FileWriter throws an IOException.
     */
    public void appendFile(Task task, int pos) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(getTaskStr(task, pos));
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Formats the task into a string representation for the file.
     *
     * @param task The task to convert into a String.
     * @param pos The task's numbering in the file's list.
     */
    private String getTaskStr(Task task, int pos) {
        return pos + "." + task.toString() + "\n";
    }

    /**
     * Re-writes the file with the updated list.
     *
     * @param list User's task list.
     * @throws DukeException If FileWriter throws an IOException.
     */
    public void updateFile(TaskList list) throws DukeException {
        try {
            String newData = list.toString().substring(33);
            FileWriter fw = new FileWriter(filePath, false);
            fw.write(newData);
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
