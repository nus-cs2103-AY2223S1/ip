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
        try{
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
            Task taskToDelete = getTaskToDelete(splitStr,list);

            if (taskToMark != null) {
              String action = splitStr[0];
              if (action.equals("mark")) {
                taskToMark.mark();
                System.out.println("Nice! I've marked this task as done:\n  " + taskToMark);
              } else {
                taskToMark.unmark();
                System.out.println("OK, I've marked this task as not done yet:\n  " + taskToMark);
              }
            } else if (taskToAdd != null) {
              list.add(taskToAdd);
              System.out.println("Got it. I've added this task:\n  " + taskToAdd + "\nNow you have "
                                  + list.size() + " tasks in the list.");
            } else if (taskToDelete != null) {
              list.remove(taskToDelete);
              System.out.println("Noted. I've removed this task:\n  " + taskToDelete + "\nNow you have "
                                  + list.size() + " tasks in the list");
            } else {
              throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            }
        }
        } catch (DukeException de) {
          System.out.println(de);
        }
      }
    }

  private static Task getTaskToDelete(String[] splitStr, List<Task> list) throws DukeException {
    try {
      if (splitStr.length != 2) return null;
      int index = Integer.parseInt(splitStr[1]);
      String action = splitStr[0];
      boolean validAction = action.equals("delete");
      boolean validIndex = index > 0 && index <= list.size();
      if (validIndex && validAction) {
        return list.get(index-1);
      } else if (validAction && !validIndex) {
        throw new DukeException("☹ OOPS!!! Please enter a valid task number to delete");
      }
      return null;
    } catch (NumberFormatException e) {
      return null;
    }
  }
    private static Task getTaskToMark(String[] splitStr, List<Task> list) throws DukeException {
      try {
        if (splitStr.length != 2) return null;
        int index = Integer.parseInt(splitStr[1]);
        String action = splitStr[0];
        boolean validAction = action.equals("mark") || action.equals("unmark");
        boolean validIndex = index > 0 && index <= list.size();
        if (validIndex && validAction) {
          return list.get(index-1);
        } else if (validAction && !validIndex) {
          throw new DukeException("☹ OOPS!!! Please enter a valid task number to mark/unmark");
        }
        return null;
      } catch (NumberFormatException e) {
        return null;
      }
    }

    private static Task getTaskToAdd(String str) throws DukeException {
      String[] splitStr = str.split(" ");
      String type = splitStr[0];
        if (type.equals("todo")) {
          if (splitStr.length < 2)throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.\n");
          String description = str.substring(type.length() + 1);
          return new ToDo(description);
        } else if (type.equals("deadline")) {
          if (splitStr.length < 2)throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.\n");
          if (str.indexOf("/by") - 1 < 0) throw new DukeException("☹ OOPS!!! Please set date of deadline with /by.\n");
          String description = str.substring(type.length() + 1,str.indexOf("/by") - 1);
          String date = str.substring(str.indexOf("/by") + 4);
          return new Deadline(description,date);
        } else if (type.equals("event")) {
          if (splitStr.length < 2)throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.\n");
          if (str.indexOf("/at") - 1 < 0) throw new DukeException("☹ OOPS!!! Please set date of event with /at.\n");
          String description = str.substring(type.length() + 1,str.indexOf("/at") - 1);
          String date = str.substring(str.indexOf("/at") + 4);
          return new Event(description,date);
        } else {
          return null;
        }
    }
}
