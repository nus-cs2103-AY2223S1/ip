
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

public class Duke {

    private static void writeToFile(String filePath, ArrayList<Task> Tasks) throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            throw new IOException("File does not exist");
        }
        FileWriter fw = new FileWriter(filePath);

      
        String textToAdd = "";
        for (Task item : Tasks) {
            if (item != null)
               textToAdd += Tasks.indexOf(item) + 1 + "." + item.toString() + "\n";
        }
        fw.write(textToAdd);
        fw.close();
    }

    public static void main(String[] args) throws Exception {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you ?");
        Scanner help = new Scanner(System.in);
        String filepath  = "./././data/duke.txt";


        ArrayList<Task> lst = new ArrayList<>();
        for(int j = 0;j<100;j++) {
            lst.add(null);
        }
        int i = 0;


        while(true) {
           String str = help.nextLine();
            String first = str.split(" ")[0];

           if (first.equals("bye")) {
               System.out.println("Bye see you again buddy !");
               break;
           }

           else if (first.equals("list")) {
               System.out.println("This is your tasks in your list: \n");
               for (Task item : lst) {
                   if (item != null)
                       System.out.println(lst.indexOf(item) + 1 + "." + item);
               }
           }

           else if (first.equals("mark") ) {
               Task current = lst.get(Integer.parseInt(str.split(" ")[1]) - 1);
               System.out.println("Nice I have marked this as done:");
               current.mark();
               System.out.println( " " + current);
               writeToFile(filepath,lst);

           }
           else if (first.equals("unmark") ) {
               Task current = lst.get(Integer.parseInt(str.split(" ")[1]) - 1);
               System.out.println("Ok I have marked this as still to be done:");
               current.unmark();
               System.out.println(" " + current);
               writeToFile(filepath,lst);


           }
           else if (first.equals("delete")) {
              Task current = lst.get(Integer.parseInt(str.split(" ")[1]) - 1);
              lst.remove(current);
               i--;
               System.out.println("Noted I have removed this task");
               System.out.println(current);
               System.out.println("Now you have" + " " + i + " " + "tasks in list");
               writeToFile(filepath,lst);




           }
           else {
               if (first.equals("deadline")) {
                   if (str.endsWith("deadline")) {
                       throw new DukeException("Oops The description of deadline cannot be empty !");
                   }
                   System.out.println("Got it.I've added this task");
                   String currD = str.substring(str.indexOf("deadline") + 8, str.indexOf("/by"));
                   lst.set(i,new Deadline(currD, str.substring(str.indexOf("/by") + 3)));
                   System.out.println(lst.get(i));
                   i++;
                   System.out.println("Now you have" + " " + i + " " + "tasks in list");
                   writeToFile(filepath,lst);

               } else if (first.equals("event")) {
                   if (str.endsWith("event")) {
                       throw new DukeException("Oops The description of event cannot be empty !");
                   }
                   System.out.println("Got it.I've added this task");
                   String currE = str.substring(str.indexOf("event") + 5, str.indexOf("/at"));
                   lst.set(i, new Events(currE, str.substring(str.indexOf("/at") + 3)));
                   System.out.println(lst.get(i));
                   i++;
                   System.out.println("Now you have" + " " + i + " " + "tasks in list");
                   writeToFile(filepath,lst);

               }
               else if (first.equals("todo")) {
                   if (str.endsWith("todo")) {
                       throw new DukeException("Oops The description of todo cannot be empty !");
                   }
                   System.out.println("Got it.I've added this task");
                   String currT = str.substring(str.indexOf("todo") + 4);
                   lst.set(i, new ToDos(currT));
                   System.out.println(lst.get(i));
                   i++;
                   System.out.println("Now you have" + " " + i + " " + "tasks in list");
                   writeToFile(filepath,lst);
               } else {
                 throw new DukeException("Oops Sorry I dont know what you are talking about :( ");

               }
           }

       }


    }
}
