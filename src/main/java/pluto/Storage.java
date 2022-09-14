package pluto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import pluto.task.Deadline;
import pluto.task.Event;
import pluto.task.Task;
import pluto.task.Todo;

/**
 * Read and write tasks from a local file.
 */
public class Storage {
    /** File object to read and write tasks into */
    private File file;
    /** Path of the file */
    private String path;

    /**
     * Constructor that initializes global variables.
     * @param path Path of the file.
     * @throws PlutoException If file is not created properlu.
     */
    public Storage(String path) throws PlutoException {
        try {
            this.file = new File(path);
            this.path = path;
            file.createNewFile();
        } catch (IOException e) {
            throw new PlutoException("OOPS!!! Couldn't find/create data file.");
        }
    }

    /**
     * Loads the tasks from the local file to a list.
     * @return List of tasks read from the local file.
     * @throws PlutoException If file cannot be parsed properly to tasks.
     */
    public ArrayList<Task> load() throws PlutoException {
        ArrayList<Task> missions = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String text = sc.nextLine().strip();
                String[] textArr = text.split("\\s+\\|\\s+", 3);
                assert textArr.length != 0 : "textArr shouldn't be empty";
                String command = textArr[0];
                assert textArr.length == 3 : "textArr should have 3 elements";
                int lastPipe = textArr[2].lastIndexOf("|");
                String date = null;
                String description = null;
                if (lastPipe != -1) {
                    date = textArr[2].substring(lastPipe + 1).strip();
                    description = textArr[2].substring(0, lastPipe).strip();
                }
                switch (command) {
                case "T":
                    Task todo = new Todo(textArr[2]);
                    markTasks(todo, textArr[1]);
                    missions.add(todo);
                    break;
                case "D":
                    assert date != null : "Deadline date cannot be null";
                    assert description != null : "Deadline description cannot be null";
                    Task deadline = new Deadline(description, Parser.parseDate(date));
                    markTasks(deadline, textArr[1]);
                    missions.add(deadline);
                    break;
                case "E":
                    assert date != null : "Event date cannot be null";
                    assert description != null : "Event description cannot be null";
                    Task event = new Event(description, Parser.parseDate(date));
                    markTasks(event, textArr[1]);
                    missions.add(event);
                    break;
                default:
                    throw new PlutoException("OOPS!!! Couldn't parse previous tasks.");
                }
            }
        } catch (IOException e) {
            throw new PlutoException("OOPS!!! " + e.getMessage());
        }
        return missions;
    }

    /**
     * Writes strings at the end of the file.
     * @param str String to be written.
     * @throws IOException If str cannot be written properly.
     */
    public void appendToFile(String str) throws IOException {
        FileWriter fwriter = new FileWriter(this.path, true);
        fwriter.write(str + "\n");
        fwriter.close();
    }

    /**
     * Marks the status of tasks parsed during load().
     * @param t Task to be marked.
     * @param status Status of the task after marking.
     */
    private void markTasks(Task t, String status) {
        if (Integer.parseInt(status) == 1) {
            t.markAsDone();
        }
    }

    /**
     * Writes all the tasks in a blank file.
     * @param tasks List of tasks to write.
     * @throws IOException If problem encountered during writing tasks.
     * @throws PlutoException If accessing tasks from TaskList generates error.
     */
    public void rewriteFile(TaskList tasks) throws IOException, PlutoException {
        FileWriter fwriter = new FileWriter(this.path);
        for (int i = 0; i < tasks.nTasks(); i++) {
            fwriter.write(tasks.getTask(i).toFile() + "\n");
        }
        fwriter.close();
    }


}
