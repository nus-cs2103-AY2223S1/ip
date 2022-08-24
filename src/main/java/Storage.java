import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String pathname;
    private ArrayList<Task> listStored;
    //here
    private static String DIRECTORY = "./DATA";

    Storage(String pathname, ArrayList<Task> listStored){
        this.pathname = pathname;
        this.listStored = listStored;
    }

   public void getData() {
       try {
           File f = new File(this.pathname);
           Scanner s = new Scanner(f);
           while (s.hasNext()) {
               String string = s.nextLine();
               int openBracketIndex = string.indexOf("(");
               int closeBracketIndex = string.indexOf(")");
               if (string.substring(1,2).equals("D")){
                   if(string.substring(4,5).equals("X")){
                       listStored.add(new Deadlines(string.substring(7, openBracketIndex -1),
                               true, string.substring(openBracketIndex + 5, closeBracketIndex)));
                   } else {
                       listStored.add(new Deadlines(string.substring(6, openBracketIndex - 1),
                               false, string.substring(openBracketIndex + 5,closeBracketIndex)));
                   }
               } else if (string.substring(1,2).equals("E")) {
                   if(string.substring(4,5).equals("X")){
                       listStored.add(new Events(string.substring(7, openBracketIndex -1),
                               true, string.substring(openBracketIndex + 5,closeBracketIndex)));
                   } else {
                       listStored.add(new Events(string.substring(6, openBracketIndex - 1),
                               false, string.substring(openBracketIndex + 5,closeBracketIndex)));
                   }
               } else {
                  if(string.substring(4,5).equals("X")){
                      listStored.add(new ToDos(string.substring(7),true));
                   } else {
                       listStored.add(new ToDos(string.substring(6), false));
                   }
               }
           }
       } catch (FileNotFoundException e ){
           createFile();
       }
   }

    public void createFile() {
        try {
            File f = new File(this.pathname);
            File dir = new File(DIRECTORY);
            dir.mkdir();
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}