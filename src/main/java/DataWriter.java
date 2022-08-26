import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class DataWriter {

    public static void writeData(ArrayList<Task> todoList, String storagePath) throws IOException {
        FileWriter fw = new FileWriter(storagePath);

        for (int i = 0; i < todoList.size(); i++) {
            fw.write(todoList.get(i).toFileData() + "\n");
        }

        fw.close();
    }
}
