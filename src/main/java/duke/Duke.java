package duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Duke {
    static final String SEPARATING_LINE = "    ____________________________________________________________";
    static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static final String OPENING = "    Hello! I'm Duke\n    What can I do for you?";
    static final String LIST_WORD = "list";
    static final String ERROR_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    static final String END_WORD = "bye";
    static final String ENDING = "    Bye. Hope to see you again soon!";

    private static void FormatPrint(String s) {
        System.out.println(SEPARATING_LINE);
        System.out.println(s);
        System.out.println(SEPARATING_LINE);
    }

    private static void endProgram(ArrayList<Task> arrayList) {
        Duke.FormatPrint(ENDING);
        // code below adapted from
        // stackoverflow.com/questions/28947250/create-a-directory-if-it-does-not-exist-and-then-create-the-files-in-that-direct

        String path = "data";
        File dest = new File(path);

        // create dir if not exist
        boolean isPathExist = dest.exists();
        if (isPathExist) {
            dest.mkdir();
        }

        File file = new File(path + "/duke.txt");
        try {
            FileWriter fw = new FileWriter(file);

            for (Task t : arrayList) {
                fw.write(t.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Fail to write file.");
        }
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

    // duke.Todo: include more support for date string
    private static String parseDateTimeString(String arg) {
        String result = arg;

        return result;
    }

    private static LocalDate parseString2LocalDate(String arg) {
        // current supported format: "2009-10-12"
        String dateString = parseDateTimeString(arg);
        LocalDate res = LocalDate.parse(dateString);
        return res;
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
                res = new Deadline(args[0].trim(), parseString2LocalDate(args[1].substring(3)));
                break;

            case Event:
                args = command.split("/", 2);
                if (args.length != 2) {
                    throw new DukeException("Invalid Input");
                }
                res = new Event(args[0].trim(), parseString2LocalDate(args[1].substring(3)));;
                break;

            default:
                throw new DukeException("Invalid Input");
        }
        tasks.add(res);
        Duke.FormatPrint("Got it. I've added this task:\n" + res.toString());
    }

    public static void main(String[] args) {

        System.out.println("Hello from\n" + logo);



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
                endProgram(stored_items);
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
                                handleDelete(stored_items, str);
                                break;

                            case "deadline":
                                createEvent(stored_items, TaskEnum.Deadline, arguments[1]);
                                break;

                            case "todo":
                                createEvent(stored_items, TaskEnum.Todo, arguments[1]);
                                break;

                            case "event":
                                createEvent(stored_items, TaskEnum.Event, arguments[1]);
                                break;

                            default:
                                FormatPrint(ERROR_MESSAGE);
                        }
                    } catch (DukeException e) {
                        FormatPrint(ERROR_MESSAGE);
                    }

                }
//                Duke.FormatPrint(str);
            }
        }
    }
}
