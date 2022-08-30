import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private ArrayList<Task> list = new ArrayList<>();
    private String path;

    public Storage(String path){
        this.path = path;
    }

    public ArrayList<Task> load() throws BroException {
        File f = new File(this.path);
        boolean isCreated = false;
        try {
            isCreated = f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!isCreated){
            Scanner sc = null;
            try {
                sc = new Scanner(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while(sc.hasNext()) {
                String s = sc.nextLine();
                if(s.startsWith("[T]")){
                    Task t = new Todo(s.substring(6).trim());
                    list.add(t);
                    if(s.substring(4, 5).equals("X")){
                        t.markAsDone();
                    }
                }
                else if(s.startsWith("[D]")){
                    String k = s.substring(6, s.indexOf(" (by")).trim();
                    String h = s.split("by:")[1].replace(')', ' ').trim();
                    Task t = new Deadline(k, h);
                    list.add(t);
                    if(s.substring(4, 5).equals("X")){
                        t.markAsDone();
                    }
                }
                else if(s.startsWith("[E]")){
                    String k = s.substring(6, s.indexOf(" (at")).trim();
                    String h = s.split("at:")[1].replace(')', ' ').trim();
                    Task t = new Event(k, h);
                    list.add(t);
                    if(s.substring(4, 5).equals("X")){
                        t.markAsDone();
                    }
                }
            }
        }
        return list;
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

