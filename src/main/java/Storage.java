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
    public void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath,true);
        fw.write(textToAdd);
        fw.write(System.getProperty("line.separator"));
        fw.close();

    }

}
