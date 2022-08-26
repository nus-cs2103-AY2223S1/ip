import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private File file;
    private String path;

    public FileHandler(String path) {
        try {
            this.file = new File(path);
            this.path = path;
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> missions = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String text = sc.nextLine().strip();
                String[] textArr = text.split(" | ", 7);
                String command = textArr[0];
                switch (command) {
                    case "T":
                        Task todo = new Todo(textArr[4]);
                        markTasks(todo, textArr[2]);
                        missions.add(todo);
                        break;
                    case "D":
                        Task deadline = new Deadline(textArr[4], textArr[6]);
                        markTasks(deadline, textArr[2]);
                        missions.add(deadline);
                        break;
                    case "E":
                        Task event = new Event(textArr[4], textArr[6]);
                        markTasks(event, textArr[2]);
                        missions.add(event);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return missions;
    }

    public void appendToFile(String str) throws IOException {
        FileWriter fwriter = new FileWriter(this.path, true);
        fwriter.write(str);
        fwriter.close();
    }

    private void markTasks(Task t, String status) {
        if (Integer.parseInt(status) == 1) {
            t.isDone = true;
        }
    }

    public void rewriteFile(ArrayList<Task> missions) throws IOException {
        FileWriter fwriter = new FileWriter(this.path);
        for (Task t: missions) {
            fwriter.write(t.toFile() + "\n");
        }
        fwriter.close();
    }


}
