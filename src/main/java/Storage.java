import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private List<Task> taskList = new ArrayList<>();
    int count = 0;
    public void add(Task task) {
        taskList.add(task);
        count++;
        System.out.println("Got it. I've added this task:\n " + task.toString());
        this.getCount();
    }
    public void iterate() {
        System.out.println("Here are the tasks in your list:");
        int i = 1;
        for (Task task : taskList) {
            System.out.println(i + "." + task.toString());
            i++;
        }
    }
    public void getCount() {
        System.out.println("Now you have " + this.count + " tasks in the list.");
    }
    public void mark(String str) throws DukeException {
        int index = Integer.parseInt(str) - 1;
        if (index >= count || index < 0) {
            throw new DukeException("☹ OOPS!!! Please enter a valid index");
        }
        this.taskList.get(index).markAsDone();
    }
    public void unmark(String str) {
        int index = Integer.parseInt(str) - 1;
        this.taskList.get(index).markAsNotDone();
    }

    public void delete(String str) throws DukeException {
        int index = Integer.parseInt(str) - 1;
        if (index >= count || index < 0) {
            throw new DukeException("☹ OOPS!!! Please enter a valid index");
        }
        Task task = taskList.get(index);
        System.out.println("Noted. I've removed this task:\n " + task.toString());
        taskList.remove(task);
        count--;
        this.getCount();
    }

    public void readFileContents(String filePath) throws FileNotFoundException, DukeException {
        File file = new File(filePath);
        Scanner s = new Scanner(file);
        System.out.println("Reading the file...");
        System.out.println("-------------------");
        while (s.hasNext()) {
            String str = s.nextLine();
            fileInterpreter(str);
        }
        System.out.println("File reading complete");
        System.out.println("-------------------");
    }

    public void fileInterpreter(String str) throws DukeException {
        Task task;
        String description = str.split("] ", 2)[1];
        if (str.contains("[T]")) {
            task = new ToDo(description);
        } else {
            String message = str.substring(str.indexOf("]", str.indexOf("]") + 1) + 2,
                    str.indexOf(" ("));
            String date = str.substring(str.indexOf(":") + 2, str.indexOf(")"));
            if (str.contains("[D]")){
                task = new Deadline(message, date);
            } else {
                task = new Event(message, date);
            }
        }
        this.add(task);
        if (str.contains("[X]")) {
            task.markAsDone();
        }
    }

    public void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        StringBuilder textToAdd = new StringBuilder();
        for (Task task : taskList) {
            textToAdd.append(task.toString()).append(System.lineSeparator());
        }
        fw.write(textToAdd.toString());
        fw.close();
    }

}
