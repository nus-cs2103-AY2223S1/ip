import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String line = "_______________________________________";

    private File directory;
    private File tasklist;
    private FileWriter fileWriter;
    private PrintWriter printWriter;

    public Storage() { // creates new files if needed
        try {
            directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }

            tasklist = new File("data/tasklist.txt");
            if (!tasklist.exists()) {
                tasklist.createNewFile();
            }
            fileWriter = new FileWriter(this.tasklist, true);
            printWriter = new PrintWriter(fileWriter);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public boolean readfile(TasksManager tasksManager) {
        try {
            Scanner sc = new Scanner(this.tasklist);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                tasksManager.addTaskNoPrint(Decoder.parseFromFile(data));
            }
            sc.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("error reading file");
            return false;
        }
    }

    public boolean rewriteFile(ArrayList<Task> tasks) {
        //delete all file contents
        printWriter.flush();
        try {
            printWriter = new PrintWriter(new FileWriter(this.tasklist, false));
            printWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        printWriter = new PrintWriter(fileWriter);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            printWriter.println(task.fileForm());
        }
        return true;
    }

    public void end() {
        this.printWriter.close();
    }
}
