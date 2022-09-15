package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

/**
 * Loads tasks and saves tasks in the specified file.
 *
 * @author Lim Ai Lin
 */
public class Storage {

    private enum Commands {
        DEADLINE,
        TODO,
        EVENT
    }

    private final File FILE;
    private final String FILE_PATH;
    private final ArrayList<Task> ls = new ArrayList<>(100);

    /**
     * Creates a storage object to load and save all tasks.
     *
     * @param filePath The text file to load the tasks from and save the tasks to.
     */
    public Storage(String filePath) {
        this.FILE_PATH = filePath;
        this.FILE = new File(filePath);
    }

    protected ArrayList<Task> load() throws IOException {
        try {
            if (this.FILE.createNewFile()) {
                System.out.println("Dino created a new file: " + FILE.getName() + "\n");
            } else {
                loadFile();
                System.out.println("Dino found the file in your directory and loaded contents.\n");
            }
        } catch (IOException e) {
            System.out.println("Dino failed at creating file./n");
            e.printStackTrace();
        }
        return ls;
    }

    public void loadFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FILE));
        try {
            String current = br.readLine();
            while (current != null) {
                String[] str = current.split("\\|", 3);
                Commands myTask = Commands.valueOf(str[0].toUpperCase(Locale.ROOT));
                assert myTask == Commands.DEADLINE || myTask == Commands.EVENT || myTask == Commands.TODO;
                switch (myTask) {
                    case DEADLINE:
                        loadDl(str);
                        break;
                    case TODO:
                        loadTodo(str);
                        break;
                    case EVENT:
                        loadEvent(str);
                        break;
                    default:
                        Ui.invalidTask();
                }
                current = br.readLine();
            }
        } catch (IOException | IllegalArgumentException | DukeException e) {
            e.printStackTrace();
        }
        br.close();
    }

    private void loadDl(String[] str) throws DukeException {
        String[] dl = str[2].split("\\|", 2);
        Task deadline = new Deadline(dl[0], dl[1]);
        ls.add(deadline);
        if (Objects.equals(str[1], "1")) {
            deadline.markAsDone();
        }
    }

    private void loadTodo(String[] str) throws DukeException {
        Task todo = new ToDo(str[2]);
        ls.add(todo);
        if (Objects.equals(str[1], "1")) {
            todo.markAsDone();
        }
    }

    private void loadEvent(String[] str) throws DukeException {
        String[] evnt = str[2].split("\\|", 2);
        Task event = new Event(evnt[0], evnt[1]);
        ls.add(event);
        if (Objects.equals(str[1], "1")) {
            event.markAsDone();
        }
    }

    public void writeFile(TaskList tasks) {
        try {
            FileWriter myWriter = new FileWriter(this.FILE_PATH);
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                Task myTask = tasks.get(i);
                str.append(format(myTask));
            }
            myWriter.write(str.toString());
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String format(Task task) {
        if (task instanceof ToDo) {
            return "TODO|" + task.getStatus() + "|" + task.getDescription() + "\n";
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "EVENT|" + task.getStatus() + "|" + task.getDescription() + "|" + event.getAt() + "\n";
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "DEADLINE|" + task.getStatus() + "|" + task.getDescription() + "|" + deadline.getBy() + "\n";
        }
        return "";
    }
}
