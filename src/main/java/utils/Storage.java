package utils;

import objects.Deadline;
import objects.Event;
import objects.Task;
import objects.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private static final String FILE_PATH = "data/tasks.txt";
    private static final File file = new File(FILE_PATH);

    /**
     * This function reads the text file at the specified file path and parses the tasks,
     * adding them into list of tasks in the app. It allows for persistent storage of tasks.
     *
     * @param tasks list of Task objects of the app
     * @throws FileNotFoundException exception thrown when the txt file is not found
     */
    public void loadTasks(List<Task> tasks) throws IOException {
        file.getParentFile().mkdir();
        file.createNewFile();
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String[] dataChunk = sc.nextLine().split(" \\| ");
            switch (dataChunk[0]) {
            case "T": {
                loadTodo(tasks, dataChunk);
                break;
            }
            case "D": {
                loadDeadline(tasks, dataChunk);
                break;
            }
            case "E": {
                loadEvent(tasks, dataChunk);
                break;
            }
            }
        }
    }

    private void loadTodo(List<Task> tasks, String[] dataChunk) {
        // "1" means done, "0" means not done
        Boolean isDone = dataChunk[1].equals("1");
        String name = dataChunk[2];
        tasks.add(new Todo(name, isDone));
    }

    private void loadDeadline(List<Task> tasks, String[] dataChunk) {
        // "1" means done, "0" means not done
        boolean isDone = dataChunk[1].equals("1");
        String name = dataChunk[2] + " ";
        String endDateTime = dataChunk[3];
        Deadline curr = new Deadline(name, endDateTime);
        tasks.add(curr);
        if (isDone) {
            curr.markAsDone();
        }
    }

    private void loadEvent(List<Task> tasks, String[] dataChunk) {
        // "1" means done, "0" means not done
        boolean isDone = dataChunk[1].equals("1");
        String name = dataChunk[2] + " ";
        String periodDateTime = dataChunk[3];
        Event curr = new Event(name, periodDateTime);
        tasks.add(curr);
        if (isDone) {
            curr.markAsDone();
        }
    }

    /**
     * This function will write the tasks from the list of Task objects into
     * a txt file, ensuring persistent storage of the tasks. When the app is opened
     * again after being terminated, the tasks saved will be available again.
     *
     * @param tasks list of Task objects in the app
     * @throws IOException an exception thrown when there is a problem with the input/output (IO)
     */
    public void saveTasks(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(file.getPath());
        for (Task task : tasks) {
            String isDoneSymbol = task.getIsDone() ? "1" : "0";
            if (task instanceof Todo) {
                fw.write("T | " + isDoneSymbol + " | " + task.getName() + "\n");
            } else if (task instanceof Deadline) {
                fw.write("D | " + isDoneSymbol + " | " + task.getName()
                        + " | " + ((Deadline) task).getDateTime() + "\n");
            } else if (task instanceof Event) {
                fw.write("E | " + isDoneSymbol + " | " + task.getName()
                        + " | " + ((Event) task).getDateTime() + "\n");
            }
        }
        fw.close();
    }
}
