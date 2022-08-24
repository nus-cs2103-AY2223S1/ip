import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class TaskList {
    ArrayList<Task> list;
    private static String DIRECTORY = "./DATA";
    private static String FILENAME = "duke.txt";
    private static String PATHNAME = String.valueOf(Paths.get(DIRECTORY, FILENAME));


    TaskList(ArrayList list) {
        this.list = list;
    }

    void showList(){
        Ui ui = new Ui();
        ui.displayListUi(this.list);
    }

    public void writeToFile(String status) throws IOException {
        FileWriter fw = new FileWriter(PATHNAME, true);
        fw.write(status + "\n");
        fw.close();
    }

    void addToList(Task task) {
        // check...
        try {
            writeToFile(task.toString());
            this.list.add(task);
            Ui ui = new Ui();
            ui.addToListUi(task, this.list);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

     void delete(String s) throws IOException {
        int i = Integer.parseInt(s.substring(7)) - 1;
        Task task = this.list.remove(i);
         File file = new File(PATHNAME);
         Scanner scanner = new Scanner(file);
         String oldLine = "";
         String content = "";
         while (scanner.hasNext()) {
             String string = scanner.nextLine();
             if (string.equals(task.toString())) {
                 oldLine = string;
             } else {
                 content = content + string + "\n";
             }

         }
         FileWriter writer = new FileWriter(file);
         writer.write(content);
         writer.close();
        Ui ui = new Ui();
        ui.deleteUi(task, this.list);
    }

}
