import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        startChat();
    }

    /*
    * Print to system out string encapsulated within horizontal lines.
    *
    * @param str String to be printed
    */
    public static void formatPrint(String str) {
        String horizontalLine = "______________________________________\n";
        System.out.println(horizontalLine + str + "\n" + horizontalLine);
    }

    public static void startChat() {
        String horizontalLine = "______________________________________\n";

        String firstText = "hi... I'm Karen\nWhat do you want this time?";
        formatPrint(firstText);

        ArrayList<Task> list = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String echoText = sc.nextLine();
                //terminate chat
                if (echoText.equals("bye")) {
                    formatPrint("K finally, good riddance!");
                    break;
                    //view list of items
                } else if (echoText.equals("list")) {
                    if (list.size() == 0) {
                        formatPrint("pff there is nothing in your list");
                    } else {
                        System.out.println(horizontalLine + "\nHere are your dumb tasks in your list:");
                        for (int i = 1; i < list.size() + 1; i++) {
                            Task item = list.get(i - 1);
                            System.out.println(i + ". " + item.toString());
                        }
                    }
                    System.out.println(horizontalLine);
                    //mark an item as done
                } else if (echoText.split(" ")[0].equals("mark")) {
                    int markValue = Integer.parseInt(echoText.split(" ")[1]);
                    if (markValue > list.size() || markValue <= 0) {
                        throw new DukeException("The mark value does not exist dummy!");
                    }
                    Task item = list.get(markValue - 1);
                    item.markAs(true);
                    String str = "Took you long enough to complete this task:\n" + item.toString();
                    formatPrint(str);
                    //mark an item as not done
                } else if (echoText.split(" ")[0].equals("unmark")) {
                    int markValue = Integer.parseInt(echoText.split(" ")[1]);
                    if (markValue > list.size() || markValue <= 0) {
                        throw new DukeException("The unmark value does not exist dummy!");
                    }
                    Task item = list.get(markValue - 1);
                    item.markAs(false);
                    String str = "Another task marked as not done?? Slow indeed\n" + item.toString();
                    formatPrint(str);
                    //add a to-do item
                } else if (echoText.split(" ")[0].equals("todo")) {
                    int firstSpaceIndex = echoText.indexOf(" ");
                    if (firstSpaceIndex == -1) {
                        throw new DukeException("Description of a todo cannot be empty dummy!");
                    }
                    String text = echoText.substring(firstSpaceIndex + 1);
                    if (text.trim().equals("")) {
                        throw new DukeException("Description of a todo cannot be empty dummy!");
                    }
                    Todo t = new Todo(text);
                    list.add(t);
                    String str = "Fine, I'll add this task:\n\t" + t + "\nNow you have " + list.size() + " tasks in the list...";
                    formatPrint(str);
                    // add a deadline item
                } else if (echoText.split(" ")[0].equals("deadline")) {
                    int firstSpaceIndex = echoText.indexOf(" ");
                    if (firstSpaceIndex == -1) {
                        throw new DukeException("Description of a deadline cannot be empty dummy!");
                    }
                    int byIndex = echoText.indexOf("/by");
                    if (byIndex == -1) {
                        throw new DukeException("A deadline must have a by clause dummy!");
                    }
                    String desc = echoText.substring(firstSpaceIndex + 1, byIndex);
                    //4 because 3 of "/by" and 1 for the additional space
                    String by = echoText.substring(byIndex + 4);
                    Deadline d = new Deadline(desc, by);
                    list.add(d);
                    String str = "Fine, I'll add this task:\n\t" + d + "\nNow you have " + list.size() + " tasks in the list...";
                    formatPrint(str);
                    //add an event item
                } else if (echoText.split(" ")[0].equals("event")) {
                    int firstSpaceIndex = echoText.indexOf(" ");
                    if (firstSpaceIndex == -1) {
                        throw new DukeException("Description of an event cannot be empty dummy!");
                    }
                    int atIndex = echoText.indexOf("/at");
                    if (atIndex == -1) {
                        throw new DukeException("An event must have a at clause dummy!");
                    }
                    String desc = echoText.substring(firstSpaceIndex + 1, atIndex);
                    String at = echoText.substring(atIndex + 4);
                    Event e = new Event(desc, at);
                    list.add(e);
                    String str = "Fine, I'll add this task:\n\t" + e + "\nNow you have " + list.size() + " tasks in the list...";
                    formatPrint(str);
                    //delete a task from the list
                } else if (echoText.split(" ")[0].equals("delete")) {
                    int delValue = Integer.parseInt(echoText.split(" ")[1]);
                    if (delValue > list.size() || delValue <= 0) {
                        throw new DukeException("The delete value does not exist dummy!");
                    }
                    Task item = list.get(delValue - 1);
                    list.remove(delValue - 1);
                    String str = "Ughh I'll remove this task:\n\t" + item.toString() + "\nNow you have " + list.size() + " tasks in the list...";
                    formatPrint(str);
//                    System.out.println(horizontalLine + "Ughh I'll remove this task:\n\t" + item.toString());
//                    System.out.println("Now you have " + list.size() + " tasks in the list...\n" + horizontalLine );
                } else {
                    formatPrint("What are you saying??? Try again");
                }
            } catch (DukeException e) {
                formatPrint(e.getMessage());
            }
        }


    }

}
