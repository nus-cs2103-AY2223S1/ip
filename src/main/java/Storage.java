import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    //File to store the list of tasks inputted by user
    private File listOfTasks;

    public Storage(String listOfTasks) {
        this.listOfTasks = new File(listOfTasks);
    }

    public List<Task> load() throws IOException, DukeException {
        List<Task> lst = new ArrayList<>();
        Scanner input = new Scanner(listOfTasks);
            while (input.hasNextLine()) {
                String taskInString = input.nextLine();
                String[] taskInArray = taskInString.split(" \\| ");
                String taskType = taskInArray[0];
                switch (taskType) {
                    case "T" : {
                        Task task= new Todo(taskInArray[2]);
                        lst.add(task);
                        if (taskInArray[1].equals("1")) {
                            task.markAsDone();
                        }
                        break;
                    }
                    case "D" : {
                        Task task = new Deadline(taskInArray[2], taskInArray[3]);
                        lst.add(task);
                        if (taskInArray[1].equals("1")) {
                            task.markAsDone();
                        }
                        break;
                    }
                    case "E" : {
                        Task task = new Event(taskInArray[2], taskInArray[3]);
                        lst.add(task);
                        if (taskInArray[1].equals("1")) {
                            task.markAsDone();
                        }
                        break;
                    }
                    default:
                        throw new DukeException("OOPS!I cannot find a valid task type!");
                }
            }
            return lst;
    }

    public void save(TaskList tasklist) {
        try {
            FileWriter fw = new FileWriter(listOfTasks.getPath());
            for (Task task : tasklist.getTasks()) {
                fw.write(task.savedString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("OOPS! I cant seem to save your tasks in the file!");
        };
    }
}
