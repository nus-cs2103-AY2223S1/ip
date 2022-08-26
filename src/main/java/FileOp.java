import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOp {

    private String path;

    public FileOp(String path){
        this.path = path;
    }

    public ArrayList<Task> createFile(ArrayList<Task> li) throws IOException {
        File f = new File(this.path);
        boolean isCreated = f.createNewFile();
        if(!isCreated){
            Scanner sc = new Scanner(f);
            while(sc.hasNext()) {
                String s = sc.nextLine();
                if(s.startsWith("[T]")){
                    String k = s.substring(6);
                    Task t = new Todo(k);
                    li.add(t);
                    if(s.substring(4, 5).equals("X")){
                        t.markAsDone();
                    }
                }
                else if(s.startsWith("[D]")){
                    String k = s.substring(6, s.indexOf(" (by"));
                    String h = s.substring(s.indexOf("y")+4, s.indexOf(')'));
                    Task t = new Deadline(k, h);
                    li.add(t);
                    if(s.substring(4, 5).equals("X")){
                        t.markAsDone();
                    }
                }
                else if(s.startsWith("[E]")){
                    String k = s.substring(6, s.indexOf(" (at"));
                    String h = s.substring(s.indexOf("t")+4, s.indexOf(')'));
                    Task t = new Event(k, h);
                    li.add(t);
                    if(s.substring(4, 5).equals("X")){
                        t.markAsDone();
                    }
                }
            }
        }
        return li;
    }

    public static void writeToFile(Task t) throws IOException {
        FileWriter w = new FileWriter("./Bro.txt", true);
        w.write(t.toString() + "\n");
        w.close();
    }

    public static void modifyTaskFile(ArrayList<Task> li) throws IOException {
        FileWriter w = new FileWriter("./Bro.txt", false);
        for(Task t : li){
            w.write(t.toString() + "\n");
        }
        w.close();
    }
}
