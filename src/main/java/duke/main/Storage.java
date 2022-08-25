package duke.main;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;
            }

            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                String input = sc.nextLine();
                String[] splitInput = input.split(" \\| ");

                switch (splitInput[0]) {
                    case "T":
                        Todo todo = new Todo(splitInput[2]);
                        if (Integer.parseInt(splitInput[1]) == 1) {
                            todo.markAsDone();
                        }
                        tasks.add(todo);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(splitInput[2], splitInput[3]);
                        if (Integer.parseInt(splitInput[1]) == 1) {
                            deadline.markAsDone();
                        }
                        tasks.add(deadline);
                        break;
                    case "E":
                        Event event = new Event(splitInput[2], splitInput[3]);
                        if (Integer.parseInt(splitInput[1]) == 1) {
                            event.markAsDone();
                        }
                        tasks.add(event);
                        break;
                    default:
                        throw new DukeException("Task is of unknown type :(");
                }
            }
        } catch (IOException e) {
            throw new DukeException("An IOException occurred!! :(");
        } catch (NumberFormatException e) {
            throw new DukeException("An error occurred during file parsing, unexpected done value encountered.");
        }

        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws DukeException {
        try {
            File file = new File(filePath);

            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(task.getOutput() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("An IOException occurred!! :(");
        }
    }
}

