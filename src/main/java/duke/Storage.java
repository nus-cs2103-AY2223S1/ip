package duke;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList loadFileData() throws DukeException {
        TaskList tasks = new TaskList();
        try {
            File file = new File(this.filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] split = sc.nextLine().split("##");
                Task task;
                if ("D".equals(split[0])) {
                    LocalDate localDate = LocalDate.parse(split[3]);
                    task = new Deadline(split[2], localDate);
                } else if ("E".equals(split[0])) {
                    LocalDate localDate = LocalDate.parse(split[3]);
                    task = new Event(split[2], localDate);
                } else if ("T".equals(split[0])) {
                    task = new ToDo(split[2]);
                } else {
                    throw new DukeException("Unable to parse items in file.");
                }
                if (split[1].equals("Y")) {
                    task.toggleDone();
                }
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            try {
                Files.createDirectories(Paths.get("data/"));
                File file = new File("data/duke.txt");
                System.out.println("New file created to store tasks.");
            } catch (IOException ex) {
                throw new DukeException(ex.getMessage());
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Unable to parse dates in file.");
        }
        return tasks;
    }


    public void saveFileData(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            for (int i = 0; i < tasks.size(); i++) {
                String str = tasks.get(i).stringify();
                fw.write(str + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
