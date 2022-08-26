package duke.utils;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Duke utility function to handle both saving and reading of the task list into local drive.
 */
public class Storage {

    /**
     * Saves the current task list of the user into the duke/duke.txt file in the project root
     * folder.
     *
     * @param tasks Task list of the user.
     */
    public void save(List<Task> tasks) {
        try {
            //duke.txt will always exist since we have already created it upon loading up the duke program
            //however, users may delete the directory and hence we still need to check for the existence

            //reference from https://stackoverflow.com/a/28620461
            String savePath =
                System.getProperty("user.dir") + System.getProperty("file.separator") + "duke";
            File saveLocation = new File(savePath);
            if (!saveLocation.exists()) {
                saveLocation.mkdir();
                File myFile = new File(savePath, "duke.txt");
                PrintWriter textFileWriter = new PrintWriter(new FileWriter(myFile));
                for (int i = 0; i < tasks.size(); i++) {
                    textFileWriter.write(tasks.get(i).toString() + "\n");
                }
                textFileWriter.close();
            } else {
                File myFile = new File(savePath, "duke.txt");
                PrintWriter textFileWriter = new PrintWriter(new FileWriter(myFile));
                for (int i = 0; i < tasks.size(); i++) {
                    textFileWriter.write(tasks.get(i).toString() + "\n");
                }
                textFileWriter.close();
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    /**
     * Reads the duke/duke.txt file in the project root folder and generates the task list
     * accordingly.
     *
     * @return The task list for the Duke program.
     */
    public List<Task> read() {
        ArrayList<Task> tasks = new ArrayList<>();
        String path =
            System.getProperty("user.dir") + System.getProperty("file.separator") + "duke";
        File saveLocation = new File(path);
        if (!saveLocation.exists()) {
            saveLocation.mkdir();
            File myFile = new File(path, "duke.txt");
        } else {
            try {
                BufferedReader reader = new BufferedReader(
                    new FileReader(new File(path, "duke.txt")));
                String line;
                try {
                    //reference from https://stackoverflow.com/a/16104650
                    while ((line = reader.readLine()) != null) {
                        parseLine(line, tasks);
                    }
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Error: " + e);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e);
                System.out.println(
                    "Don't worry I'll create the file when you create your task list!");
            }
        }
        return tasks;
    }

    private void parseLine(String txt, ArrayList<Task> list) {
        char eventType = txt.charAt(1);
        char done = txt.charAt(4);
        boolean isDone = done == 'X';
        //regex reference from https://stackoverflow.com/a/17779833
        String[] event = txt.substring(7).split("\\(");
        switch (eventType) {
        case 'T':
            list.add(new ToDo(event[0]));
            break;
        case 'D':
            LocalDateTime time = LocalDateTime.parse(event[1].substring(0, event[1].indexOf(")")),
                DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));
            list.add(new Deadline(event[0], time));
            break;
        case 'E':
            time = LocalDateTime.parse(event[1].substring(0, event[1].indexOf(")")),
                DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));
            list.add(new Event(event[0], time));
            break;
        default:
            break;
        }
        list.get(list.size() - 1).setDone(isDone);
    }
}
