package duke.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return List of tasks.
     * @throws DukeException  If there is an error during file parsing.
     */
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

                switch (input.charAt(0)) {
                case 'T':
                    String[] splitInput = input.split(" \\| ");
                    Todo todo = new Todo(splitInput[2]);
                    if (Integer.parseInt(splitInput[1]) == 1) {
                        todo.markAsDone();
                    }
                    String tag = splitInput[3];
                    todo.loadTag(tag);
                    tasks.add(todo);
                    break;
                case 'D':
                    String[] splitInputD = input.split(" \\| ");
                    Deadline deadline = new Deadline(splitInputD[2], splitInputD[3]);
                    if (Integer.parseInt(splitInputD[1]) == 1) {
                        deadline.markAsDone();
                    }
                    String tagD = splitInputD[4];
                    deadline.loadTag(tagD);
                    tasks.add(deadline);
                    break;
                case 'E':
                    String[] splitInputE = input.split(" \\| ");
                    Event event = new Event(splitInputE[2], splitInputE[3]);
                    if (Integer.parseInt(splitInputE[1]) == 1) {
                        event.markAsDone();
                    }
                    String tagE = splitInputE[4];
                    event.loadTag(tagE);
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

    /**
     * Saves tasks in the file.
     *
     * @param tasks list of tasks to be saved.
     * @throws DukeException  If there is an error.
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            File file = new File(filePath);

            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks.getAllTasks()) {
                fw.write(task.getOutput() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("An IOException occurred!! :(");
        }
    }
}

