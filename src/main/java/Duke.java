import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    static ArrayList<Task> listOfTask = new ArrayList<>();

     static void showList(){
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= listOfTask.size(); i++) {
            System.out.println(i + "." + listOfTask.get(i - 1).toString());
        }
    }

    static void addList(Task task) {
        System.out.println("Got it. I've added this task:");
        listOfTask.add(task);
        System.out.println(task.toString());
        System.out.println("Now you have " + listOfTask.size() + " tasks in the list.");
    }

    static void markHelper(String s) {
        int i = Integer.parseInt(s.substring(5, 6)) - 1;
        Task task =  listOfTask.get(i);
        task.mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    static void unmarkHelper(String s) {
        int i = Integer.parseInt(s.substring(7, 8)) - 1;
        Task task = listOfTask.get(i);
        task.unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    static void delete(String s) {
         int i = Integer.parseInt(s.substring(7,8)) - 1;
         Task task = listOfTask.remove(i);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + listOfTask.size() + " tasks in the list.");
    }


    public static void main(String[] args) {
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            String s = input.nextLine();
            int index = s.indexOf("/");
            if (s.equals("bye")) {
                break;
            } else if (s.equals("list")) {
                showList();
            } else if (s.length() > 5 && s.substring(0, 4).equals("mark")) {
                markHelper(s);
            } else if (s.length() > 7 && s.substring(0, 6).equals("unmark")) {
                unmarkHelper(s);
            } else if (s.length() > 8 && s.substring(0,8).equals("deadline")) {
                Task t = new Deadlines(s.substring(9, index - 1),s.substring(index + 1));
                addList(t);
            } else if (s.length() > 5 && s.substring(0,5).equals("event")){
                Task t = new Events(s.substring(6,index - 1),s.substring(index + 1));
                addList(t);
            } else if (s.length() > 4 && s.substring(0,4).equals("todo")){
                Task t = new ToDos(s.substring(5));
                addList(t);
            } else if (s.length() > 7 && s.substring(0,6).equals("delete")) {
                delete(s)   ;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}


