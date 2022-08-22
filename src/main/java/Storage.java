import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading data and saving data
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load data from the file path and stores the data in an ArrayList.
     *
     * @return ArrayList containing the tasks from the file
     */
    public ArrayList<Task> loadData() {
        ArrayList<Task> list = new ArrayList<>();

        File f = new File(this.filePath);
        try {
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            Scanner fileReader = new Scanner(f);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                String[] dataArr = data.split("\\s\\|\\s");
                Task task;
                if (dataArr[0].equals("T")) {
                    task = new Todo(dataArr[2]);
                } else if (dataArr[0].equals("D")) {
                    task = new Deadline(dataArr[2], dataArr[3]);
                } else {
                    task = new Event(dataArr[2], dataArr[3]);
                }
                if (dataArr[1].equals("1")) {
                    task.setAsDone();
                }
                list.add(task);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    /**
     * Save data from the to-do list into the file specified by filepath
     *
     * @param list ArrayList of Task to be saved
     */
    public void saveData(ArrayList<Task> list) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (Task t : list) {
                StringBuilder taskString = new StringBuilder();
                taskString.append(t.getTaskType() + " | ");
                if (t.getStatus()) {
                    taskString.append("1 | ");
                } else {
                    taskString.append("0 | ");
                }
                if (t instanceof Todo) {
                    taskString.append(((Todo) t).getDescription() + "\n");
                } else if (t instanceof Event) {
                    taskString.append(((Event) t).getDescription() + " | " + ((Event) t).getEventAt() + "\n");
                } else {
                    taskString.append(((Deadline) t).getDescription() + " | " + ((Deadline) t).getDeadlineBy() + "\n");
                }
                fw.write(taskString.toString());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
