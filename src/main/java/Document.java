import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Document {
    protected Path outputFile;

    public Document(Path f) {
        this.outputFile = f;
    }

    public void refreshList(List<Task> taskList) throws IOException {
        try(BufferedWriter writer = Files.newBufferedWriter(this.outputFile)){
            writer.write("");
            writer.flush();
            for (Task curTask : taskList) {
                if (curTask instanceof Event) {
                    writer.write("Event | " + curTask.getStatusNumber() + " | " + curTask.getDescription() + " | "
                            + ((Event) curTask).getDatetime() + "\n");
                } else if (curTask instanceof Todo) {
                    writer.write("Todo | " + curTask.getStatusNumber() + " | " + curTask.getDescription() + "\n");
                } else if (curTask instanceof Deadline) {
                    writer.write("Deadline | " + curTask.getStatusNumber() + " | " + curTask.getDescription()
                            + " | " + ((Deadline) curTask).getDatetime() + "\n");
                }
            }
        } catch(IOException err) {
            System.out.println("\n:( OOPS! I can't refresh the task file!");
        }
    }

    public List<Task> readTasks() {
        List<Task> inputTasks = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(this.outputFile)) {
            String taskLine = reader.readLine();
            while (taskLine != null) {
                String[] taskDetails = taskLine.split("\\s\\|\\s");
                switch (taskDetails[0]) {
                case "Event":
                    Task loadEvent = new Event(taskDetails[2], taskDetails[3]);
                    if (taskDetails[1].equals("0")) {
                        loadEvent.markAsUndone();
                    } else {
                        loadEvent.markAsDone();
                    }
                    inputTasks.add(loadEvent);
                    taskLine = reader.readLine();
                    break;
                case "Deadline":
                    Task loadDeadline = new Deadline(taskDetails[2], taskDetails[3]);
                    if (taskDetails[1].equals("0")) {
                        loadDeadline.markAsUndone();
                    } else {
                        loadDeadline.markAsDone();
                    }
                    inputTasks.add(loadDeadline);
                    taskLine = reader.readLine();
                    break;
                case "Todo":
                    Task loadTodo = new Todo(taskDetails[2]);
                    if (taskDetails[1].equals("0")) {
                        loadTodo.markAsUndone();
                    } else {
                        loadTodo.markAsDone();
                    }
                    inputTasks.add(loadTodo);
                    taskLine = reader.readLine();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to read from the task list file: " + this.outputFile.toString());
        }
        return inputTasks;
    }
}
