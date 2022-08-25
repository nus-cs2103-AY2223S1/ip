import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public void save(TaskList tasks) {
        try {
            File file = new File("src/main/duke.txt");
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                fw.write(task.saveTaskAsString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public ArrayList<Task> loadSaveData() throws DukeException, IOException {

        ArrayList<Task> loadedTasks = new ArrayList<>();
        this.file = new File("src/main/duke.txt");
        if (!file.exists()) {
            file.createNewFile();
        } else {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String taskString = scan.nextLine();

                // split and organise task data
                String[] taskSplit = taskString.split(" \\| ");
                String taskType = taskSplit[0];
                boolean isTaskDone = taskSplit[1].equals("1");
                String taskDescription = taskSplit[2];

                Task task;
                switch (taskType) {
                    case "T": {
                        task = new ToDo(taskDescription);
                        break;
                    }
                    case "D": {
                        String taskDate = taskSplit[3];
                        task = new Deadline(taskDescription, taskDate);
                        break;
                    }
                    case "E": {
                        String taskDate = taskSplit[3];
                        task = new Event(taskDescription, taskDate);
                        break;
                    }
                    default:
                        throw new DukeException("Task type is not supported!");
                }
                if (isTaskDone) {
                    task.markAsDone();
                }
                loadedTasks.add(task);
            }
        }
        return loadedTasks;
    }

}
