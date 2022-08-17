//only exceptions in example caught
//without automatic ui testing
//not using arraylist
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);
        Task[] taskList = new Task[100];
        int indCount = 0;
        String buffLine = "    _____________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("    Hello from\n" + logo);
        System.out.println("    What can I do for you?\n" + buffLine);
        String userReply = sc.nextLine();

        while (!userReply.equals("bye")) {
            try {
                if (userReply.startsWith("mark")) {
                    int pos = Character.getNumericValue(userReply.charAt(5));
                    taskList[pos - 1].markAsDone();
                    System.out.println(buffLine + "\n" + "    Nice! I've marked this task as done: ");
                    taskList[pos - 1].fullDesc();
                    System.out.println(buffLine);
                    userReply = sc.nextLine();
                } else if (userReply.startsWith("unmark")) {
                    int pos = Character.getNumericValue(userReply.charAt(7));
                    taskList[pos - 1].markAsUndone();
                    System.out.println(buffLine + "\n" + "    Ok, I've marked this task as not done yet: ");
                    taskList[pos - 1].fullDesc();
                    System.out.println(buffLine);
                    userReply = sc.nextLine();
                } else if (userReply.startsWith("todo")) {
                    try {
                        taskList[indCount] = new ToDos(userReply.substring(5, userReply.length()));
                        System.out.println(buffLine + "\n" + "    Got it. I've added this task: ");
                        taskList[indCount].fullDesc();
                        System.out.println("    Now you have " + String.valueOf(indCount + 1)
                                + " tasks in this list." + "\n" + buffLine);
                        indCount++;
                        userReply = sc.nextLine();
                    } catch (StringIndexOutOfBoundsException t) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                        userReply = sc.nextLine();
                    }
                } else if (userReply.startsWith("deadline")) {
                    try {
                        int splitPoint = userReply.indexOf("/");
                        taskList[indCount] = new Deadlines(userReply.substring(9, splitPoint - 1),
                                userReply.substring(splitPoint + 1, userReply.length()));
                        System.out.println("    Got it. I've added this task: ");
                        taskList[indCount].fullDesc();
                        System.out.println("    Now you have " + String.valueOf(indCount + 1)
                                + " tasks in this list." + "\n" + buffLine);
                        indCount++;
                        userReply = sc.nextLine();
                    } catch (StringIndexOutOfBoundsException t) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                        userReply = sc.nextLine();
                    }
                } else if (userReply.startsWith("event")) {
                    int splitPoint = userReply.indexOf("/");
                    taskList[indCount] = new Events(userReply.substring(6, splitPoint - 1),
                        userReply.substring(splitPoint + 1, userReply.length()));
                    System.out.println("    Got it. I've added this task: ");
                    taskList[indCount].fullDesc();
                    System.out.println("    Now you have " + String.valueOf(indCount + 1)
                            + " tasks in this list." + "\n" + buffLine);
                    indCount++;
                    userReply = sc.nextLine();
                } else if (userReply.equals("list")){
                    System.out.println(buffLine);
                    for (int i = 0; i <  indCount; i++) {
                        System.out.println("    " + (i + 1) + ". " + taskList[i].stringDesc());
                    }
                    System.out.println(buffLine);
                    userReply = sc.nextLine();
                } else if (userReply.startsWith("delete")) {
                    System.out.println(buffLine + "\n    Noted. I've removed this task: ");
                    taskList[Character.getNumericValue(userReply.charAt(7)) - 1].fullDesc();
                    for (int i = userReply.charAt(7) - 1; i < indCount; i++) {
                        taskList[i] = taskList[i + 1];
                    }
                    indCount--;
                    System.out.println("    Now you have " + String.valueOf(indCount) +
                            " tasks in this list." + "\n" + buffLine);
                    userReply = sc.nextLine();
                }
                else {
                    throw new DukeException("");
                }
            } catch (DukeException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                userReply = sc.nextLine();
            }
        }

        System.out.println(buffLine + "\n" + "    Bye. Hope to see you again soon!"
                + "\n" + buffLine);
    }
}
