import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

// loading tasks from the file and saving tasks in the file
public class Storage {

    private File f;

    public Storage (String filepath) throws IOException {
        try {
            f = new File(filepath);
            f.getParentFile().mkdirs();
            if (f.createNewFile()) {
                System.out.println("File created.");
            } else {
                System.out.println("File already exist.");
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
            for (int i = 1 ; i <= tasks.getSize(); i++) {
                String s = "";
                Task task = tasks.getTask(i);
                s += task.toString().substring(1,2);
                if (task.isDone) {
                    s += " | 1 | ";
                } else {
                    s += " | 0 | ";
                }
                s += task.description + " | " + task.getDate() + "\n";
                fw.write(s);
                fw.flush();
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
