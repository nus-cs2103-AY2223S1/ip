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
    private static String filePath;
    private TaskList tasks = new TaskList(new ArrayList<>());

    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Reads in saved information from the saved file.
     *
     * @throws IOException the io exception
     */
    public void readStorage(Response response) throws IOException {
        try {
            File file = new File(filePath);
            FileReader fr = new FileReader(file.getPath());
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            if (line == null) {
                response.append("Your task list is empty.");
            } else {
                assert line != null : "Line read is empty";
                response.append("These are the tasks you had previously:\n");
                while (line != null) {
                    String[] segments = line.split(">");
                    switch (segments[0]) {
                    case "T":
                        assert segments.length == 4 : "A todo task follows the format 'todo task'";
                        tasks.createTaskSilently(new Todo(segments[2]));
                        if (segments[1].equals("X")) {
                            tasks.getTask(tasks.getSize() - 1).setDone();
                        }
                        if (segments[3].startsWith("#")) {
                            tasks.getTask(tasks.getSize() - 1).setTag(segments[3]);
                        }
                        break;

                    case "E":
                        assert segments.length == 5 : "An event task follows the format 'event task /at YYYY-MM-DD'";
                        String time = segments[3];
                        LocalDate date = LocalDate.parse(time);
                        tasks.createTaskSilently(new Event(segments[2], date));
                        if (segments[1].equals("X")) {
                            tasks.getTask(tasks.getSize() - 1).setDone();
                        }
                        if (segments[4].startsWith("#")) {
                            tasks.getTask(tasks.getSize() - 1).setTag(segments[4]);
                        }
                        break;

                    case "D":
                        assert segments.length == 5
                                : "A deadline task follows the format 'deadline task /by YYYY-MM-DD'";
                        String time2 = segments[3];
                        LocalDate date2 = LocalDate.parse(time2);
                        tasks.createTaskSilently(new Deadline(segments[2], date2));
                        if (segments[1].equals("X")) {
                            tasks.getTask(tasks.getSize() - 1).setDone();
                        }
                        if (segments[4].startsWith("#")) {
                            tasks.getTask(tasks.getSize() - 1).setTag(segments[4]);
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
    public static void writeStorage(TaskList tasks) {
        try {
            File myFile = new File(filePath);
            OutputStream os = new FileOutputStream(myFile);
            PrintWriter pw = new PrintWriter(os);
            for (Task task : tasks.getTaskList()) {
                String type = task.getType();
                switch (type) {

                case "T":
                    assert type == "T" : "Task must be a todo";
                    Todo todo = (Todo) task;
                    pw.println(type + ">" + todo.getStatusIcon() + ">" + todo.getDescription() + ">" + todo.getTag());
                    break;

                case "E":
                    assert type == "E" : "Task must be an event";
                    Event event = (Event) task;
                    pw.println(type + ">" + event.getStatusIcon() + ">"
                            + event.getDescription() + ">" + event.getAt() + ">" + event.getTag());
                    break;

                case "D":
                    assert type == "D" : "Task must be a deadline";
                    Deadline deadline = (Deadline) task;
                    pw.println(type + ">" + deadline.getStatusIcon() + ">"
                            + deadline.getDescription() + ">" + deadline.getBy() + ">" + deadline.getTag());
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
