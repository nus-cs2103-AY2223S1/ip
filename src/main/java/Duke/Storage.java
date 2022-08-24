package Duke;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    public List<String> readTasks(Scanner filescanner){
        List<String> lst = new ArrayList<String>(0);
        while(filescanner.hasNextLine()){
            String task = filescanner.nextLine();
            lst.add(task);
        }
        return lst;
    }

    public void addTasks(String pathName,List<String> lst ) throws IOException {
        for(int i=0;i<lst.size();i++) {
            writeToFile(pathName, lst.get(i));
        }
    }

    public void replaceTasks(String pathName, List<String> lst1, List<String> lst2) throws IOException {
        System.out.println("Code has been called to replace tasks");
        overwriteFile(pathName,lst1.get(0));
        for(int i=1;i<lst1.size();i++) {
            writeToFile(pathName, lst1.get(i));
        }
        for(int i=0;i<lst2.size();i++) {
            writeToFile(pathName, lst2.get(i));
        }

    }
    public void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath,true);
        fw.write(textToAdd);
        fw.write(System.getProperty("line.separator"));
        fw.close();

    }

    public void overwriteFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath,false);
        fw.write(textToAdd);
        fw.write(System.getProperty("line.separator"));
        fw.close();

    }

}
