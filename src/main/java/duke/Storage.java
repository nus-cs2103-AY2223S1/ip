package duke;

import java.util.Scanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Handles storage, writing and editing of output text file.
 *
 * @author Yuvaraj Kumaresan
 */
public class Storage {

    /**
     * Handles adding and modifying the task list.
     */
    protected static TaskList taskList;

    /**
     * Constructor.
     *
     * @param list The tasklist used to update the list in storage.
     */
    public Storage(TaskList list) {
        taskList = list;
    }

    /**
     * Reads tasks from text file duke.txt.
     */
    public static void readFromFile() {
        try {
            File myObj = new File("duke.txt");
            if (myObj.createNewFile()) {
            } else {
                FileInputStream saved = new FileInputStream("duke.txt");
                Scanner scan = new Scanner(saved);
                while (scan.hasNextLine()) {
                    String next = scan.nextLine();
                    if (next.startsWith("duke.ToDo")) {
                        String[] temp = next.split(" / ");
                        ToDo item = new ToDo(temp[2]);
                        item.setIsDone(temp[1].equalsIgnoreCase("true"));
                        taskList.arrayList.add(item);
                    } else if (next.startsWith("duke.Deadline")) {
                        String[] temp = next.split(" / ");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm");
                        Deadline item = new Deadline(temp[2], LocalDateTime.parse(temp[3], formatter));
                        item.setIsDone(temp[1].equalsIgnoreCase("true"));
                        taskList.arrayList.add(item);
                    } else if (next.startsWith("duke.Event")) {
                        String[] temp = next.split(" / ");
                        Event item = new Event(temp[2], temp[3]);
                        item.setIsDone(temp[1].equalsIgnoreCase("true"));
                        taskList.arrayList.add(item);
                    }
                }
                scan.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Write tasks to text file duke.txt.
     */
    public static void writeToFile() throws IOException {
        FileWriter dukeWriter = new FileWriter("duke.txt");
        for (int i = 0; i < taskList.arrayList.size(); i++) {
            if (taskList.arrayList.get(i).getClass() == ToDo.class) {
                dukeWriter.write("duke.ToDo / " + taskList.arrayList.get(i).getIsDone()
                        + " / " + taskList.arrayList.get(i).getDescription() + "\n");
            }
            if (taskList.arrayList.get(i).getClass() == Event.class) {
                dukeWriter.write("duke.Event / " + ((Event) taskList.arrayList.get(i)).getIsDone()
                        + " / " + ((Event) taskList.arrayList.get(i)).getDescription() + " / "
                        + ((Event) taskList.arrayList.get(i)).getAt() + "\n");
            }
            if (taskList.arrayList.get(i).getClass() == Deadline.class) {
                dukeWriter.write("duke.Deadline / " + ((Deadline) taskList.arrayList.get(i)).getIsDone()
                        + " / " + ((Deadline) taskList.arrayList.get(i)).getDescription() + " / "
                        + ((Deadline) taskList.arrayList.get(i)).getBy().format(DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm")) + "\n");
            }
        }
        dukeWriter.close();
    }
}
