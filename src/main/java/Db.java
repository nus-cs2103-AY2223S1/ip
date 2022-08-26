import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Db {
    private static final String FILE = "./duke.txt";

    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE));
            while (true) {
                try {
                    String taskString = br.readLine();
                    if (taskString == null) {
                        // EOF reached
                        br.close();
                        break;
                    }
                    // process each line into a task
                    String[] taskArray = taskString.split("\\|");
                    // if (taskArray.length < 3) {
                    // continue;
                    // }
                    String taskType = taskArray[0].strip();
                    boolean isDone = taskArray[1].strip().equals("1");
                    String description = taskArray[2].strip();
                    switch (taskType) {
                        case "T":
                            tasks.add(new Todo(isDone, description));
                            break;
                        case "D":
                            String deadlineString = taskArray[3].strip();
                            LocalDate deadline = LocalDate.parse(deadlineString,
                                    DateTimeFormatter.ofPattern("dd MMM yy"));
                            tasks.add(new Deadline(isDone, description, deadline));
                            break;
                        case "E":
                            String eventTimeString = taskArray[3].strip();
                            LocalDate eventTime = LocalDate.parse(eventTimeString,
                                    DateTimeFormatter.ofPattern("dd MMM yy"));
                            tasks.add(new Event(isDone, description, eventTime));
                            break;
                    }
                } catch (IOException e) {
                    System.out.println("IOException...");
                    System.out.println(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFound...");
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    public void save(List<Task> tasks) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE));
            for (Task task : tasks) {
                // process each task into a line
                String taskString = task.toDbString();
                bw.write(taskString);
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            // TODO: handle exception
        }
    }
}
