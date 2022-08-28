package duke;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

/**
 * Storage is used to handle data saved.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs Storage.
     * @param filePath filepath containing input from previous session.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the list containing input from previous session.
     * @return List containing input from previous session.
     * @throws FileNotFoundException If file does not exist.
     */
    public ArrayList<String> load() throws FileNotFoundException {
        ArrayList<String> list = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String in = s.nextLine();
            list.add(in);
        }
        s.close();
        System.out.println("I have added tasks from your previous session to the current list");
        if (list.isEmpty()) {
            throw new FileNotFoundException();
        }
        return list;
    }

    /**
     * Saves the input for next session.
     * @param list List of input to be saved for next session.
     */
    public void save(ArrayList<String> list) {
        try {
            new File(filePath).getParentFile().mkdirs();
            FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/data/duke.txt");

            for (String tasks : list) {
                fw.write(String.format("%s%n", tasks));
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Oops: " + e.getMessage());
        }
    }

}
