package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * A storage handler that loads/saves tasks to the file.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads tasks from storage and returns them in an ArrayList.
     *
     * @return An ArrayList of Tasks.
     * @throws FileNotFoundException If the storage file cannot be found.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(filePath);
        f.getParentFile().mkdirs();
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Unable to read file.");
        }

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String savedTask = s.nextLine();
            // Extract properties of the saved task
            HashMap<String, Object> props = getProperties(savedTask);
            Character type = (Character) props.get("type");
            Boolean isMarked = (Boolean) props.get("isMarked");
            LocalDate date = (LocalDate) props.get("date");
            String description = (String) props.get("description");
            int period = (int) props.get("period");

            switch(type) {
            case 'T':
                addTask(tasks, new ToDo(description), isMarked);
                break;

            case 'D':
                addTask(tasks, new Deadline(description, date, period), isMarked);
                break;

            case 'E':
                addTask(tasks, new Event(description, date), isMarked);
                break;

            default:
                break;
            }
        }
        return tasks;
    }

    private HashMap<String, Object> getProperties(String savedTask) {
        HashMap<String, Object> ret = new HashMap<>();
        String[] temp = savedTask.split("\\[");
        ret.put("type", temp[1].charAt(0));
        ret.put("isMarked", temp[2].charAt(0) == 'X');

        String dateTemp = temp[temp.length - 2];
        dateTemp = dateTemp.substring(0, dateTemp.length() - 1);
        LocalDate date = LocalDate.now();
        if (!dateTemp.isEmpty()) {
            date = LocalDate.parse(dateTemp);
        }
        ret.put("date", date);

        int period = temp[temp.length - 1].charAt(0) - '0';
        ret.put("period", period);

        String description = "";
        for (int i = 3; i < temp.length - 2; ++i) {
            if (i < temp.length - 3) {
                description += temp[i];
                description += "[";
            } else {
                description += temp[i].substring(0, temp[i].length() - 1);
            }
        }
        ret.put("description", description);
        return ret;
    }

    private void addTask(ArrayList<Task> tasks, Task t, boolean b) {
        t.setMarked(b);
        tasks.add(t);
    }

    /**
     * Rewrites the tasks in storage to match the given task list.
     *
     * @param tasks The given task list.
     * @throws IOException If an error occurs in writing to storage.
     */
    public void rewriteFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        for (int i = 0; i < tasks.size(); ++i) {
            try {
                appendTaskToFile(tasks.get(i));
            } catch (IOException e) {
                System.out.println("Unable to rewrite file.");
            }
        }
    }

    /**
     * Appends the given task to storage.
     *
     * @param t The task to append.
     * @throws IOException If an error occurs in writing to storage.
     */
    public void appendTaskToFile(Task t) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        String type = t.getType();
        String marked = t.getMarked() ? "X" : " ";
        String description = t.getDescription();
        LocalDate date = t.getDate();
        int period = t.getPeriod();

        String dateString = date == null ? "" : date.toString();

        fw.write("[" + type + "][" + marked + "][" + description + "]["
                + dateString + "][" + period + "]" + System.lineSeparator());
        fw.close();
    }
}
