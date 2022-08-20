import java.util.ArrayList;
import java.util.Scanner;
public class Duke {


//
//
//    public void start(){
//        Scanner input = new Scanner(System.in);
//
//        System.out.println("What are your commands sir:");
//    }
//
//    public static void main(String[] args) {
//        start();
//    }



    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("What are your commands sir:");

        ArrayList<DukeTask> tasklist = new ArrayList<>();

//        String[] arr = new String[100];
//        char[] markArr = new char[100];
//        char[] eventTypeArr = new char[100];
        boolean pred = true;
//        int i = 0;

        while(pred) {
            if (input.hasNext()) {
                String str = input.nextLine();
                if (str.startsWith("list")) {
                    System.out.println("You requested to view your schedule:");
                    for (int j = 0; j < tasklist.size(); j++) {
                        System.out.println(String.format("List %d: ", j) + tasklist.get(j).toString());
                    }
                } else if (str.startsWith("bye")) {
                    System.out.println("Bye. Hope to see you again");
                    pred = false;

                } else if (str.startsWith("mark")) {
//                    System.out.println(str.substring(5));
                    try {
                        int j = Integer.valueOf(str.substring(5));
                        tasklist.get(j).isMarked = true;
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(String.format("List %d: ", j) + tasklist.get(j).toString());
                    } catch (Exception e) {
                        System.out.println("Something went wrong, here's the error message cuz im lazy to figure it out for you: " + e);
                    }


                } else if (str.startsWith("unmark")) {
                    int j = Integer.valueOf(str.substring(7));
                    tasklist.get(j).isMarked = false;
                    System.out.println("Got it. I've mark this task as not done:");
                    System.out.println(String.format("List %d: ", j) + tasklist.get(j).toString());

                } else if (str.startsWith("todo")) {
                    try {
                        str = str.substring(5);
                        if (str.isBlank()) {
                            System.out.println("Oops, todo can't be empty");
                            continue;
                        }
                        DukeTask t = new DukeTask(str, false, 'T');
                        tasklist.add(t);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(String.format("List %d: ", tasklist.size() - 1) + t.toString());
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Oops, todo can't be empty");
                    } catch (Exception e) {
                        System.out.println("...");
                        System.out.println(e);
                    }


                } else if (str.startsWith("deadline")) {
                    str = str.substring(9);
                    String s = str.substring(0, str.indexOf('/')) + '(' + str.substring(str.indexOf('/') + 1) + ')';
                    DukeTask t = new DukeTask(s, false, 'D');
                    tasklist.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(String.format("List %d: ", tasklist.size() - 1) + t.toString());

                } else if (str.startsWith("event")) {
                    str = str.substring(6);
                    String s = str.substring(0, str.indexOf('/')) + '(' + str.substring(str.indexOf('/') + 1) + ')';
                    DukeTask t = new DukeTask(s, false, 'E');
                    tasklist.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(String.format("List %d: ", tasklist.size() - 1) + t.toString());

                } else if (str.startsWith("delete")) {
//                    System.out.println(str.substring(5));
                    try {
                        int j = Integer.valueOf(str.substring(7));
                        System.out.println("Alight! I've deleted this task for you:");
                        System.out.println(String.format("List %d: ", j) + tasklist.get(j).toString());
                        tasklist.remove(j);

                    } catch (Exception e) {
                        System.out.println("Something went wrong, here's the error message cuz im lazy to figure it out for you: " + e);
                    }


                } else {
                    System.out.println("Sorry, I don't know what that means");

                }
            }
        }

//
//        String logo = " ____        _        \n"
//                    + "|  _ \\ _   _| | _____ \n"
//                    + "| | | | | | | |/ / _ \\\n"
//                    + "| |_| | |_| |   <  __/\n"
//                    + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Bye from\n" + logo);
    }
}
