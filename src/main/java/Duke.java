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

    private static ArrayList<Task> remove(int index, ArrayList<Task> arr) {
        ArrayList<Task> newArr = new ArrayList<>();
        if(index < 0 || index > arr.size()) {
            return arr;
        }
        for(int i = 0; i < arr.size(); i++) {
            if(i == index) {
                i++;
            }
            newArr.add(arr.get(i));
        }

        return newArr;
    }

    private static void handleMarkDoneUndone(ArrayList<Task> arrayList, String command) throws DukeException {
        String args[] = command.split(" ", 2);
        if (args.length != 2) {
            throw new DukeException("Invalid command");
        }
        int index = Integer.parseInt(args[1]) - 1;
        if (index < 0 || index > arrayList.size() - 1) {
            throw new DukeException("Invalid command");
        }
        Task curr = arrayList.get(index);
        if (args[0].equals("mark")) {
            if (!curr.isDone) {
                curr.isDone = true;
                Duke.TaskStateChangePrint(curr, true);
            }
        } else { // args[0].equals("unmark")
            if (curr.isDone) {
                curr.isDone = false;
                Duke.TaskStateChangePrint(curr, false);
            }
        }
    }

    private static void handleDelete(ArrayList<Task> arrayList, String command) throws DukeException {
        String args[] = command.split(" ", 2);
        int index;
        try {
            index = Integer.parseInt(args[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid command");
        }
        Task curr = arrayList.get(index);
        arrayList = Duke.remove(index, arrayList);
        Duke.FormatPrint("Noted. I've removed this task:\n" + curr.toString()
                + "Now you have " + String.valueOf(arrayList.size()) + " tasks in the list.");
    }

    private enum TaskEnum {
        Todo,
        Deadline,
        Event
    }

    private static void createEvent(ArrayList<Task> tasks, TaskEnum taskEnum, String command) throws DukeException{
        String args[];
        Task res;
        switch (taskEnum) {
            case Todo:
                res = new Todo(command);
                break;

            case Deadline:
                args = command.split("/", 2);
                if (args.length != 2) {
                    throw new DukeException("Invalid Input");
                }
                res = new Deadline(args[0].trim(), args[1].substring(3));
                break;

            case Event:
                args = command.split("/", 2);
                if (args.length != 2) {
                    throw new DukeException("Invalid Input");
                }
                res = new Event(args[0].trim(), args[1].substring(3));;
                break;

            default:
                throw new DukeException("Invalid Input");
        }
        tasks.add(res);
        Duke.FormatPrint("Got it. I've added this task:\n" + res.toString());
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
        String ERROR_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        String END_WORD = "bye";
        String ENDING = "    Bye. Hope to see you again soon!";

        ArrayList<Task> stored_items = new ArrayList<>();

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
                    String arguments[] = str.split(" ", 2);
                    try {
                        switch (arguments[0]) {
                            case "mark": // same flow as case "unmark"
                            case "unmark":
                                handleMarkDoneUndone(stored_items, str);
                                break;

                            case "delete":
                                Duke.handleDelete(stored_items, str);
                                break;

                            case "deadline":
                                Duke.createEvent(stored_items, TaskEnum.Deadline, arguments[1]);
                                break;

                            case "todo":
                                Duke.createEvent(stored_items, TaskEnum.Todo, arguments[1]);
                                break;

                            case "event":
                                Duke.createEvent(stored_items, TaskEnum.Event, arguments[1]);
                                break;

                            default:
                                Duke.FormatPrint(ERROR_MESSAGE);
                        }
                    } catch (DukeException e) {
                        Duke.FormatPrint(ERROR_MESSAGE);
                    }

                }
//                Duke.FormatPrint(str);
            }
        }
    }
}
