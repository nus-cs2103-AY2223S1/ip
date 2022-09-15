package duke;

import duke.task.*;

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
        EVENT,
        NOTE
    }

    private final File FILE;
    private final String FILE_PATH;
    private final ArrayList<Task> taskLs = new ArrayList<>(100);
    private final ArrayList<Note> noteLs = new ArrayList<>(100);
    /**
     * Creates a storage object to load and save all tasks.
     *
     * @param filePath The text file to load the tasks from and save the tasks to.
     */
    public Storage(String filePath) {
        this.FILE_PATH = filePath;
        this.FILE = new File(filePath);
    }

    protected TaskList load() throws IOException {
        try {
            if (this.FILE.createNewFile()) {
                System.out.println("Dino created a new file: " + FILE.getName() + "\n");
            } else {
                loadFile();
            }
        } catch (IOException e) {
            System.out.println("Dino failed at creating file./n");
            e.printStackTrace();
        }
        return new TaskList(taskLs, noteLs);
    }

    public void loadFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FILE));
        try {
            String current = br.readLine();
            while (current != null) {
                String[] str = current.split("\\|", 3);
                Commands command = Commands.valueOf(str[0].toUpperCase(Locale.ROOT));
                assert command == Commands.DEADLINE || command == Commands.EVENT || command == Commands.TODO
                        || command == Commands.NOTE;
                switch (command) {
                    case DEADLINE:
                        loadDl(str);
                        break;
                    case TODO:
                        loadTodo(str);
                        break;
                    case EVENT:
                        loadEvent(str);
                        break;
                    case NOTE:
                        loadNote(str);
                        break;
                    default:
                        Ui.invalidTask();
                        break;
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
        taskLs.add(deadline);
        if (Objects.equals(str[1], "1")) {
            deadline.markAsDone();
        }
    }

    private void loadTodo(String[] str) throws DukeException {
        Task todo = new ToDo(str[2]);
        taskLs.add(todo);
        if (Objects.equals(str[1], "1")) {
            todo.markAsDone();
        }
    }

    private void loadEvent(String[] str) throws DukeException {
        String[] evnt = str[2].split("\\|", 2);
        Task event = new Event(evnt[0], evnt[1]);
        taskLs.add(event);
        if (Objects.equals(str[1], "1")) {
            event.markAsDone();
        }
    }

    private void loadNote(String[] str) throws DukeException {
        Note note = new Note(str[1]);
        noteLs.add(note);
    }

    public void writeFile(TaskList tasks) {
        try {
            FileWriter myWriter = new FileWriter(this.FILE_PATH);
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < tasks.getTaskSize(); i++) {
                Task myTask = tasks.getTask(i);
                str.append(format(myTask));
            }
            for (int i = 0; i < tasks.getNoteSize(); i++) {
                Note myNote = tasks.getNote(i);
                str.append(format(myNote));
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

    private String format(Note note) {
       return "NOTE|" + note.getDescription() + "\n";
    }
}
