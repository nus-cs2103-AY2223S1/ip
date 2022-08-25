package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Storage {
    protected File file;

    public Storage(String filePath) throws DukeException {
        if (filePath.isEmpty()) {
            throw new DukeException("\u2639 OOPS!!! File path cannot be empty.");
        }
        this.file = new File(filePath);
    }

    public TaskList getData() throws DukeException {
        TaskList tasks = new TaskList();
        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new DukeException("\u2639 OOPS!!! File not found: Unable to retrieve data.");
        }
        sc.useDelimiter("( \\| )|(\\n)");  // split by | or new line
        while (sc.hasNext()) {
            try {
                String type = sc.next(), status = sc.next(), description = sc.next();
                Task task;
                switch (type) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    task = new Deadline(description, sc.next());
                    break;
                case "E":
                    task = new Event(description, sc.next());
                    break;
                default:
                    throw new DukeException("\u2639 OOPS!!! Invalid task type: %s", type);
                }
                if (status.equals("1")) {
                    task.markAsDone();;
                } else if (!status.equals("0")) {
                    throw new DukeException("\u2639 OOPS!!! Invalid task status: %s", status);
                }
                tasks.add(task);
            } catch (NoSuchElementException e) {
                sc.close();
                throw new DukeException("\u2639 OOPS!!! File content is not in the correct format.");
            }
        }
        sc.close();
        return tasks;
    }

    public void saveData(TaskList tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(file);
            String content = tasks.getTasks().stream().map(x -> x.getSaveFormat() + "\n").reduce("", (x,y) -> x + y);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("\u2639 OOPS!!! Unable to save data.");
        }
    }
}
