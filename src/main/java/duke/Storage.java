package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File toRead;
    private String path;

    public Storage (String path) {
        this.path = path;
        toRead = new File(path);

        if (!toRead.exists()) {
            try {
                File directory = toRead.getParentFile();
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                toRead.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Scanner fileReader = new Scanner(toRead);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] info = line.split(",");
                String taskType = info[0];
                Task toAdd;
                switch (taskType) {
                case "T":
                    toAdd = new Todo(info[2]);
                    if (Integer.parseInt(info[1]) == 1) {
                        toAdd.complete();
                    }
                    tasks.add(toAdd);
                    break;
                case "D":
                    toAdd = new Deadline(info[2]);
                    String[] dateArr = info[3].split(" ");
                    toAdd.setDate(LocalDate.parse(dateArr[0]));
                    if(dateArr.length > 1) {
                        toAdd.setTime(dateArr[1]);
                    }
                    if (Integer.parseInt(info[1]) == 1) {
                        toAdd.complete();
                    }
                    tasks.add(toAdd);
                    break;
                case "E":
                    toAdd = new Event(info[2]);
                    String[] dateArrEvent = info[3].split(" ");
                    toAdd.setDate(LocalDate.parse(dateArrEvent[0]));
                    if(dateArrEvent.length > 1) {
                        toAdd.setTime(dateArrEvent[1]);
                    }
                    if (Integer.parseInt(info[1]) == 1) {
                        toAdd.complete();
                    }
                    tasks.add(toAdd);
                    break;
                default:

                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("Oops file cannot be found!");
        } finally {
            return tasks;
        }
    }

    public void write(TaskList tasks) {
        try {
            FileWriter toLoad = new FileWriter(path);
            for (Task t: tasks.getTasks()) {
                String taskInfo = t.getTaskType() + ",";

                if (t.isCompleted()) {
                    taskInfo += "1,";
                } else {
                    taskInfo += "0,";
                }
                taskInfo += t.getTaskName() + ",";

                //change here too
                if (t.getTaskType().equals("D") || t.getTaskType().equals("E")) {
                    taskInfo += t.getDateFormat();
                }

                toLoad.write(taskInfo + "\n");
            }

            toLoad.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }


}

