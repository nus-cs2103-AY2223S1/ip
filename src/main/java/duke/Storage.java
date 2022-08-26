package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private final String FILEPATH;

    public Storage(String FILEPATH) {
        this.FILEPATH = FILEPATH;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(FILEPATH);
        f.getParentFile().mkdirs();
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch(IOException e) {
            System.out.println("Unable to read file.");
        }

        ArrayList<Task> taskList = new ArrayList<>();
        Scanner s = new Scanner(f);
        while(s.hasNext()) {
            String nextTask = s.nextLine();
            String[] temp = nextTask.split("\\[");
            Character taskType = temp[1].charAt(0);
            Boolean marked = temp[2].charAt(0) == 'X';
            String dateTemp = temp[temp.length - 1];
            dateTemp = dateTemp.substring(0, dateTemp.length() - 1);
            LocalDate date = LocalDate.now();
            if (!dateTemp.isEmpty()) {
                date = LocalDate.parse(dateTemp);
            }
            String task = "";
            for (int i = 3; i < temp.length - 1; ++i) {
                if (i < temp.length - 2) {
                    task += temp[i];
                    task += "[";
                } else {
                    task += temp[i].substring(0, temp[i].length() - 1);
                }
            }

            switch(taskType) {
                case 'T':
                    addTask(taskList, new ToDo(task), marked);
                    break;

                case 'D':
                    addTask(taskList, new Deadline(task, date), marked);
                    break;

                case 'E':
                    addTask(taskList, new Event(task, date), marked);
                    break;
            }
        }
        return taskList;
    }

    private void addTask(ArrayList<Task> taskList, Task t, boolean b) {
        t.setMarked(b);
        taskList.add(t);
    }

    public void rewriteFile(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH);
        fw.write("");
        for (int i = 0; i < taskList.size(); ++i) {
            try {
                appendTaskToFile(taskList.get(i));
            } catch (IOException e) {
                System.out.println("Unable to rewrite file.");
            }
        }
    }

    public void appendTaskToFile(Task t) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH, true);
        String type = t.getType();
        String marked = t.getMarked() ? "X" : " ";
        String description = t.getTask();
        LocalDate date = t.getDate();

        String dateString = date == null ? "" : date.toString();

        fw.write("[" + type + "][" + marked + "][" + description + "]["
                + dateString + "]" + System.lineSeparator());
        fw.close();
    }
}
