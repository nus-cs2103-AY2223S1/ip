import java.util.*;
import java.util.ArrayList;

public class Bro {

    ArrayList<Task> list1 = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello! I'm THE BRO\n" + "What can I do for you?");
        Bro d = new Bro();
        Scanner sc= new Scanner(System.in);
        d.output1(sc);

    }
    public void output1(Scanner sc){
        String str= sc.nextLine();

        if(str.equals("bye")){
            System.out.println("Bye. See you later broo!");
        }

        else if(str.equals("list")){
            int count = 1;
            for(Task x: list1){
                System.out.println(count + "." + x.toString());
                count++;
            }
            this.output1(sc);
        }

        else if(str.startsWith("mark ")){
            int k = Integer.parseInt(str.substring(5));
            list1.get(k - 1).markAsDone();
            System.out.println("I have marked this task\n" + (list1.get(k-1)).toString());
            this.output1(sc);
        }

        else if(str.startsWith("unmark ")){
            int k = Integer.parseInt(str.substring(7));
            list1.get(k - 1).markAsNotDone();
            System.out.println("I have unmarked this task\n" + (list1.get(k-1)).toString());
            this.output1(sc);
        }

        else if(str.startsWith("todo ")){
            Task t = new Todo(str.substring(5));
            t.markAsNotDone();
            list1.add(t);
            System.out.println(t.toString());
            this.output1(sc);
        }

        else if(str.startsWith("deadline ")){
            Task t = new Deadline(str.substring(9, str.indexOf("by")-1), str.substring(str.indexOf("by") + 2));
            t.markAsNotDone();
            list1.add(t);
            System.out.println(t.toString());
            this.output1(sc);
        }

        else if(str.startsWith("event ")){
            Task t = new Event(str.substring(6, str.indexOf("at")-1), str.substring(str.indexOf("at") + 2));
            t.markAsNotDone();
            list1.add(t);
            System.out.println(t.toString());
            this.output1(sc);
        }

        else{
            System.out.println("invalid stmt");
            this.output1(sc);
        }
    }
}
