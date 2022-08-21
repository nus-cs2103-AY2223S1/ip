import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents storage to load tasks from file and save tasks in the file.
 */
public class Storage {
    private final String filePath;
    private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructor for Storage.
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * The method to read data from input file path.
     * @return ArrayList
     * @throws IOException
     */
    public ArrayList<Task> read() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File myData = new File(filePath);
            Scanner sc = new Scanner(myData);
            while (sc.hasNext()) {
                String input = sc.nextLine();
                String[] task = input.split(" \\| ");
                boolean isDone = task[1].equals("1");
                if (task[0].equals("T")) {
                    tasks.add(new Todo(task[2], isDone));
                } else if (task[0].equals("D")) {
                    tasks.add(new Deadline(task[2], isDone, LocalDateTime.parse(task[3], timeFormat)));
                } else if (task[0].equals("E")) {
                    tasks.add(new Event(task[2], isDone, LocalDateTime.parse(task[3], timeFormat)));
                }
            }
        } catch (FileNotFoundException e) {
            if (new File("data").mkdir()) {
                System.out.println("Folder does not exist");
            } else if (new File("data/duke.txt").createNewFile()) {
                System.out.println("I cannot find the file duke.txt");
            }
        }
        return tasks;
    }

    /**
     * The method to write data to input file path.
     * @param task
     */
    public void write(ArrayList<Task> task) {
        try {
            FileWriter fileWriter = new FileWriter(filePath, false);
            for (Task t : task) {
                fileWriter.write(t.formatChange() + "\n");
            }
            fileWriter.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}