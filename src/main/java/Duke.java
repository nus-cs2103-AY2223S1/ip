import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void chat(){
        Scanner myScan = new Scanner(System.in);
        String s;
        ArrayList<Task> aList = new ArrayList<>();

        while (true) {

            try {
                s = myScan.nextLine();
                if (s.equals("bye")) {
                    System.out.println("----------------------");
                    System.out.println("Bye, hope to see you again!");
                    System.out.println("----------------------");
                    break;

                } else if (s.equals("list")) {
                    System.out.println("----------------------");
                    for (int i = 0; i < aList.size(); i++) {
                        String display = String.format("%d.%s", i + 1, aList.get(i).toString());
                        System.out.println(display);
                    }
                    System.out.println("----------------------");

                } else if (s.indexOf("mark") == 0) {
                    String subString = s.substring(5);
                    int a = Integer.parseInt(subString) - 1;
                    aList.get(a).markDone();

                    System.out.println("----------------------");
                    System.out.println("Ok! I've marked this task as done");
                    System.out.println(aList.get(a).toString());
                    System.out.println("----------------------");

                } else if (s.indexOf("unmark") == 0) {
                    String subString = s.substring(7);
                    int a = Integer.parseInt(subString) - 1;
                    aList.get(a).markUndone();

                    System.out.println("----------------------");
                    System.out.println("Ok! I've marked this task as undone");
                    System.out.println(aList.get(a).toString());
                    System.out.println("----------------------");
                } else if (s.indexOf("delete") == 0) {
                    String subString = s.substring(7);
                    int a = Integer.parseInt(subString) - 1;

                    System.out.println("----------------------");
                    System.out.println("Noted! I've removed this task");
                    System.out.println(aList.get(a).toString());
                    aList.remove(a);
                    System.out.println("Now you have " + aList.size() + " tasks!");
                    System.out.println("----------------------");


                } else if (s.indexOf("todo") == 0) {
                    if (s.length() < 5) {
                        throw new EmptyDescriptionException("Empty description", "todo");
                    }
                    String subString = s.substring(5, s.length());
                    Todo a = new Todo(subString);
                    aList.add(a);

                    System.out.println("----------------------");
                    System.out.println("added: " + a.toString());
                    System.out.println(String.format("Now you have %d tasks in the list", aList.size()));
                    System.out.println("----------------------");

                } else if (s.indexOf("deadline") == 0 ) {
                    if (s.length() < 9) {
                        throw new EmptyDescriptionException("Empty description", "deadline");
                    } else if (!s.contains("/by")) {
                        throw new InvalidFormatException("Invalid format");
                    } else if (s.indexOf("/by") + 3 == s.length()) {
                        throw new InvalidFormatException("Invalid format");
                    }

                    String descript = s.substring(9, s.indexOf("/") - 1);
                    String by = s.substring(s.indexOf("/") + 4);
                    Deadline d = new Deadline(descript, by);
                    aList.add(d);
                    System.out.println("----------------------");
                    System.out.println("added: " + d.toString());
                    System.out.println(String.format("Now you have %d tasks in the list", aList.size()));
                    System.out.println("----------------------");

                } else if (s.indexOf("event") == 0 ) {
                    if (s.length() < 6) {
                        throw new EmptyDescriptionException("Empty description", "event");
                    } else if (!s.contains("/at")) {
                        throw new InvalidFormatException("Invalid format");
                    } else if (s.indexOf("/at") + 3 == s.length()) {
                        throw new InvalidFormatException("Invalid format");
                    }

                    String descript = s.substring(6, s.indexOf("/") - 1);
                    String at = s.substring(s.indexOf("/") + 4);
                    Event e = new Event(descript, at);
                    aList.add(e);

                    System.out.println("----------------------");
                    System.out.println("added: " + e.toString());
                    System.out.println(String.format("Now you have %d tasks in the list", aList.size()));
                    System.out.println("----------------------");

                } else {
                    throw new InvalidTaskException("No valid task descriptor");
                }

            } catch (EmptyDescriptionException e) {
                System.out.println("----------------------");
                System.out.println(e.toString());
                System.out.println("----------------------");
            } catch (InvalidTaskException e) {
                System.out.println("----------------------");
                System.out.println(e.toString());
                System.out.println("----------------------");
            } catch (InvalidFormatException e) {
                System.out.println("----------------------");
                System.out.println(e.toString());
                System.out.println("----------------------");
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("----------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can i do for you?");
        System.out.println("----------------------");

        chat();
    }
}
