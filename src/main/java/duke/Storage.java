package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Storage {
    private final File file;

    public Storage(String filePath) {
        System.out.println("Retrieving files...");
        File file = new File(filePath);
        this.file = file;
    }

    private static boolean isMarked(String toProcess) {
        return !toProcess.startsWith("[ ");
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> ls = new ArrayList<>();
        System.out.println("Retrieving files...");
        try {
            Scanner reader = new Scanner(this.file);
            while (reader.hasNextLine()) {
                String info = reader.nextLine();
                if (info.startsWith("  [T]")) {
                    String[] infoData = info.split("] ");
                    ToDo toAdd = new ToDo(infoData[infoData.length - 1]);
                    if (isMarked(infoData[infoData.length - 2])) {
                        toAdd.mark();
                    }
                    ls.add(toAdd);
                } else if (info.startsWith("  [D]")) {
                    String[] infoData = info.split("] ");
                    String[] descBy = infoData[infoData.length - 1].split("by: ");
                    Deadline toAdd = new Deadline(descBy[0].substring(0, descBy[0].length() - 2), descBy[1].substring(0, descBy[1].length() - 1));
                    if (isMarked(infoData[infoData.length - 2])) {
                        toAdd.mark();
                    }
                    ls.add(toAdd);
                } else if (info.startsWith("  [E]")) {
                    String[] infoData = info.split("] ");
                    String[] descAt = infoData[infoData.length - 1].split("at: ");
                    Event toAdd = new Event(descAt[0].substring(0, descAt[0].length() - 2), descAt[1].substring(0, descAt[1].length() - 1));
                    if (isMarked(infoData[infoData.length - 2])) {
                        toAdd.mark();
                    }
                    ls.add(toAdd);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No saved files. Starting a new task sheet!");
        } catch (DukeException e) {
            throw new DukeException("Previous file was corrupted. Creating new task sheet!");
        }
        System.out.println("Retrieval complete!");
        return ls;
    }

    public void save(TaskList tasks) throws IOException {
        try {
            File createFile = new File("Duke.txt");
            if (createFile.createNewFile()) {
                System.out.println("Creating Duke.txt...");
            } else {
                System.out.println("Updating Duke.txt...");
            }
            FileWriter writer = new FileWriter("Duke.txt");
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i) + "\n");
            }
            writer.close();
            System.out.println("duke.Duke.txt is fully updated.");
        } catch (IOException e) {
            System.out.println("An error occurred when trying to save your task list. Please try again.");
        }
    }

}
