import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;



public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>();

    private static boolean inputChecker(String[] arr) {
        if (arr.length  < 2) {
            return false;
        }
        if (arr[1].isBlank()) {
            return false;
        }
        return true;
    }

    private static void mark(String[] arr) {
        int i = Integer.parseInt(arr[1]);
        if (i  <= Task.getCount()) {
            tasks.get(i - 1).complete();
            System.out.println("Nice! I have marked this task as done: ");
            System.out.println(tasks.get(i - 1));
        } else {
            System.out.println("Index does not exist");
        }

    }

    private static void unmark(String[] arr) {
        int i = Integer.parseInt(arr[1]);
        if (i <= Task.getCount()) {
            tasks.get(i - 1).incomplete();
            System.out.println("OK, I have marked this task as not done yet: ");
            System.out.println(tasks.get(i - 1));
        } else {
            System.out.println("Index does not exist");
        }
    }

    private static void list() {
        if (Task.getCount() == 0) {
            System.out.println("You currently have no tasks remaining! Create a task now!");
            return;
        }
        for (int i = 0; i < Task.getCount(); i++) {
            if (tasks.get(i) == null) {
                break;
            }
            else {
                System.out.println((i+1) + ". " + tasks.get(i).toString());
            }
        }
    }

    private static void dateSetter(Task t, String date) throws DukeException{
        String[] dateTime = date.split(" ", 2);
        String ddmmyyyyRegex = "[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}";
        String yyyymmddRegex = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}";
        String timeRegex = "[0-9]{4}";
        if (dateTime[0].matches(ddmmyyyyRegex)) {
            String[] info = dateTime[0].split("/");
            String dd = info[0];
            String mm = info[1];
            String yyyy = info[2];
            dd = dd.length() == 1 ? "0" + dd : dd;
            mm = mm.length() == 1 ? "0" + mm : mm;
            LocalDate toAdd = LocalDate.parse(yyyy + "-" + mm + "-" + dd);
            t.setDate(toAdd);
            if (dateTime.length == 2 ) {
                if (dateTime[1].matches(timeRegex)) {
                    t.setTime(dateTime[1]);
                }
            }
        } else if (dateTime[0].matches(yyyymmddRegex)){
           String[] info = dateTime[0].split("-");
           String dd = info[2];
           String mm = info[1];
           String yyyy = info[0];
           dd = dd.length() == 1 ? "0" + dd : dd;
           mm = mm.length() == 1 ? "0" + mm : mm;
           LocalDate toAdd = LocalDate.parse(yyyy + "-" + mm + "-" + dd);
            t.setDate(toAdd);
           if (dateTime.length == 2) {
               if (dateTime[1].matches(timeRegex)) {
                   t.setTime(dateTime[1]);
               } else {
                   throw new DukeException("Please input your time properly, it should be in 24h time.");
               }
           }
        } else {
//            System.out.println(dateTime[0]);
            throw new DukeException("Please input your deadline properly, I can only accept in dd/mm/yyyy OR yyyy-mm-dd.");
        }

    }


    private static void todo(String input) {
        Todo curr = new Todo(input);
        tasks.add(curr);
        System.out.println("Got it. I've added this task: ");
        System.out.println(curr);
        System.out.println("Now you have " + Task.getCount() + " tasks in the list.");
    }

    private static void deadline (String input){
        int created = 0;
        try {
            if (!input.contains("/by")) {
                throw new DukeException("Deadline creation should contain the '/by' tag! Please input again!");
            }
            String arr[] = input.split("/by ", 2);
            Deadline curr = new Deadline(arr[0]);
            created = 1;
            dateSetter(curr, arr[1]);
            tasks.add(curr);
            System.out.println("Got it. I've added this task: ");
            System.out.println(curr);
            System.out.println("Now you have " + Task.getCount() + " tasks in the list.");
        } catch (DukeException e) {
            if (created == 1) {
                Task.reduceTaskCount();
            }
            System.out.println(e);
        }

    }

    private static void event(String input) {
        int created = 0;
        try {
            if (!input.contains("/at")) {
                throw new DukeException("Event creation should contain the '/at' tag! Please input again!");
            }
            String arr[] = input.split("/at ", 2);
            Event curr = new Event(arr[0]);
            created = 1;
            dateSetter(curr, arr[1]);
            tasks.add(curr);
            System.out.println("Got it. I've added this task: ");
            System.out.println(curr);
            System.out.println("Now you have " + Task.getCount() + " tasks in the list.");
        } catch (DukeException e) {
            if (created == 1) {
                Task.reduceTaskCount();
            }
            System.out.println(e);
        }
    }

    private static void delete (String input) {
        int i = Integer.parseInt(input);
        Task removed = tasks.remove(i - 1);
        Task.reduceCount();
        System.out.println("Noted. I have removed this task:");
        System.out.println(removed);
        System.out.println("Now you have " + Task.getCount() + " tasks in the list.");
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);

        while(true) {
            String input = in.nextLine();
            String arr[] = input.split(" ", 2);

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (input.equals("list")) {
                list();
            }

            else if (arr[0].equals("mark")){
                if (!inputChecker(arr)) {
                    System.out.println(DukeException.MarkIndexEmptyException());
                } else {
                    mark(arr);
                }

            }
            else if (arr[0].equals("unmark")) {
                if (!inputChecker(arr)) {
                    System.out.println(DukeException.UnmarkIndexEmptyException());
                } else {
                    unmark(arr);
                }
            }

            else if (arr[0].equals("todo")) {
                if (!inputChecker(arr)) {
                    System.out.println(DukeException.EmptyTaskException());
                } else {
                    todo(arr[1]);
                }

            }

            else if (arr[0].equals("deadline")) {
                if (!inputChecker(arr)) {
                    System.out.println(DukeException.EmptyTaskException());
                } else {
                    deadline(arr[1]);
                }

            }
            else if (arr[0].equals("event")) {
                if (!inputChecker(arr)) {
                    System.out.println(DukeException.EmptyTaskException());
                } else {
                    event(arr[1]);
                }
            }
            else if (arr[0].equals("delete")) {
                if (!inputChecker(arr)) {
                    System.out.println("Index not found in the list!");
                } else {
                    delete(arr[1]);
                }

            }

            else {
                System.out.println("Input not recognised! Please try again! ");
            }
        }

    }
}
