import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class List {
    private static final String FILE_PATH = "././././data/duke.txt";
    private ArrayList<Task> data;

    public List() {
        this.data = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(FILE_PATH));
            while (sc.hasNext()) {
                String s = sc.nextLine(); 
                Task task;
                switch (s.charAt(1)) {
                case 'T':
                    task = new ToDoTask(s.substring(7));
                    break;
                case 'D':
                    String[] parts = s.substring(7).split(" \\(by: ");
                    task = new DeadlineTask(parts[0], parts[1]);
                    break;
                case 'E':
                    String[] sections = s.substring(7).split(" \\(at: ");
                    task = new EventTask(sections[0], sections[1]);
                    break; 
                default:
                    throw new DukeException("Unrecognised Task Type");
                }
                if (s.charAt(4) == 'X') task.mark(true);
                data.add(task);
            }
        } catch (IOException | DukeException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void addTask(Task task) {
        data.add(task);
        
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            fw.write(task.toString() + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        
        System.out.println("Got it. I've added this task:\n" +
                " " + task + "\n" +
                "Now you have " + data.size() + " tasks in the list.\n");
    }
    
    public void writeDataFile (int pos, Task task) {
        try {
            Scanner sc = new Scanner(new File(FILE_PATH));
            StringBuilder copy = new StringBuilder();
            int counter = 0;
            while (sc.hasNext()) {
                if (counter == pos) {
                    if (task != null) copy.append(task).append("\n");
                    sc.nextLine();
                } else {
                    copy.append(sc.nextLine()).append("\n");
                }
                counter++;
            }
            String fileContents = copy.toString();
            sc.close();
            FileWriter fw = new FileWriter(FILE_PATH, false);
            fw.write(fileContents);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void markTask (int pos, boolean isDone) {
        Task task = data.get(pos);
        task.mark(isDone);
        writeDataFile(pos, task);
        System.out.println(
                ( isDone ? "Nice! I've marked this task as done:\n " : "OK, I've marked this task as not done yet:\n ")
                        + task + "\n"
        );
    }
    
    public void deleteTask(int pos) {
        Task task = data.get(pos);
        data.remove(pos);
        writeDataFile(pos, null);
        System.out.println("Noted. I've removed this task:\n " + 
                task + "\n" +
                "Now you have " + data.size() + " tasks in the list.\n");
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < data.size(); i++) {
            result.append(i + 1).append(".").append(data.get(i)).append("\n");
        }
        return result.toString();
    }
}

// must wait for data to be saved before entering another command 