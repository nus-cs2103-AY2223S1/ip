import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;


public class DobbyIO {

    public static void save(DobbyList dl) throws IOException {
        try{
            File newFile = new File("./src/main/data/dobbyList.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(newFile.getAbsoluteFile()));
            bw.write(dl.toSave());
            bw.close();

        } catch(IOException e) {
            DobbyChat.noFileFound();
        }
    }
    public static void load() {

    }
}
