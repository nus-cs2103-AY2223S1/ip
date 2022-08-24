package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File data = new File("src/main/data/");
    private File list = new File("src/main/data/list.txt");
    private ArrayList<String[]> infoList;

    public ArrayList<String[]> load() throws IOException {
        infoList = new ArrayList<>(100);

        //create a file to store the task list if it does not exist
        if (!list.exists()) {
            if (!data.exists()) {
                data.mkdir();
            }
            list.createNewFile();
            return infoList;
        }

        //read contents from file containing the task list
        Scanner listSc = new Scanner(list);
        while (listSc.hasNext()) {
            String[] info = listSc.nextLine().split(" \\| ");
            infoList.add(info);
        }
        return infoList;

    }

    public void save(String[] taskDescriptions) throws IOException {
        FileWriter fw = new FileWriter("src/main/data/list.txt");
        for (String task : taskDescriptions) {
            fw.write(task);
        }
        fw.close();
    }
}
