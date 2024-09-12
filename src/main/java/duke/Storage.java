package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Gets tasks from file, creates a new file if file is absent, and upload tasks in file.
 */
public class Storage {
    private String pathname;
    private ArrayList<Task> listStored;
    private String directory;

    /**
     * Creates a Storage object.
     * @param pathname Pathname to the file to be stored.
     * @param listStored
     * @param directory Directory of the file to be stored.
     */
    Storage(String pathname, ArrayList<Task> listStored, String directory) {
        this.pathname = pathname;
        this.listStored = listStored;
        this.directory = directory;
    }

    /**
     * Gets data from the given path of the file and store the tasks in this.listStored.
     */
    public void getData() {
        try {
            File f = new File(this.pathname);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String string = s.nextLine();
                String typeOfTask = string.substring(1, 2);
                boolean gotMarkSymbol = string.substring(4, 5).equals("X");

                if (typeOfTask.equals("T")) {
                    if (gotMarkSymbol) {
                        listStored.add(new ToDo(string.substring(7), true));
                    } else {
                        listStored.add(new ToDo(string.substring(6), false));
                    }
                } else {
                    int openBracketIndex = string.indexOf("(");
                    int closeBracketIndex = string.indexOf(")");
                    String nameOfMarkedTaskForDeadlineOrEventTask = string.substring(7, openBracketIndex - 1);
                    String nameOfUnmarkedTaskForDeadlineOrEventTask = string.substring(6, openBracketIndex - 1);
                    String atOrByInfo = string.substring(openBracketIndex + 5, closeBracketIndex);

                    if (typeOfTask.equals("D")) {
                        if (gotMarkSymbol) {
                            listStored.add(new Deadline(nameOfMarkedTaskForDeadlineOrEventTask,
                                    true, atOrByInfo));
                        } else {
                            listStored.add(new Deadline(nameOfUnmarkedTaskForDeadlineOrEventTask,
                                    false, atOrByInfo));
                        }
                    } else if (typeOfTask.equals("E")) {
                        if (gotMarkSymbol) {
                            listStored.add(new Event(nameOfMarkedTaskForDeadlineOrEventTask,
                                    true, atOrByInfo));
                        } else {
                            listStored.add(new Event(nameOfUnmarkedTaskForDeadlineOrEventTask,
                                    false, atOrByInfo));
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            createFile();
        }
    }

    /**
     * Creates file if file is not found when getting data from the file with the given path.
     */
    public void createFile() {
        try {
            File f = new File(this.pathname);
            File dir = new File(this.directory);
            dir.mkdir();
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Goes through the this.listStored to update the updated list of tasks into the file when exiting program.
     * @throws IOException
     */
    public void updateFile() throws IOException {
        File f = new File(this.pathname);
        FileWriter fw = new FileWriter(f);
        String newContent = "";
        for (int i = 0; i < listStored.size(); i++) {
            newContent = newContent + listStored.get(i) + "\n";
        }
        fw.write(newContent);
        fw.close();
    }
}

