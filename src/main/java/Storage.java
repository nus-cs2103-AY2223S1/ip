import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage Class represents the
 * storage for Duke.
 */
public class Storage {
    private ArrayList<Task> storage = new ArrayList<>();

    private void save() {
        try {
            FileWriter fw = new FileWriter(Duke.LOCALSTORAGE);
            for (Task t : storage) {
                fw.write(t.toStore());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to save file.");
        }
    }

    public void loadLocalStorage() throws FileNotFoundException {
        File localStorage = new File(Duke.LOCALSTORAGE);
        Scanner sc = new Scanner(localStorage);
        while (sc.hasNext()) {
            String[] strings = sc.nextLine().split(" \\| ");
            if (strings[0].equals("T")) {
                storage.add(new ToDo(Integer.parseInt(strings[1]),
                        strings[2]));
            } else if (strings[0].equals("E")) {
                storage.add(new Event(Integer.parseInt(strings[1]),
                        strings[2], strings[3]));
            } else if (strings[0].equals("D")) {
                storage.add(new Deadline(Integer.parseInt(strings[1]),
                        strings[2], strings[3]));
            }
        }
    }

    /**
     * Prints current tasks in the storage to user.
     */
    public void printStorage() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < storage.size(); i++) {
            Task currTask = this.storage.get(i);
            System.out.println((i + 1) + "." + currTask.toString());
        }
    }

    /**
     * Mark the specified task in the storage as done.
     * @param i Specified task.
     */
    public void markDone(int i) {
        storage.get(i - 1).markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(storage.get(i - 1).toString());
        save();
    }

    /**
     * Mark the specified task in the storage as not done.
     * @param i Specified task.
     */
    public void unmarkDone(int i) {
        storage.get(i - 1).unmarkDone();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(storage.get(i - 1).toString());
        save();
    }

    /**
     * Deletes the specified task in the storage.
     * @param i Specified task.
     */
    public void deleteTask(int i) {
        Task target = storage.get(i - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(target.toString());
        storage.remove(target);
        if (storage.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + storage.size() + " tasks in the list.");
        }
        save();
    }

    /**
     * Adds the Task into the storage.
     * @param t Specified Task.
     */
    public void addTask(Task t) {
        this.storage.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        if (storage.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + storage.size() + " tasks in the list.");
        }
        save();
    }
}
