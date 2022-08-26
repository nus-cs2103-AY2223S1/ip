import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void getFile() throws IOException {
        File file = new File(this.filePath);
        if (file.createNewFile()) {
            System.out.println(file.getName() + " has been created");
        } else {
            System.out.println(file.getName() + " already exists");
        }
    }

    public void updateFile(TaskList lst) throws IOException {
        File file = new File(this.filePath);
        FileWriter writer = new FileWriter(file);
        for (Task task: lst.lst) {
            writer.write(task.toFileString() + "\n");
        }
        writer.close();
    }

    public ArrayList<Task> load() throws FileNotFoundException, IOException {
        ArrayList<Task> lst = new ArrayList<>();
        File file = new File("./src/main/java/duke.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine();
            String[] stringDetails = currentLine.split("\\|");
            String taskType = stringDetails[0];
            String isMark = stringDetails[1];
            String description = stringDetails[2];
            switch (taskType) {
                case "T ":
                    Todo todo = new Todo(description.substring(1));
                    if (isMark.equals(" 1 ")) {
                        todo.mark();
                    }
                    lst.add(todo);
                    break;
                case "D ": {
                    String by = stringDetails[3].substring(1);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                    LocalDate date = LocalDate.parse(by, formatter);
                    Deadline deadline = new Deadline(description.substring(1), date);
                    if (isMark.equals(" 1 ")) {
                        deadline.mark();
                    }
                    lst.add(deadline);
                    break;
                }
                case "E ": {
                    String at = stringDetails[3];
                    Event event = new Event(description.substring(1), at.substring(1));
                    if (isMark.equals(" 1 ")) {
                        event.mark();
                    }
                    lst.add(event);
                    break;
                }
            }
        }
        return lst;
    }
}
