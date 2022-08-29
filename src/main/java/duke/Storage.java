package duke;

import task.DukeTask;
import task.DukeTaskDeadline;
import task.DukeTaskEvent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A class that writes and read from files
 */
public class Storage {

    private static String FILEPATH;
    private static ArrayList<DukeTask> tasklist;
    
    Storage() {}

    /**
     * Set FILEPATH and tasklist
     * @param lst
     * @param filePath
     */
    public static void setOnce(ArrayList<DukeTask> lst, String filePath) {
        tasklist = lst;
        FILEPATH = filePath;
    }

    /**
     * Read list from available file
     */
    public void read() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILEPATH));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] temp = line.split("/");
                if (temp.length == 3) {
                    tasklist.add(new DukeTask(temp[2], temp[1].contains("X"), temp[0].charAt(0)));
                } else {
                    // OOP will be made better later on
                    if (temp[0].contains("D")){
                        LocalDateTime ldt1 = LocalDateTime.parse(temp[3].substring(1, temp[3].length() - 1));
                        tasklist.add(new DukeTaskDeadline(temp[2], temp[1].contains("X"), temp[0].charAt(0), ldt1));
                    } else if (temp[0].contains("E")) {
                        tasklist.add(new DukeTaskEvent(temp[2], temp[1].contains("X"), temp[0].charAt(0), temp[3]));
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    /**
     * Save current list items into file
     */
    public void save() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILEPATH));
            
            for (int i = 0; i < tasklist.size(); i ++) {
                DukeTask t = tasklist.get(i);
                if (t.getTaskType() == 'D') {
                    DukeTaskDeadline tD = (DukeTaskDeadline) t;
                    writer.write(tD.toStringSaveFile() +"\n");
                } else if (t.getTaskType() == 'E') {
                    DukeTaskEvent tE = (DukeTaskEvent) t;
                    writer.write(tE.toStringSaveFile() +"\n");
                } else {
                    writer.write(t.toStringSaveFile() + "\n");
                }
                
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

    }


    
}

