import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File file;

    public Storage() throws IOException {
        File directory = new File("data/");
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File("data/duke.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        this.file = file;
    }

    public ArrayList<Task> loadFile() throws DukeException, IOException {
        Scanner sc = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<>();

        while (sc.hasNext()) {
            String curr = sc.nextLine();
            String[] info = curr.split("\\|");
            switch (info[0]) {
            case "T":
                Task addTodo = new Todo(info[2], (info[1].equals("1")));
                tasks.add(addTodo);
                break;
            case "E":
                Task addEvent = new Event(info[2], LocalDate.parse(info[3]), info[1].equals("1"));
                tasks.add(addEvent);
                break;
            case "D":
                Task addDeadline = new Deadline(info[2], LocalDate.parse(info[3]), info[1].equals("1"));
                tasks.add(addDeadline);
                break;
            default:
                throw new IOException("Error in reading file");
            }
        }

        return tasks;
    }

    public void writeFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks.getTasks()) {
            if (t instanceof Todo) {
                sb.append("T")
                        .append("|")
                        .append(t.isDone ? "1" : "0")
                        .append("|")
                        .append(t.description)
                        .append(System.lineSeparator());
            } else if (t instanceof Deadline) {
                sb.append("D")
                        .append("|")
                        .append(t.isDone ? "1" : "0")
                        .append("|")
                        .append(t.description)
                        .append("|")
                        .append(((Deadline) t).by)
                        .append(System.lineSeparator());
            } else {
                sb.append("E")
                        .append("|")
                        .append(t.isDone ? "1" : "0")
                        .append("|")
                        .append(t.description)
                        .append("|")
                        .append(((Event) t).at)
                        .append(System.lineSeparator());
            }
        }
        fw.write(sb.toString());
        fw.close();
    }
}
