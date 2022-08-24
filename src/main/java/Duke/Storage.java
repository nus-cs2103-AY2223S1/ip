package Duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    String filePath;

    final static String PATH = "./data";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> load() {
        File directory = new File(PATH);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File file = new File(this.filePath);
        try {
            file.createNewFile();
            ArrayList<String> result = new ArrayList<>();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                result.add(data);
            }
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void writeFile(String str) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(str);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
