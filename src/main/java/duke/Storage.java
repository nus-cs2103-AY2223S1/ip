package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String pathname;
    private ArrayList<Task> listStored;
    private String directory;

    Storage(String pathname, ArrayList<Task> listStored, String directory) {
        this.pathname = pathname;
        this.listStored = listStored;
        this.directory = directory;
    }

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

