package pikachu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import pikachu.task.Deadline;
import pikachu.task.Event;
import pikachu.task.Task;
import pikachu.task.Todo;

/**
 * Represents a storage process for the task data. A <code>Storage</code> object corresponds to
 * a filepath to put the file e.g., <code>/User/Pikachu/MyManager</code>
 */
public class Storage {
    String filepath;
    File f;

    public Storage(String fp) {
        filepath = fp;

    }

    /**
     * Returns the initial tasks in the storage space when starting the Pikachu task manager
     *
     * @return tasks in the storage space.
     * @throws PikachuException If anything unexpected happen (e,g invalid filepath).
     */
    public List<Task> load() throws PikachuException{
        List<Task> initialTasks = new ArrayList<>();
        String currLine;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            while ((currLine = reader.readLine()) != null) {
                if (currLine.startsWith("T")) {
                    String[] taskDetails = currLine.split(" \\| ",3);
                    boolean isDone = taskDetails[1].equals("1");
                    initialTasks.add(new Todo(taskDetails[2],isDone));
                } else if (currLine.startsWith("D")) {
                    String[] taskDetails = currLine.split(" \\| ",4);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
                    taskDetails[3] = taskDetails[3].substring(0, taskDetails[3].length()-1);
                    LocalDate lt = LocalDate.parse(taskDetails[3], formatter);
                    boolean isDone = taskDetails[1].equals("1");
                    initialTasks.add(new Deadline(taskDetails[2],isDone, lt));
                } else if (currLine.startsWith("E")) {
                    String[] taskDetails = currLine.split(" \\| ",4);
                    boolean isDone = taskDetails[1].equals("1");
                    initialTasks.add(new Event(taskDetails[2],isDone, taskDetails[3]));
                } 
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e);
            throw new PikachuException("No previous record available");
        }
        

        return initialTasks;
        
    }

    /**
     * Saves the new list of task to storage space.
     *
     * @param tasks new list of tasks.
     * @throws PikachuException If anything unexpected happen (e,g invalid filepath).
     */
    public void save(List<Task> tasks) throws PikachuException {
        try {
            f = new File(filepath);
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            if (!f.exists()) {
                f.createNewFile();
            }
            FileWriter myWriter = new FileWriter(filepath);
            for (Task taskie :tasks) {
                String str;

                if (taskie.getName() == "T") {
                    str = String.format("%s | %d | %s \n", taskie.getName(), taskie.isDone ? 1 : 0, taskie.description);
                } else {
                    str = String.format("%s | %d | %s | %s \n", taskie.getName(), taskie.isDone ? 1 : 0, taskie.description, taskie.getTiming());
                }
                myWriter.write(str);
            }
            myWriter.close();
        } catch (Exception e) {
            throw new PikachuException("Something wrong with the saving process! Buy a new computer!");
        }

    }

}
