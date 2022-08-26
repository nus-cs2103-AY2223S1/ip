package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static duke.DukeConstants.KEY_SEPARATOR;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(TaskList taskList, Ui ui) {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            fw.write(taskList.formatTasks());
            fw.close();
        } catch (IOException e) {
            ui.showError("there is a problem with saving");
            return;
        }
    }

    public List<Task> load() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        List<Task> tasks= new ArrayList<>();
        while(scanner.hasNext()) {
            String dataStr = scanner.nextLine();
            String[] taskStr = dataStr.split(KEY_SEPARATOR);
            Task task;

            switch (taskStr[0]) {
                case "T": task = new Todo(taskStr[2]);
                    break;
                case "D": task = new Deadline(taskStr[2], LocalDate.parse(taskStr[3]));
                    break;
                case "E": String[] timeDesc = taskStr[3].split(" ", 2);
                    if(timeDesc.length > 1) {
                        LocalDate date = LocalDate.parse(timeDesc[0]);
                        task = new Event(taskStr[2], date, timeDesc[1]);
                    } else {
                        LocalDate date = LocalDate.parse(taskStr[3]);
                        task = new Event(taskStr[2], date);
                    }

                    break;
                default: task = new Task(taskStr[2]);
                    break;
            }
            if(Integer.parseInt(taskStr[1]) == 1) {
                task.markAsDone();
            } else {
                task.markAsUndone();
            }
            tasks.add(task);
        }
        scanner.close();
        return tasks;
    }
}
