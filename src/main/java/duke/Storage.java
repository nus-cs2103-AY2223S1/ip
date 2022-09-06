package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Storage {

    /**
     * Reads data from storage and stores in taskList.
     * @param taskList taskList to store data read from storage
     * @throws IOException input errors
     */
    public static void read(TaskList taskList) throws IOException {

        File directory = new File("data");
        File file = new File("data/duke.txt");
        if (directory.exists()) {
            if (file.exists()) {
                Scanner sc = new Scanner(file);
                while (sc.hasNext()) {
                    String taskStr = sc.nextLine();
                    char type = taskStr.charAt(1);
                    char done = taskStr.charAt(4);
                    String task = taskStr.substring(7);
                    if (type == 'T') {
                        ToDos todo = new ToDos(task);
                        taskList.add(todo);
                    } else if (type == 'D') {
                        String[] taskNameBy = task.split(" \\(by: ");
                        String taskName = taskNameBy[0];
                        String by = taskNameBy[1].substring(0, taskNameBy[1].length() - 1);
                        try {
                            Deadlines deadline = new Deadlines(taskName, by);
                            if (done == 'X') {
                                deadline.markAsDone();
                            }
                            taskList.add(deadline);
                        } catch (DateTimeParseException e){
                            System.out.println("OOPS!!! Please enter date in YYYY-MM-DD format");
                        }
                    } else if (type == 'E') {
                        String[] taskNameAt = task.split(" \\(at: ");
                        String taskName = taskNameAt[0];
                        String at = taskNameAt[1].substring(0, taskNameAt[1].length() - 1);
                        Events event = new Events(taskName, at);
                        if (done == 'X') {
                            event.markAsDone();
                        }
                        taskList.add(event);
                    }
                }
            } else {
                file.createNewFile();
            }
        } else {
            directory.mkdir();
            file.createNewFile();
        }

    }

    /**
     * Stores data from taskList into storage.
     * @param taskList taskList to be stored into storage.
     */
    public static void write(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            for (int i = 0; i < taskList.size(); i++) {
                Task t = taskList.get(i);
                fw.write(t.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
