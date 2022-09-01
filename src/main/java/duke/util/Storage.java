package duke.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;

import duke.Response;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Class that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private TaskList tasks = new TaskList(new ArrayList<>());
    private static String FILE_PATH;

    public Storage(String filePath) {
        FILE_PATH = filePath;
    }

    /**
     * Reads in saved information from the saved file.
     *
     * @throws IOException the io exception
     */
    public void storageRead(Response response) throws IOException {
        try {
            File file = new File(FILE_PATH);
            FileReader fr = new FileReader(file.getPath());
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            if (line == null) {
                response.append("Your task list is empty.");
            } else {
                response.append("These are the tasks you had previously:\n");
                while (line != null) {
                    String[] segments = line.split(">");
                    switch (segments[0]) {
                    case "T":
                        tasks.createTaskSilently(new Todo(segments[2]));
                        if (segments[1].equals("X")) {
                            tasks.getTask(tasks.getSize() - 1).setDone();
                        }
                        break;

                    case "E":
                        String time = segments[3];
                        LocalDate date = LocalDate.parse(time);
                        tasks.createTaskSilently(new Event(segments[2], date));
                        if (segments[1].equals("X")) {
                            tasks.getTask(tasks.getSize() - 1).setDone();
                        }
                        break;

                    case "D":
                        String time2 = segments[3];
                        LocalDate date2 = LocalDate.parse(time2);
                        tasks.createTaskSilently(new Deadline(segments[2], date2));
                        if (segments[1].equals("X")) {
                            tasks.getTask(tasks.getSize() - 1).setDone();
                        }
                        break;

                    default:
                        break;

                    }
                    line = br.readLine();
                }
            }
            for (int i = 0; i < tasks.getSize(); i++) {
                response.append("  " + (i + 1) + ": " + tasks.getTask(i) + "\n");
            }
            br.close();
        } catch (FileNotFoundException e) {
            if (new File("data").mkdir()) {
                response.append("Directory for a save file does not exist, creating one for you.\n");
                response.append("Creating a save file for you (duke.txt)");
                new File("data/duke.txt").createNewFile();
            } else if (new File("data/duke.txt").createNewFile()) {
                response.append("Save file does not exist, creating one for you.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the current information to a save file.
     *
     * @param tasks the task list
     */
    public static void storageWrite(TaskList tasks) {
        try {
            File myFile = new File(FILE_PATH);
            OutputStream os = new FileOutputStream(myFile);
            PrintWriter pw = new PrintWriter(os);
            for (Task task : tasks.getTaskList()) {
                switch (task.getType()) {

                case "T":
                    pw.println(task.getType() + ">" + task.getStatusIcon() + ">" + task.getDescription());
                    break;

                case "E":
                    Event event = (Event) task;
                    pw.println(event.getType() + ">" + event.getStatusIcon() + ">"
                            + event.getDescription() + ">" + event.getAt());
                    break;

                case "D":
                    Deadline deadline = (Deadline) task;
                    pw.println(deadline.getType() + ">" + deadline.getStatusIcon() + ">"
                            + deadline.getDescription() + ">" + deadline.getBy());
                    break;

                default:
                    break;
                }
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets task list.
     *
     * @return the task list
     */
    public TaskList getTaskList() {
        return this.tasks;
    }
}
