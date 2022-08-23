import java.util.ArrayList;
import java.util.Scanner;

import java.time.LocalDate;

public class Duke {
    public static void main(String[] args) {
        startChat();
    }

    /**
    * Print to system out string encapsulated within horizontal lines.
    *
    * @param str String to be printed
    */
    public static void formatPrint(String str) {
        String horizontalLine = "______________________________________\n";
        System.out.println(horizontalLine + str + "\n" + horizontalLine);
    }

    public static void startChat() {
        Storage storage = new Storage("src/data/tasks.txt");
        ArrayList<Task> list = storage.loadData();

        String horizontalLine = "______________________________________\n";

        String firstText = "hi... I'm Karen\nWhat do you want this time?";
        formatPrint(firstText);

        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String inputText = sc.nextLine();
                Parser parser = new Parser(inputText);
                String firstWord = parser.getFirstText();
                //terminate chat
                if (firstWord.equals("bye")) {
                    formatPrint("K finally, good riddance!");
                    storage.saveData(list);
                    break;
                    //view list of items
                } else if (firstWord.equals("list")) {
                    if (list.size() == 0) {
                        formatPrint("pff there is nothing in your list");
                    } else {
                        System.out.println(horizontalLine + "Here are your dumb tasks in your list:");
                        for (int i = 1; i < list.size() + 1; i++) {
                            Task item = list.get(i - 1);
                            System.out.println(i + ". " + item.toString());
                        }
                        System.out.println(horizontalLine);
                    }
                    //mark an item as done
                } else if (firstWord.equals("mark")) {
                    int markValue = parser.getTaskNumber();
                    if (markValue > list.size() || markValue <= 0) {
                        throw new DukeException("The mark value does not exist dummy!");
                    }
                    Task item = list.get(markValue - 1);
                    item.setAsDone();
                    String str = "Took you long enough to complete this task:\n" + item.toString();
                    formatPrint(str);
                    //mark an item as not done
                } else if (firstWord.equals("unmark")) {
                    int markValue = parser.getTaskNumber();
                    if (markValue > list.size() || markValue <= 0) {
                        throw new DukeException("The unmark value does not exist dummy!");
                    }
                    Task item = list.get(markValue - 1);
                    item.setAsUndone();
                    String str = "Another task marked as not done?? Slow indeed\n" + item.toString();
                    formatPrint(str);
                    //add a to-do item
                } else if (firstWord.equals("todo")) {
                    String desc = parser.getTodoDescription();
                    Todo t = new Todo(desc);
                    list.add(t);
                    String str = "Fine, I'll add this task:\n\t" + t + "\nNow you have " + list.size() + " tasks in the list...";
                    formatPrint(str);
                    // add a deadline item
                } else if (firstWord.equals("deadline")) {
                    String desc = parser.getDeadlineDescription();
                    LocalDate byDate = parser.getDeadlineDate();
                    Deadline d = new Deadline(desc, byDate);
                    list.add(d);
                    String str = "Fine, I'll add this task:\n\t" + d + "\nNow you have " + list.size() + " tasks in the list...";
                    formatPrint(str);
                    //add an event item
                } else if (firstWord.equals("event")) {
                    String desc = parser.getEventDescription();
                    LocalDate atDate = parser.getEventDate();
                    Event e = new Event(desc, atDate);
                    list.add(e);
                    String str = "Fine, I'll add this task:\n\t" + e + "\nNow you have " + list.size() + " tasks in the list...";
                    formatPrint(str);
                    //delete a task from the list
                } else if (firstWord.equals("delete")) {
                    int delValue = parser.getTaskNumber();
                    if (delValue > list.size() || delValue <= 0) {
                        throw new DukeException("The delete value does not exist dummy!");
                    }
                    Task item = list.get(delValue - 1);
                    list.remove(delValue - 1);
                    String str = "Ughh I'll remove this task:\n\t" + item.toString() + "\nNow you have " + list.size() + " tasks in the list...";
                    formatPrint(str);
                } else {
                    formatPrint("What are you saying??? Try again");
                }
            } catch (DukeException e) {
                formatPrint(e.getMessage());
            }
        }


    }

}
