package jarvis;

import jarvis.task.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;
public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public List<Task> load() throws IOException {
        File myFile = new File(filePath);
        List<Task> taskList = new ArrayList<>();
        if (!myFile.createNewFile()) {
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String next = sc.nextLine();
                int divisor = next.lastIndexOf("|");

                if (next.charAt(0) == 'D') {
                    String description = next.substring(8, divisor - 1);
                    String date = next.substring((divisor + 2));
                    taskList.add(new Deadline(description, date));

                    if (next.charAt(4) == '1') {
                        taskList.get(taskList.size() - 1).mark();
                    }
                }
                if (next.charAt(0) == 'E') {
                    String description = next.substring(8, divisor - 1);
                    String date = next.substring((divisor + 2));
                    taskList.add(new Event(description, date));

                    if (next.charAt(4) == '1') {
                        taskList.get(taskList.size() - 1).mark();
                    }
                }
                if (next.charAt(0) == 'T') {
                    String description = next.substring(8);
                    taskList.add(new ToDo(description));

                    if (next.charAt(4) == '1') {
                        taskList.get(taskList.size() - 1).mark();
                    }
                }
            }
        }
        return taskList;
    }

    public void write(TaskList tasks) throws IOException {
        File myFile = new File(filePath);
        myFile.createNewFile();
        FileWriter myWriter = new FileWriter(myFile);
        for (int i = 0; i < tasks.getList().size(); i++ ) {
            Task curr = tasks.getList().get(i);
            if (curr instanceof Deadline) {
                if (curr.isDone) {
                    myWriter.write("D" + " | 1 | " + curr.description + " | " + ((Deadline) curr).by + "\n");
                } else {
                    myWriter.write("D" + " | 0 | " + curr.description + " | " + ((Deadline) curr).by + "\n");
                }
            } else if (curr instanceof Event) {
                if (curr.isDone) {
                    myWriter.write("E" + " | 1 | " + curr.description + " | " + ((Event) curr).at + "\n");
                } else {
                    myWriter.write("E" + " | 0 | " + curr.description + " | " + ((Event) curr).at + "\n");
                }
            } else {
                if (curr.isDone) {
                    myWriter.write("T" + " | 1 | " + curr.description + "\n");
                } else {
                    myWriter.write("T" + " | 0 | " + curr.description + "\n");
                }
            }
        }
        myWriter.close();
    }
}
