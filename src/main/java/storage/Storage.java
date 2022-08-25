package storage;

import tasks.*;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storage {

    private static final TaskList TASK_LIST = new TaskList();
    private static final Pattern TASK_PATTERN = Pattern.compile("^([0-9]).\\[(T|D|E)\\]\\[(✓|✘)\\] (.[^\\(]*)(?: (.*: (.*?)))?\\)?$");

    public void save() {
        try {
            FileWriter myWriter = new FileWriter("tasklist.txt");
            myWriter.write(TASK_LIST.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public TaskList load() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("tasklist.txt"));
            String line = in.readLine();
            while (line != null) {
                Matcher matcher = TASK_PATTERN.matcher(line);
                matcher.find();
                String type = matcher.group(2);
                String done = matcher.group(3);
                String desc = matcher.group(4);
                String time = matcher.group(6);
                handle(type, done, desc, time);
                line = in.readLine();
            }
        } catch (FileNotFoundException e) {
            save();
        } catch (IllegalStateException e) {
            System.out.println("Tasklist is invalid. Writing new file...");
            save();
        } catch (IOException e) {
            System.out.println("Tasklist cannot be read. Writing new file...");
            save();
        }
        return TASK_LIST;
    }

    private void handle(String type, String done, String desc, String time) {
        switch (type) {
            case "T":
                ToDo todo = new ToDo(desc);
                if (done.equals("\u2713")) {
                    todo.markAsDone();
                }
                TASK_LIST.addTask(todo);
                break;
            case "D":
                Deadline deadline = new Deadline(desc, time);
                if (done.equals("\u2713")) {
                    deadline.markAsDone();
                }
                TASK_LIST.addTask(deadline);
                break;
            case "E":
                Event event = new Event(desc, time);
                if (done.equals("\u2713")) {
                    event.markAsDone();
                }
                TASK_LIST.addTask(event);
                break;
        }
    }
}
