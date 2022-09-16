package duke;

import java.io.*;
import java.util.ArrayList;


public class Storage {

    /**
     * stores the tasklist into a file.
     *
     * @param array represents the list of tasks
     */
    public static void writeToFile(ArrayList<Task> array) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) != null) {
                try {
                    FileWriter fw = new FileWriter("./duke.txt", true);
                    fw.write(array.get(i).toString().charAt(1) + " " + array.get(i).description.trim() + " "
                            + array.get(i).getStatusIcon() + " " + array.get(i).date() + " " + array.get(i).timing()
                            + System.lineSeparator());
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * checks if the file exists or not.
     *
     * @return boolean value that tells us if the file exists or not
     */
    public static boolean fileExists() {
        File f = new File("./duke.txt");
        return f.exists();
    }

    /**
     * clears the file of all its contents.
     */
    public static void clearFile() {
        FileWriter fwOb = null;
        try {
            fwOb = new FileWriter("./duke.txt", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        try {
            fwOb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * loads the tasks in the file onto the array.
     * @throws  FileNotFoundException if the file has not been made yet.
     */
    public static void load() throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader("./duke.txt"));
        while (true) {
            try {
                String taskString = br.readLine();
                if (taskString == null) {
                    // EOF reached
                    br.close();
                    break;
                }

                String[] array = taskString.split(" ");
                if (array[0].equals("T") && array[2].equals("O")) {
                    Parser.listOfTasks.add(new Todo(array[1], false));
                } else if (array[0].equals("T") && array[2].equals("X")) {
                    Parser.listOfTasks.add(new Todo(array[1], true));
                } else if (array[0].equals("D") && array[2].equals("O")) {
                    Parser.listOfTasks.add(new Deadline(array[1], array[3], array[4], false));
                } else if (array[0].equals("D") && array[2].equals("X")) {
                    Parser.listOfTasks.add(new Deadline(array[1], array[3], array[4], true));
                } else if (array[0].equals("E") && array[2].equals("O")) {
                    Parser.listOfTasks.add(new Event(array[1], array[3], array[4], false));
                } else if (array[0].equals("E") && array[2].equals("X")) {
                    Parser.listOfTasks.add(new Event(array[1], array[3], array[4], true));
                }


                //Parser.listOfTasks
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
