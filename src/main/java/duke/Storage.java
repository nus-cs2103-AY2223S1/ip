package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 * The Storage class deals with loading tasks from the data file and
 * saving tasks in the file.
 *
 * @author Edric Yeo
 */
public class Storage {

    private String filePath;

    /**
     * Constructor for a Storage instance based on a given data file.
     *
     * @param filePath The filePath of the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Method that loads the saved data file.
     *
     * @return A List of Tasks that have been stored in the data file.
     */
    public List<Task> load() throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] words = input.split(" # ");

            // check description
            String desc = words[2];
            // check task
            String task = words[0];
            if (task.equals("T")) {
                Task todo = new Todo(desc);
                // check marked
                if (words[1].equals("1")) {
                    todo.mark();
                }
                tasks.add(todo);
            } else if (task.equals("D")) {
                Task deadline = new Deadline(desc, words[3]);
                // check marked
                if (words[1].equals("1")) {
                    deadline.mark();
                }
                tasks.add(deadline);
            } else if (task.equals("E")) {
                Task event = new Event(desc, words[3]);
                // check marked
                if (words[1].equals("1")) {
                    event.mark();
                }
                tasks.add(event);
            } else {
                System.out.println("Incorrect File Input!");
            }
        }
        return tasks;
    }

    /**
     * Method that saves the current tasks in the data file.
     *
     * @param tasks The current List of Tasks.
     */
    public void save(TaskList tasks) throws IOException {
        tasks.saveTasks(this.filePath);
    }
}
