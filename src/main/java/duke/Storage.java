package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File file;

    public Storage(String filePath) throws DukeException {
        this.filePath = filePath;
        this.file = new File(this.filePath);
        try {
            // Does not overwrite directory or file if it already exists.
            this.file.getParentFile().mkdir();
            this.file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Directory or file cannot be located. New file is created.");
        }
    }

    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNext()) {
                tasks.add(parse(scanner.nextLine()));
            }
            scanner.close();
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("File is inaccessible");
        }
    }

    public void save(List<Task> tasks) {
        List<String> data = new ArrayList<>();
        for (Task task : tasks) {
            data.add(task.stringify());
        }

        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(String.join(System.lineSeparator(), data));
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private Task parse(String str) throws DukeException {
        try {
            String[] entry = str.split(" \\| ");
            if (entry[0].equals("T")) {
                return new ToDo(entry[2], entry[1].equals("1"));
            }
            if (entry[0].equals("D")) {
                return new Deadline(entry[2], entry[1].equals("1"), LocalDate.parse(entry[3]));
            }
            if (entry[0].equals("E")) {
                return new Event(entry[2], entry[1].equals("1"), LocalDate.parse(entry[3]));
            }
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Invalid string in file.");
        }
        throw new DukeException("Invalid string in file.");
    }
}
