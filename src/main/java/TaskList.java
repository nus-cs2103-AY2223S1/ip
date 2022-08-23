import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    List<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    TaskList(List<Task> tasks) throws DukeException{
        this.tasks = tasks;
    }

    void add(Task task) {
        this.tasks.add(task);
    }

    void remove(int i) {
        this.tasks.remove(i);
    }

    private void parseFileToTasks(String filepath) {
        try {
            Scanner in = new Scanner(new FileReader(filepath));
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] data = line.split(" \\| ");
                Task task = null;
                switch (data[0]) {
                case "T":
                    task = new Todo(data[2]);
                case "D":
                    task = new Deadline(data[2], data[3]);
                case "E":
                    task = new Event(data[2], data[3]);
                }
                if (task == null) {
                    throw new DukeException("task is null");
                }
                this.tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (DukeException e) {
            try {
                (new File("data//tasks.txt")).createNewFile();
            } catch (IOException ex) {
                System.out.println("something went wrong creating taskfile");
            }
        }
    }

    public void print() {
        for (Task t: tasks) {
            System.out.println(t);
        }
    }

    public String toString() {
        String out = "";
        for (Task t: tasks) {
            out += t.toFileString() + "\n";
        }
        return out;
    }
}
