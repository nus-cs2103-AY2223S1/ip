package duke.storage;

import duke.others.DukeException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void appendToFile(String textToAppend, Ui ui) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(textToAppend);
            fw.close();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    public void overwriteFile(TaskList tasks, Ui ui) {
        try {
            FileWriter fw = new FileWriter(filePath);
            StringBuilder textToAdd = new StringBuilder();
            for (Task task : tasks.getTasks()) {
                textToAdd.append(task.toString()).append(System.lineSeparator());
            }
            fw.write(textToAdd.toString());
            fw.close();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }
    public List<Task> load() throws DukeException {
        try {
            File file = new File(filePath);
            Scanner s = new Scanner(file);
            List<Task> tasks= new ArrayList<>();
            while (s.hasNext()) {
                String str = s.nextLine();
                Task task = fileInterpreter(str);
                tasks.add(task);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException();
        }
    }

    public Task fileInterpreter(String str) throws DukeException {
        Task task;
        String description = str.split("] ", 2)[1];
        if (str.contains("[T]")) {
            task = new ToDo(description);
        } else {
            String message = str.substring(str.indexOf("]", str.indexOf("]") + 1) + 2,
                    str.indexOf(" ("));
            String dateString = str.substring(str.indexOf(":") + 2, str.indexOf(")"));
            LocalDate localDate = Parser.parseDateFormats(dateString);
            if (str.contains("[D]")){
                task = new Deadline(message, localDate);
            } else {
                task = new Event(message, localDate);
            }
        }
        if (str.contains("[X]")) {
            task.markAsDone();
        }
        return task;
    }

}
