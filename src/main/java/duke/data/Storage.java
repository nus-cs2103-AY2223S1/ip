package duke.data;

import duke.exception.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File f;

    public Storage(String filepath) throws IOException {
        try {
            f = new File(filepath);
            f.getParentFile().mkdirs();
            if (f.createNewFile()) {
                System.out.println("File created.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> load() throws FileNotFoundException, DukeException {
        ArrayList<Task> ls = new ArrayList<>();
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                String taskType = s.split("\\|")[0];
                boolean isMarkedDone = s.split("\\|")[1].equals(" 1 ");
                String taskDescription = s.split("\\|")[2];
                if (taskType.equals("D ")) {
                    String taskDate = s.split("\\|")[3];
                    Task task = new Deadline(taskDescription, taskDate, true);
                    task.toggleIsDone(isMarkedDone);
                    ls.add(task);
                } else if (taskType.equals("E ")) {
                    String taskDate = s.split("\\|")[3];
                    Task task = new Event(taskDescription, taskDate, true);
                    task.toggleIsDone(isMarkedDone);
                    ls.add(task);
                } else {
                    Task task = new Todo(taskDescription);
                    task.toggleIsDone(isMarkedDone);
                    ls.add(task);
                }
            }
            sc.close();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return ls;
    }

    /**
     * Update the file to write to tasks.txt.
     *
     * @throws IOException e.
     */
    public void updateFile(TaskList tasks) throws IOException {
        try {
            FileWriter fw = new FileWriter(f);
            for (int i = 1; i <= tasks.getSize(); i++) {
                String s = "";
                Task task = tasks.getTask(i);
                String taskType = task.toString().substring(1, 2);
                s += taskType;
                if (task.getIsDone()) {
                    s += " | 1 | ";
                } else {
                    s += " | 0 | ";
                }
                if (taskType.equals("T")) {
                    s += task.getDescription() + "\n";;
                } else {
                    s += task.getDescription() + " | " + task.getDate() + "\n";
                }
                fw.write(s);
                fw.flush();
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
