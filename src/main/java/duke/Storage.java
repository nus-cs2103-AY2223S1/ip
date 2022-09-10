package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.Todo;

/**
 * Storage to read and save tasks into an ArrayList of task from duke.txt and
 * to write tasks from an Arraylist into duke.txt.
 */
public class Storage {

    private String filePath;
    private ArrayList<Task> tasks;
    private Ui ui;

    /**
     * Constructor of Storage.
     *
     * @param ui Ui to show read and write messages
     * @param filePath Path of text file
     */
    public Storage(Ui ui, String filePath) {
        this.ui = ui;
        this.filePath = filePath;
    }

    /**
     * Loads the file and save tasks into ArrayList.
     *
     * @return ArrayList with task read from the text file
     * @throws DukeException If invalid commands or arguments
     */
    public ArrayList<Task> load() {
        readFile();
        return tasks;
    }

    /**
     * Reads the file and create directory or file if either one is missing.
     * If the file is not missing, read the file and add task into ArrayList.
     *
     * @throws DukeException If there is invalid task in text file
     */
    public void readFile() {
        try {
            File txt = getTaskText();
            FileReader fr = new FileReader(txt);
            BufferedReader br = new BufferedReader(fr);
            tasks = new ArrayList<>();
            readTasks(br);
        } catch (DukeException msg) {
            System.out.println("The text file contain invalid tasks");
        } catch (IOException e) {
            System.out.println("Unable to read file");
        }
    }

    /**
     * @return full text of tasks
     * @throws IOException If unable to get text.
     */
    private File getTaskText() throws IOException {
        File txt;
        File file;
        file = new File("./data/");
        assert(file != null);
        if (!file.exists()) {
            file.mkdir();
        }

        txt = new File(filePath);
        if (!txt.exists()) {
            txt.createNewFile();
        }
        return txt;
    }

    /**
     * Reads tasks from the text file.
     *
     * @param br reader
     * @throws IOException If unable to read file.
     * @throws DukeException If task is invalid.
     */
    private void readTasks(BufferedReader br) throws IOException, DukeException {
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            String[] info = line.split(" / ", 4);
            assert(info.length >= 3);
            switch (info[0]) {
            case "T":
                tasks.add(new Todo(TaskType.TODO, info[2], info[1].equals("1")));
                break;
            case "D":
                tasks.add(new Deadline(TaskType.DEADLINE, info[2], info[1].equals("1"), info[3]));
                break;
            case "E":
                tasks.add(new Event(TaskType.EVENT, info[2], info[1].equals("1"), info[3]));
                break;
            default:
                throw new DukeException("Invalid task");
            }
        }
    }

    /**
     * Writes file to store task in a text file.
     *
     * @param taskList TaskList to extract task and save it inside a text file
     * @throws DukeException If there is invalid task
     */
    public void writeFile(TaskList taskList) {
        try {
            FileWriter fw = getFileWriter();
            ArrayList<Task> tasks = taskList.getTasks();
            writeTasks(fw, tasks);
            fw.close();
        } catch (IOException msg) {
            System.out.println("Failed to save file.");
        }
    }

    /**
     * Writes task into the file.
     *
     * @param fw file writer
     * @param tasks task to write
     * @throws IOException If unable to write task into file.
     */
    private void writeTasks(FileWriter fw, ArrayList<Task> tasks) throws IOException {
        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);

            String type = getTypeText(curr);
            String marked = curr.isMarked()
                    ? "1 / "
                    : "0 / ";
            String name = curr.getName() + " / ";;
            LocalDateTime time = getLocalDateTimeText(curr);

            String line = getWriteText(type, marked, name, time);
            fw.write(line);
        }
    }

    /**
     * @param curr task
     * @return local date time text of task
     */
    private LocalDateTime getLocalDateTimeText(Task curr) {
        LocalDateTime time = null;
        switch (curr.getTaskType()) {
        case DEADLINE:
            Deadline dl = (Deadline) curr;
            time = dl.getByTime();
            break;
        case EVENT:
            Event event = (Event) curr;
            time = event.getAtTime();
            break;
        default:
            break;
        }
        return time;
    }

    /**
     * @param curr task
     * @return task type text
     */
    private String getTypeText(Task curr) {
        String type = "";
        switch (curr.getTaskType()) {
        case TODO:
            type = "T / ";
            break;
        case DEADLINE:
            type = "D / ";
            break;
        case EVENT:
            type = "E / ";
            break;
        default:
            break;
        }
        return type;
    }

    /**
     * @param type task type
     * @param marked mark status of task
     * @param name name of task
     * @param time time of task
     * @return text to write with task info
     */
    private String getWriteText(String type, String marked, String name, LocalDateTime time) {
        String line = type + marked + name;
        if (time != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm, d/MM/yyyy");
            line += time.format(formatter);
        }
        line += "\n";
        return line;
    }

    /**
     * @return File to write on
     * @throws IOException If file invalid
     */
    private FileWriter getFileWriter() throws IOException {
        File writeF = new File("./data/duke.txt");
        assert(writeF != null);
        if (!writeF.exists()) {
            writeF.createNewFile();
        }
        FileWriter fw = new FileWriter(writeF);
        return fw;
    }
}
