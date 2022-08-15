import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      List<Task> list = new ArrayList<>();

      System.out.print("Hello I'm Duke\nWhat can I do for you?\n");
      while (true){
        String str = sc.nextLine();
        if(str.equals("bye")){
          System.out.println("Bye. Hope to see you again soon!");
          break;
        } else if (str.equals("list")) {
          System.out.println("Here are the tasks in your list:");
          for (int i = 1; i <= list.size(); i++){
            System.out.println(i + "." + list.get(i-1));
          }
        } else {
          if (canMarkTask(str, list.size())) {
            markTask(str,list);
          } else {
            list.add(new Task(str));
            System.out.println("added: " + str);
          }
        }
      }
    }

    public static boolean canMarkTask(String str, int numOfTasks) {
      try {
        String[] markTask = str.split(" ");
        if(markTask.length != 2) return false;
        int num = Integer.parseInt(markTask[1]);
        String action = markTask[0];
        return num > 0
               && num <= numOfTasks
               && (action.equals("mark") || action.equals("unmark"));
      } catch (NumberFormatException e){
        return false;
      }
    }
    public static void markTask(String str,List<Task> list) {
      String[] markTask = str.split(" ");
      int index = Integer.parseInt(markTask[1]);
      String action = markTask[0];
      if (action.equals("mark")) {
        list.get(index - 1).mark();
      } else {
        list.get(index - 1).unmark();
      }
    }

}
