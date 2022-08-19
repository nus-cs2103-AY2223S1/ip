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
        ArrayList<String> dataList = new ArrayList<>();
        File f = new File(filePath);
        Scanner sc = new Scanner(f);

        while (sc.hasNext()) {
            dataList.add(sc.nextLine());
        }
        sc.close();

        return dataList;
    }

    public void save(ArrayList<String> dataList) {
        try {
            new File(filePath).getParentFile().mkdirs();
            FileWriter fw = new FileWriter(filePath);

            for (String data : dataList) {
                fw.write(String.format("%s%n", data));
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
