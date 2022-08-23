package Main;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Stream;
import Tasks.Task;
import Tasks.TaskList;

public class Storage {

    private File storageFile;

    public Storage(String filePath) {
        this.storageFile = new File(filePath);
    }

    public ArrayList<Task> load(Ui ui) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(storageFile));
            Stream<String> content = reader.lines();

            content.forEach(s -> handleString(s, tasks));

            ui.setLoaded();
        } catch (FileNotFoundException e1) {
            storageFile.getParentFile().mkdirs();
            storageFile.createNewFile();

            FileWriter writer = new FileWriter(storageFile);
            writer.write("  Luna finds the following items saved in your list 🍃");
            writer.close();
        } finally {
            return tasks;
        }
    }

    public void handleString(String s, ArrayList<Task> tasks) {
        Task tsk = Parser.parseSaved(s);
        if (tsk != null) {
            tasks.add(tsk);
        }
    }

    public void updateStorage(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(storageFile);
            String content = "  Luna finds the following items saved in your list 🍃";
            content = content.concat(tasks.stored());

            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.out.println("⚡️Luna has encountered an error while updating tasks⚡️" +
                                "\n️Please exit and try again ️⛈");
        }
    }

}
