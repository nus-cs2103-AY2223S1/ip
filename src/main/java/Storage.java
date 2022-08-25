import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class Storage{
    private TaskList taskList = new TaskList(new ArrayList<>());

    public void storageRead() throws IOException {
            try {
                FileReader fr = new FileReader("./data/duke.txt");
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                System.out.println("These are the tasks you had previously");
                while (line != null) {
                    String[] segments = line.split(">");
                    switch (segments[0]) {
                        case "T":
                            taskList.createTaskSilently(new Todo(segments[2]));
                            if (segments[1].equals("X")) {
                                taskList.getTask(taskList.getSize() - 1).taskDone();
                            }
                            break;

                        case "E":
                            String time = segments[3].strip();
                            LocalDate date = LocalDate.parse(time);
                            taskList.createTaskSilently(new Event(segments[2], date));
                            if (segments[1].equals("X")) {
                                taskList.getTask(taskList.getSize() - 1).taskDone();
                            }
                            break;

                        case "D":
                            String time2 = segments[1].strip();
                            LocalDate date2 = LocalDate.parse(time2);
                            taskList.createTaskSilently(new Deadline(segments[2], date2));
                            if (segments[1].equals("X")) {
                                taskList.getTask(taskList.getSize() - 1).taskDone();
                            }
                            break;
                    }
                    line = br.readLine();
                }
                String toDisplay = "  <----\n";
                for (int i = 0; i < taskList.getSize(); i++) {
                    toDisplay += "  " + (i + 1) + ": " + taskList.getTask(i) + "\n";
                }
                toDisplay += "  ---->";
                System.out.println(toDisplay);
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void storageWrite(ArrayList<Task> taskList) {
        try {
            File myFile = new File("./data/duke.txt");
            OutputStream os = new FileOutputStream(myFile);
            PrintWriter pw = new PrintWriter(os);
            for (Task task : taskList) {
                switch (task.getType()) {

                    case "T":
                        pw.println(task.getType() + ">" + task.getStatusIcon() + ">" + task.description);
                        break;

                    case "E":
                        Event event = (Event) task;
                        pw.println(event.getType() + ">" + event.getStatusIcon() + ">"
                                + event.description + ">" + event.at);
                        break;

                    case "D":
                        Deadline deadline = (Deadline) task;
                        pw.println(deadline.getType() + ">" + deadline.getStatusIcon() + ">"
                                + deadline.description + ">" + deadline.by);
                        break;
                }
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TaskList getTaskList() {
        return this.taskList;
    }
}
