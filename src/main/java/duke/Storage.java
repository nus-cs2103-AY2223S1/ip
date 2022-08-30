package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks saved on the hard disk.
     * If the file does not exist, creates the file.
     *
     * @return ArrayList containing the tasks.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            // Load data into tasks.
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] temp = line.split(" // ");
                Task task = new Task("Error loading this task!");
                switch (temp[0]) {
                case "T":
                    task = new Todo(temp[2]);
                    break;
                case "D":
                    task = new Deadline(temp[2], temp[3]);
                    break;
                case "E":
                    task = new Event(temp[2], temp[3]);
                    break;
                }
                if (temp[1].equals("1")) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // Create the empty file if there is no existing file.
            File file = new File(filePath);
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Updates the tasks stored on the hard disk.
     *
     * @param tasks New tasks to be updated.
     */
    public void update(ArrayList<Task> tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (int i = 0; i < tasks.size(); i++) {
                String str = tasks.get(i).toString();
                StringBuilder task = new StringBuilder();
                task.append(str.charAt(1) + " // ");
                if (str.charAt(4) == 'X') {
                    task.append("1 // ");
                } else {
                    task.append("0 // ");
                }

                String rest = str.substring(7);
                switch(str.charAt(1)) {
                case('T'):
                    task.append(rest);
                    break;
                case('D'):
                    rest = rest.replaceAll("[\\[\\](){}]", "");
                    String[] arrD = rest.split(" by: ");
                    task.append(arrD[0] + " // " + arrD[1]);
                    break;
                case('E'):
                    rest = rest.replaceAll("[\\[\\](){}]", "");
                    String[] arrE = rest.split(" at: ");
                    task.append(arrE[0] + " // " + arrE[1]);
                }
                task.append("\n");
                writer.write(task.toString());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
