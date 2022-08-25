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



public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

    public void save(TaskList tasklist) throws IOException {
        tasklist.saveTasks(this.filePath);
    }


}
