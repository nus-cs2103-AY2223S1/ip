import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static String SEPARATING_LINE = "    ____________________________________________________________";

    private static void FormatPrint(String s) {
        System.out.println(SEPARATING_LINE);
        System.out.println(s);
        System.out.println(SEPARATING_LINE);
    }

    private static void ListPrint(ArrayList<Task> arr) {
        int count = 1;
        String result = "Here are the tasks in your list:\n";
        for (Task t : arr) {
            if (count != 1) {
                result += "\n";
            }
            result += "    " + String.valueOf(count) + ". " + t.toString();
            count++;
        }
        Duke.FormatPrint(result);
    }

    private static void TaskStateChangePrint(Task t, boolean b) {
        String res;
        if (b) {
            res = "Nice! I've marked this task as done:\n";
        } else {
            res = "OK, I've marked this task as not done yet:\n";
        }
        Duke.FormatPrint(res + t.toString());
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String OPENING = "    Hello! I'm Duke\n    What can I do for you?";
        String LIST_WORD = "list";
        ArrayList<Task> stored_items = new ArrayList<>();
        String END_WORD = "bye";
        String ENDING = "    Bye. Hope to see you again soon!";

        // opening
        Duke.FormatPrint(OPENING);

        // respond to the input
        Scanner sc = new Scanner(System.in);

        while (true) {
            String str = sc.nextLine();
            str = str.replace("\n", "").replace("/r", "");

            // System.out.print("Input String:\n" + str + "\n");

            if (str.equals(END_WORD)) {
                Duke.FormatPrint(ENDING);
                break;
            } else {
                if (str.equals(LIST_WORD)) {
                    Duke.ListPrint(stored_items);
                } else {
                    String temp[] = str.split(" ", 2);
                    int index;
                    Task curr;
                    String pack[];
                    try {
                        switch (temp[0]) {
                            case "mark":
                                index = Integer.parseInt(temp[1]) - 1;
                                curr = stored_items.get(index);
                                if (!curr.isDone) {
                                    curr.isDone = true;
                                    Duke.TaskStateChangePrint(curr, true);
                                }
                                break;

                            case "unmark":
                                index = Integer.parseInt(temp[1]) - 1;
                                curr = stored_items.get(index);
                                if (curr.isDone) {
                                    curr.isDone = false;
                                    Duke.TaskStateChangePrint(curr, false);
                                }
                                break;

                            case "deadline":
                                pack = temp[1].split("/", 2);
                                curr = new Deadline(pack[0].trim(), pack[1].substring(3));
                                stored_items.add(curr);
                                Duke.FormatPrint("Got it. I've added this task:\n" + curr.toString());
                                break;

                            case "todo":
                                curr = new Todo(temp[1]);
                                stored_items.add(curr);
                                Duke.FormatPrint("Got it. I've added this task:\n" + curr.toString());
                                break;

                            case "event":
                                pack = temp[1].split("/", 2);
                                curr = new Event(pack[0].trim(), pack[1].substring(3));
                                stored_items.add(curr);
                                Duke.FormatPrint("Got it. I've added this task:\n" + curr.toString());
                                break;

                            default:
                                Duke.FormatPrint(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                    } catch (Exception e) {
                        Duke.FormatPrint(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                }
//                Duke.FormatPrint(str);
            }
        }
    }
}
