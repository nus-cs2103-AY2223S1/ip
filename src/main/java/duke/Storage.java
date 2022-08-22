package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(ArrayList<String> dataArrayList) {
        try {
            new File(filePath).getParentFile().mkdirs();
            FileWriter fw = new FileWriter(filePath);
            for (String data : dataArrayList) {
                fw.write(String.format("%s%n", data));
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("\n\tOOPS!!! I'm sorry, but I'm unable to write to file due to: " + e.getMessage());
        }
    }

    public ArrayList<String> load() throws FileNotFoundException {
        ArrayList<String> data = new ArrayList<>();
        File f = new File(filePath);
        Scanner sc = new Scanner(f);

        while (sc.hasNextLine()) {
            data.add(sc.nextLine());
        }
        sc.close();

        return data;
    }
}
