import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

/**
 *  Duke Class
 *  The main class
 *
 * @author Kang Qiao
 */

public class Duke {

    private static ArrayList<Task> inputs = new ArrayList<Task>();



    private static String answer(String msg) {
        return "_______________________________________________________" +
                "\n" + msg + "\n" +
                "_______________________________________________________";
    }
//add tasks
    private static String addition(String msg) {
        return "_______________________________________________________" +
                "\n" + "Nice, I have added  this task to your list:\n " + msg + "\n" +
                "Great, now you have " + inputs.size() + " tasks in the list.\n" +
                "_______________________________________________________";
    }
//to list down all the tasks
    private static String all() {
        String userInputs = "";
        for (int i = 0; i < inputs.size(); i++)
        {
            int index = i + 1;
            Task tempTask = inputs.get(i);
            userInputs += "\n" + index + "."
                    + tempTask.toString() + "\n";

        }

        return userInputs;
    }
//set this task as done
    private static String markDone(String str) {
        return "_______________________________________________________" + "\n" +
                "Nice! I've marked this task as done:" +
                "\n" + str + "\n" +
                "\n" + "_______________________________________________________";
    }

    private static String markUndone(String str) {
        return "_______________________________________________________" + "\n" +
                "Wow! I've marked this task as not done yet:" +
                "\n" + str + "\n" +
                "\n" + "_______________________________________________________";
    }

    public static void main(String[] args) throws DukeException {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  /\n"
                + "|____/ \\,_|_|\\_\\___|\n";

        System.out.println("Hello I'm\n" + logo + "What can I do for you?\n");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();





        while(!str.equals("bye"))
        {
            //for listing
            if (str.equals("list"))
            {
                System.out.println("Here are all the tasks in your list\n");
                System.out.println("_______________________________________________________" +
                        "\n" + all() + "\n" +
                        "_______________________________________________________");
            }
            else
            {
                String instruction = str.split(" ")[0];
                if (instruction.equals("mark"))
                {
                    //this is as index is greater than array index by 1
                    int index = Integer.valueOf(str.split(" ")[1]) - 1;
                    inputs.get(index).setDone();
                    System.out.println(markDone(inputs.get(index).toString()));
                }
                else if ((instruction.equals("unmark")))
                {
                    int index = Integer.valueOf(str.split(" ")[1]) - 1;
                    inputs.get(index).setUndone();
                    System.out.println(markUndone(inputs.get(index).toString()));
                }
                else if(instruction.equals("todo"))
                {
                  try {
                      str = str.split(" ", 2)[1];
                      ToDo tasktoDo = new ToDo(str);
                      inputs.add(tasktoDo);
                      System.out.println(addition(tasktoDo.toString()));

                  } catch (ArrayIndexOutOfBoundsException e) {
                          throw new DukeException(("OOPS!!! The description of a ToDo cannot be empty."));
                }

                }
                else if(instruction.equals("deadline"))
                {
                  try {
                      //splits away deadline
                      str = str.split(" ", 2)[1];
                      String desc = str.split("/by")[0];
                      String by = str.split("/by")[1];
                      Deadline taskDeadline = new Deadline(desc, by);
                      inputs.add(taskDeadline);
                      System.out.println(addition(taskDeadline.toString()));
                  } catch (ArrayIndexOutOfBoundsException e) {
                      throw new DukeException(("OOPS!!! The description of a Deadline cannot be empty."));
                  }

                }
                else if(instruction.equals("event"))
                {
                  try {
                      //splits away event
                      str = str.split(" ", 2)[1];
                      String desc = str.split("/at")[0];
                      String at = str.split("/at")[1];
                      Event taskEvent = new Event(desc, at);
                      inputs.add(taskEvent);
                      System.out.println(addition(taskEvent.toString()));
                  } catch (ArrayIndexOutOfBoundsException e) {
                      throw new DukeException(("OOPS!!! The description of an Event cannot be empty."));
                  }

                }
                else
                {
                    throw new DukeException("I have no idea what you are saying, this is not a task >_<");
                }
            }
            str = sc.nextLine();
        }
        System.out.println("_______________________________________________________" +
                "\n" + "Bye. Hope to see you again soon!");
    }
}