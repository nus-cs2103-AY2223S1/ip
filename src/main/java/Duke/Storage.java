package duke;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Helper function to write task descriptions to the file
     * @param task task which we want information for
     */
    public void writeToFile(Task task) {
        try {
            FileWriter writer = new FileWriter(this.filePath, true);
            writer.write(task.toString() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error occured while writing to file");
        }
    }

    public void fileUpdater(Task newTask, boolean delete, int index) {
        try {
            List<String> list = Files.readAllLines(Path.of(this.filePath));
            if (delete) {
                list.remove(index);
            } else {
                list.set(index, newTask.toString());
            }

            // after task list is updated, rewrite to the file
            FileWriter writer = new FileWriter(this.filePath, false);
            for (String x: list) {
                writer.write(x + "\n");
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("Error occured while updating the file");

        }
    }


    /**
     * Initialise the list from the .txt file
     * @param list list storing all tasks
     */
    public void listInit(List<Task> list) {
        try {
            List<String> tasks = Files.readAllLines(Path.of(this.filePath));
            for (String x : tasks) {
                String taskType = x.substring(1,2);
                Boolean done = x.substring(4,5).equals("X");
                String[] arr = x.substring(6).split("\\(");
                String taskName = arr[0].trim();
                // String date = arr[1].replace(")","").trim();
                Task toAdd;
                if (taskType.equals("T")) {
                    toAdd = new ToDo(taskName);
                } else if (taskType.equals("D")) {
                    toAdd = new Deadline(taskName, arr[1].replace(")", "").
                            replace("by","").trim(), true);
                } else {
                    toAdd = new Event(taskName, arr[1].replace(")","").
                            replace("at","").trim(), true);
                }

                if (toAdd == null) {
                    throw new DukeException("File could not be read properly to retrieve task list.");
                } else {
                    if (done) {
                        toAdd.Done();
                    }
                    list.add(toAdd);
                }
            }
        } catch (IOException e) {
            System.out.println("Error occured while reading the file for existing tasks");
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }

    public void openFile() {
        try {
            File file = new File("./tasks.txt");
            if (!file.exists()) {
                System.out.println("File doesn't exist, creating file");
                file.createNewFile();
                System.out.println("File created");
            } else {
                System.out.println("File exists");
                // read from file and add to task list
            }
        } catch (IOException e) {
            System.out.println("Error while opening file");
        }
    }

}
