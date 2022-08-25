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
     * Constructor of Storage
     *
     * @param ui Ui to show read and write messages
     * @param filePath Path of text file
     */
    public Storage(Ui ui, String filePath) {
        this.ui = ui;
        this.filePath = filePath;
    }

    /**
     * Load the file and save tasks into ArrayList
     *
     * @return ArrayList with task read from the text file
     * @throws DukeException If invalid commands or arguments
     */
    public ArrayList<Task> load() throws DukeException {
        readFile();
        return tasks;
    }

    /**
     * Read the file and create directory or file if either one is missing.
     * If the file is not missing, read the file and add task into ArrayList.
     * @throws DukeException If there is invalid task in text file
     */
    public void readFile() throws DukeException {
        try {
            File file;
            File txt;
            FileReader fr;
            BufferedReader br;

            file = new File("./data/");
            if (!file.exists()) {
                file.mkdir();
            }

            txt = new File(filePath);
            if (!txt.exists()) {
                txt.createNewFile();
            }

            fr = new FileReader(txt);
            br = new BufferedReader(fr);

            String line;
            tasks = new ArrayList<>();

            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }

                String[] info = line.split(" / ", 4);
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
        } catch (DukeException msg) {
            throw new DukeException(msg.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write file to store task in a text file.
     *
     * @param taskList TaskList to extract task and save it inside a text file
     * @throws IOException If error writing files
     * @throws DukeException If there is invalid task
     */
    public void writeFile(TaskList taskList) throws IOException, DukeException {
        try {
            File writeF = new File("./data/duke.txt");
            if (!writeF.exists()) {
                writeF.createNewFile();
            }
            FileWriter fw = new FileWriter(writeF);

            ArrayList<Task> tasks = taskList.getTasks();
            for (int i = 0; i < tasks.size(); i++) {
                Task curr = tasks.get(i);
                String type = "";
                String marked;
                String name;
                LocalDateTime time = null;

                marked = curr.isMarked()
                        ? "1 / "
                        : "0 / ";
                name = curr.getName() + " / ";

                switch (curr.getTaskType()) {
                case TODO:
                    type = "T / ";
                    break;
                case DEADLINE:
                    type = "D / ";
                    Deadline dl = (Deadline) curr;
                    time = dl.getByTime();
                    break;
                case EVENT:
                    type = "E / ";
                    Event event = (Event) curr;
                    time = event.getAtTime();
                    break;
                default:
                    break;
                }

                String line = type + marked + name;

                if (time != null) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm, d/MM/yyyy");
                    line += time.format(formatter);
                }

                line += "\n";
                fw.write(line);
            }

            fw.close();

        } catch (IOException msg) {
            throw new DukeException("Failed to save file");
        }
    }

}
