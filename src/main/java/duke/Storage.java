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

    public ArrayList<String> load() throws FileNotFoundException {
        ArrayList<String> loadedData = new ArrayList<>();
        File file = new File(filePath);
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            loadedData.add(sc.nextLine());
        }

        sc.close();

        return loadedData;
    }

    public void save(ArrayList<String> data) {
        try {
            FileWriter file = new FileWriter(filePath);

            for (int i = 0; i < data.size(); i++) {
                file.write(String.format("%s%n", data.get(i)));
            }

            file.close();
        } catch (IOException e) {
            System.out.println("Error encountered : " + e.getMessage());
        }
    }
}

