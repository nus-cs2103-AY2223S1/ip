import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
    }

    public ArrayList<String> read() throws FileNotFoundException{
        ArrayList<String> loadedData = new ArrayList<>();
        File file = new File(filePath);
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            loadedData.add(sc.nextLine());
        }

        sc.close();

        return loadedData;
    }


}

