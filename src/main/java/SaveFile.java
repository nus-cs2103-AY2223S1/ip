import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class SaveFile {
    File save;
    String FILE_PATH = "data/duke.txt";

    public SaveFile() {
        // Make save file if it does not exist in data directory.
        new File("data").mkdir();
        File saveFile = new File(FILE_PATH);

        try {
            saveFile.createNewFile();
        }
        catch(IOException e) {
            e.getStackTrace();
            System.out.println(
                    "____________________________________________________________ \n"
                            + "☹ OOPS!!! Unable to find/create save file. \n"
                            + "____________________________________________________________");
        }

        this.save = saveFile;
    }

    public void initialise(ArrayList<Task> storage) {
        try {
            Scanner s = new Scanner(save);
            while (s.hasNext()) {
                String task = s.nextLine();
                String[] temp = task.split(" : ");
                Task newTask;

                if (temp[0].equals("T")) {
                    newTask = new Todo(temp[2]);

                } else if (temp[0].equals("D")) {
                    newTask = new Deadline(temp[2], temp[3]);

                } else if (temp[0].equals("E")) {
                    newTask = new Event(temp[2], temp[3]);

                } else {
                    throw new DukeException("");
                }

                if (temp[1].equals("1")) {
                    newTask.markDone();
                }

                storage.add(newTask);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DukeException e) {
            e.printStackTrace();
            System.out.println(
                    "____________________________________________________________ \n"
                            + "☹ OOPS!!! Unknown element in save file. \n"
                            + "____________________________________________________________");
        }

    }

    public void addTask(String taskToAdd) {
        try {
            PrintWriter fw = new PrintWriter(new FileWriter(FILE_PATH, true)); // create a FileWriter in append mode
            fw.println(taskToAdd);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(
                    "____________________________________________________________ \n"
                            + "☹ OOPS!!! Unable to add task. \n"
                            + "____________________________________________________________");
        }
    }

    public void reload(ArrayList<Task> storage) {
        try {
            PrintWriter writer = new PrintWriter(FILE_PATH);
            writer.print("");
            writer.close();

            Iterator<Task> iter = storage.iterator();
            while (iter.hasNext()) {
                this.addTask(iter.next().toStore());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void reset(ArrayList<Task> storage) {
        storage.clear();

        try {
            PrintWriter writer = new PrintWriter(FILE_PATH);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
