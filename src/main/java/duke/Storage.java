package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Storage {
    ArrayList<Task> tasks;
    String filepath;

    public Storage(String filepath) {
        this.tasks = new ArrayList<Task>();
        this.filepath = filepath;
    }

    // Loads tasks from file and returns an ArrayList of Tasks objects (not done)
    public ArrayList<Task> load() throws Exception {
        new File("./data").mkdirs();
        File dataStore = new File(filepath);
        if (dataStore.exists()) {
            BufferedReader fileRead = new BufferedReader(new FileReader(dataStore));
            String st;
            while ((st = fileRead.readLine()) != null) {
                String[] strSplit = st.split(" \\| ");
                if (strSplit[0].equals("T")) {
                    Todo newTask = new Todo(strSplit[2]);
                    tasks.add(newTask);
                }
                else if (strSplit[0].equals("D")) {
                    Deadline newTask;
                    if (strSplit[3].length() > 10) {
                        String[] dlDateAndTime = strSplit[3].split(" ");
                        newTask = new Deadline(strSplit[2], dlDateAndTime[0], dlDateAndTime[1]);
                    } else {
                        newTask = new Deadline(strSplit[2], strSplit[3]);
                    }
                    tasks.add(newTask);
                } 
                else if (strSplit[0].equals("E")) {
                    String[] eventDateAndTime = strSplit[3].split(" ");
                    Event newTask = new Event(strSplit[2], eventDateAndTime[0], eventDateAndTime[1]);
                    tasks.add(newTask);
                }
                if (strSplit[1].equals("1")) {
                    tasks.get(tasks.size()-1).setStatus(1);
                }
            }
            fileRead.close();
        }
        return tasks;
    } 

    // Stores tasks into file
    public void store(ArrayList<Task> tasks) throws Exception {
        FileWriter myWriter = new FileWriter(filepath);
        int numTasks = tasks.size();
        for (int i = 0; i < numTasks; i++) {
            Task t = tasks.get(i);
            if (t instanceof Deadline || t instanceof Event) {
                myWriter.write(t.getTaskType() + " | " +  t.getBinaryStatus() + " | " + t.getTask() + " | " + t.getDue() + "\n");
            } else {
                myWriter.write(t.getTaskType() + " | " +  t.getBinaryStatus() + " | " + t.getTask() + "\n");
            }
        }
        myWriter.close();
    }
}
