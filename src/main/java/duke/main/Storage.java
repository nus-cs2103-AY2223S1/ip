package duke.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.errors.DukeException;
import duke.task.Deadline;
import duke.task.Events;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDos;

public class Storage {
    private String filePath;

    public Storage(String file) {
        filePath = file;
    }

    private File checkFile() throws IOException {
        File file = new File(filePath);
        if (file.exists() && !file.isDirectory()) {
        } else {
            file.getParentFile().mkdir();
            file.createNewFile();
        }
        return file;
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            File file = checkFile();
            Scanner fileScanner = new Scanner(file);
            ArrayList<Task> tasks = new ArrayList<>();
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] separatedLines = line.split("\\|");
                switch (separatedLines[0]) {
                case ("T"):
                    Task todo = new ToDos(separatedLines[2]);
                    if (separatedLines[1] == "1") {
                        todo.finished();
                    }
                    tasks.add(todo);
                    break;
                case ("D"):
                    LocalDateTime by = LocalDateTime.parse(separatedLines[3]);
                    Task deadline = new Deadline(separatedLines[2], by);
                    if (separatedLines[1] == "1") {
                        deadline.finished();
                    }
                    tasks.add(deadline);
                    break;
                case ("E"):
                    Task event = new Events(separatedLines[2], separatedLines[3]);
                    if (separatedLines[1] == "1") {
                        event.finished();
                    }
                    tasks.add(event);
                    break;
                default:
                    throw new DukeException("error in file, check loading");
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException("IOException in loading" + e.getMessage());
        }
    }

    public void save(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i + 1);
                fw.write(task.textFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("IOException in saving" + e.getMessage());
        }
    }
}
