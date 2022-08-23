import models.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileOps {
    public void run() {
        try {
            File myObj = new File("saved.txt");
            if (myObj.createNewFile()) {
                System.out.println("file created" + myObj.getAbsolutePath());
            } else {
                //File already exists
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String text) {
        try {
            FileWriter myWriter = new FileWriter("saved.txt",true);
            myWriter.write(text);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void rewrite(List<Task> list) {
        try {
            FileWriter myWriter = new FileWriter("saved.txt",true);
            PrintWriter pw = new PrintWriter("saved.txt");
            for(int i = 0; i < list.size(); i++) {
                myWriter.write(list.get(i).toString());
            }
            pw.close();
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
