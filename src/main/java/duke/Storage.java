import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> load() throws FileNotFoundException {
        ArrayList<String> dukeList = new ArrayList<>();
        File file = new File(filePath);
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            dukeList.add(sc.nextLine());
        }

        sc.close();

        return dukeList;
    }

    public void save(ArrayList<String> dukeList) { // throws DukeIOException
        try {
            new File(filePath).getParentFile().mkdirs();
            FileWriter writeFile = new FileWriter(filePath);

            int len = dukeList.size();

            for (int i = 0; i < len; i++) {
                writeFile.write(String.format("%s%n", dukeList.get(i)));
            }

            writeFile.close();
        } catch (IOException e) {
            // Change to DukeIOException
            System.out.println("An error has occurred in the file: " + e.getMessage());
        }
    }
}
