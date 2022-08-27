package duke;

import duke.task.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    File file;

    public Storage(String filePath) {
        file = new File(filePath);
    }

    public ArrayList<Task> load() {
        ArrayList<Task> res = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                res.add(fileLineToTask(sc.nextLine()));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            try {
                Files.createDirectories(Paths.get("./data"));
                File f = new File("data/tasks.txt");
            } catch (IOException ex) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        return res;
    }

    public void refresh(TaskList tasks) {
        File temp = new File("data/temp.txt");
        try {
            FileWriter fw = new FileWriter(temp);
            fw.write(tasks.toFileFormat());
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        this.file.delete();
        temp.renameTo(new File("data/tasks.txt"));
        this.file = temp;
    }

    public void addTaskToStorage(Task task) {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(task.toFileFormat() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public static Task fileLineToTask(String fileLine) {
        String delimiter = " \\| ";
        String[] strings = fileLine.split(delimiter, 4);
        boolean isDone = strings[1].equals("1");
        if (strings[0].equals("T")) {
            return new ToDo(strings[2], isDone);
        } else if (strings[0].equals("D")) {
            return new Deadline(strings[2], isDone, LocalDate.parse(strings[3]));
        } else {
            return new Event(strings[2], isDone, LocalDate.parse(strings[3]));
        }
    }
}
