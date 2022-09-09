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

public class Storage {
    private final String filePath;
    
    public Storage(String FILE_PATH) {
        this.filePath = FILE_PATH;
    }
    
    /**
     * Retrieves information from the data file to populate an ArrayList
     *
     * @return ArrayList of Tasks represented in the data file. 
     * @throws DukeException If data file cannot be accessed.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(filePath));
            while (sc.hasNext()) {
                String s = sc.nextLine();
                Task task = getTask(s);
                if (s.charAt(6) == 'X') task.mark(true);
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        return tasks;
    }

    private Task getTask(String s) throws DukeException {
        Task task;
        String fullTaskDescription = s.substring(9);
        switch (s.charAt(3)) {
        case 'T':
            assert s.length() >= 8 : "Loaded todo task is not long enough";
            task = new ToDoTask(fullTaskDescription);
            break;
        case 'D':
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
            task = new DeadlineTask(deadlineDescription, date);
            break;
        case 'E':
            String[] sections = fullTaskDescription.split(" \\(at: ");
            assert sections.length > 0 : "Loaded event task has no description";
            String eventDescription = sections[0];
            String eventDate = sections[1].substring(0, sections[1].length() - 1); // removes last bracket
            task = new EventTask(eventDescription, eventDate);
            break;
        default:
            throw new DukeException("Unrecognised Task Type");
        }
        return task;
    }

    public void appendFile(Task task, int pos) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(pos + "." + task.toString() + "\n");
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        } 
    }
    
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
