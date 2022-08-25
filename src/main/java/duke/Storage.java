import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        File target = new File(filePath);
        if (target.exists()) {
            Scanner sc = new Scanner(target);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] components = line.split(Pattern.quote(" | "));
                String taskType = components[0];
                boolean isDone = components[1].equals("1");
                String desc = components[2];
                String cmd;
                LocalDate d;
                switch (taskType) {
                    // TODO
                    case "T":
                        cmd = "todo " + desc;
                        addTodo(cmd);
                        break;

                    // DEADLINE
                    case "D":
                        String deadline = components[3];
                        d = LocalDate.parse(deadline);
                        cmd = "deadline " + desc + " /by " + d;
                        addDeadline(cmd);
                        break;

                    // EVENT
                    case "E":
                        String time = components[3];
                        d = LocalDate.parse(time);
                        cmd = "event " + desc + " /at " + d;
                        addEvent(cmd);
                        break;
                }
                if (isDone)
                    tasks.get(tasks.size() - 1).markAsDone();
            }
        } else {
            File parent = new File(DIR + "/data");
            boolean isFolderCreated = parent.mkdir();
            boolean isFileCreated = target.createNewFile();
        }
    }

    public void store() throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task curr : tasks) {
            String status = curr.getStatusIcon().equals("X") ? "1" : "0";
            if (curr instanceof Todo) {
                writer.write("T | " + status + " | " + curr.getDescription() + "\n");
            } else if (curr instanceof Deadline) {
                writer.write("D | " + status + " | " + curr.getDescription() + "| " +
                        ((Deadline) curr).getBy().toString() + "\n");
            } else if (curr instanceof Event) {
                writer.write("E | " + status + " | " + curr.getDescription() + "| " +
                        ((Event) curr).getAt().toString() + "\n");
            }
        }
        writer.close();
    }

}
