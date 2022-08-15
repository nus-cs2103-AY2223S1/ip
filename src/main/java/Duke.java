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
          String[] splitStr = str.split(" ");
          Task taskToMark = getTaskToMark(splitStr,list);
          Task taskToAdd = getTaskToAdd(str);

          if (taskToMark != null) {
            String action = splitStr[0];
            if (action.equals("mark")){
              taskToMark.mark();
              System.out.println("Nice! I've marked this task as done:\n  " + taskToMark);
            } else {
              taskToMark.unmark();
              System.out.println("OK, I've marked this task as not done yet:\n  " + taskToMark);
            }
          } else if (taskToAdd != null){
            list.add(taskToAdd);
            System.out.println("Got it. I've added this task:\n  " + taskToAdd + "\nNow you have "
                               + list.size() + " tasks in the list.");
          } else {
            System.out.println("idk");
          }
        }
      }
    }

    public static Task getTaskToMark (String[] splitStr, List<Task> list) {
      try {
        if(splitStr.length != 2) return null;
        int index = Integer.parseInt(splitStr[1]);
        String action = splitStr[0];
        if (index > 0
            && index <= list.size()
            && (action.equals("mark") || action.equals("unmark"))){
          return list.get(index-1);
        }
        return null;
      } catch (NumberFormatException e){
        return null;
      }
    }

    public static Task getTaskToAdd(String str) {
      String[] splitStr = str.split(" ");
      String type = splitStr[0];
      if (type.equals("todo")) {
        String description = str.substring(type.length() + 1);
        return new ToDo(description);
      } else if (type.equals("deadline")) {
        String description = str.substring(type.length() + 1,str.indexOf("/by") - 1);
        String date = str.substring(str.lastIndexOf("/by") + 4);
        return new Deadline(description,date);
      } else if (type.equals("event")) {
        String description = str.substring(type.length() + 1,str.indexOf("/at") - 1);
        String date = str.substring(str.lastIndexOf("/at") + 4);
        return new Event(description,date);
      } else {
        return null;
      }
    }
}
