import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TaskSaver {

    private static File createSaveDirectory() throws IOException {
        try {
            String path = ".." + File.separator + ".." + File.separator + ".."
                    + File.separator + "data";
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File saveFile = new File(path + File.separator + "duketasks.txt");
            saveFile.createNewFile();
            return saveFile;
        } catch (IOException e) {
            throw e;
        }
    }
    
    public static void saveToDirectory(ArrayList<Task> tasks) {
        try {
            File saveFile = createSaveDirectory(); 
            PrintWriter printWriter = new PrintWriter(saveFile);
            tasks.forEach(task -> printWriter.write(task.toString() + "\n"));
            printWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
