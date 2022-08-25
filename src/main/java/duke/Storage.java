package duke;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Storage {

    private File saveFile;

    public Storage() {
        try {
            this.saveFile = createSaveDirectory();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getPresentWorkingDirectory() {
        return new File("").getAbsolutePath();
    }

    public File createSaveDirectory() throws IOException {
        try {
            String path = this.getPresentWorkingDirectory() +
                    File.separator + "data";
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
    
    public void saveToDirectory(ArrayList<Task> tasks) {
        try {
            PrintWriter printWriter = new PrintWriter(saveFile);
            tasks.forEach(task -> printWriter.write(task.toString() + "\n"));
            printWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
