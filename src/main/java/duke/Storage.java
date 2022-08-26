package duke;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Storage {
    private final String FILE_PATH;
    
    public Storage(String FILE_PATH) {
        this.FILE_PATH = FILE_PATH;
    }
    
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> data = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(FILE_PATH));
            while (sc.hasNext()) {
                String s = sc.nextLine();
                Task task;
                switch (s.charAt(1)) {
                case 'T':
                    task = new ToDoTask(s.substring(7));
                    break;
                case 'D':
                    String[] parts = s.substring(7).split(" \\(by: ");
                    DateTimeFormatter pattern = new DateTimeFormatterBuilder()
                            // case-insensitive to parse JAN and FEB
                            .parseCaseInsensitive()
                            // add pattern
                            .appendPattern("MMM dd yyyy")
                            // create formatter (use English Locale to parse month names)
                            .toFormatter(Locale.ENGLISH);
                    LocalDate date = LocalDate.parse(parts[1].substring(0, parts[1].length() - 1), pattern);
                    task = new DeadlineTask(parts[0], date);
                    break;
                case 'E':
                    String[] sections = s.substring(7).split(" \\(at: ");
                    task = new EventTask(sections[0], sections[1]);
                    break;
                default:
                    throw new DukeException("Unrecognised duke.task.Task Type");
                }
                if (s.charAt(4) == 'X') task.mark(true);
                data.add(task);
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        return data;
    }
    
    public void appendFile(Task task) throws DukeException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            fw.write(task.toString() + "\n");
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        } 
    }

    public void writeFile(int pos, Task task) throws DukeException {
        try {
            Scanner sc = new Scanner(new File(FILE_PATH));
            StringBuilder copy = new StringBuilder();
            int counter = 0;
            while (sc.hasNext()) {
                if (counter == pos) {
                    if (task != null) copy.append(task).append("\n");
                    sc.nextLine();
                } else {
                    copy.append(sc.nextLine()).append("\n");
                }
                counter++;
            }
            String fileContents = copy.toString();
            sc.close();
            FileWriter fw = new FileWriter(FILE_PATH, false);
            fw.write(fileContents);
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
