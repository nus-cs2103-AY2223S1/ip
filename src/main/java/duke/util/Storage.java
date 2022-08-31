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

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Class that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private TaskList taskList = new TaskList(new ArrayList<>());
    private static String FILE_PATH;

    public Storage(String filePath) {
        FILE_PATH = filePath;
    }

    /**
     * Reads in saved information from the saved file.
     *
     * @throws IOException the io exception
     */
    public void storageRead() throws IOException {
        try {
            File file = new File(FILE_PATH);
            FileReader fr = new FileReader(file.getPath());
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            if (line == null) {
                System.out.println("Your task list is empty.");
            } else {
                System.out.println("These are the tasks you had previously");
                while (line != null) {
                    String[] segments = line.split(">");
                    switch (segments[0]) {
                    case "T":
                        taskList.createTaskSilently(new Todo(segments[2]));
                        if (segments[1].equals("X")) {
                            taskList.getTask(taskList.getSize() - 1).setDone();
                        }
                        break;

                    case "E":
                        String time = segments[3].strip();
                        LocalDate date = LocalDate.parse(time);
                        taskList.createTaskSilently(new Event(segments[2], date));
                        if (segments[1].equals("X")) {
                            taskList.getTask(taskList.getSize() - 1).setDone();
                        }
                        break;

                    case "D":
                        String time2 = segments[1].strip();
                        LocalDate date2 = LocalDate.parse(time2);
                        taskList.createTaskSilently(new Deadline(segments[2], date2));
                        if (segments[1].equals("X")) {
                            taskList.getTask(taskList.getSize() - 1).setDone();
                        }
                        break;

                    default:
                        break;

                    }
                    line = br.readLine();
                }
            }
            String toDisplay = "  <----\n";
            for (int i = 0; i < taskList.getSize(); i++) {
                toDisplay += "  " + (i + 1) + ": " + taskList.getTask(i) + "\n";
            }
            toDisplay += "  ---->";
            System.out.println(toDisplay);
            br.close();
        } catch (FileNotFoundException e) {
            if (new File("data").mkdir()) {
                System.out.println("Directory does not exist, creating one for you.");
                System.out.println("Creating a save file for you (duke.txt)");
                new File("data/duke.txt").createNewFile();
            } else if (new File("data/duke.txt").createNewFile()) {
                System.out.println("Save file does not exist, creating one for you.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the current information to a save file.
     *
     * @param taskList the task list
     */
    public static void storageWrite(TaskList taskList) {
        try {
            File myFile = new File(FILE_PATH);
            OutputStream os = new FileOutputStream(myFile);
            PrintWriter pw = new PrintWriter(os);
            for (Task task : taskList.getTaskList()) {
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
        return this.taskList;
    }
}
