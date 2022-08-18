import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    //  initialise task list
    private static ArrayList<Task> list = new ArrayList<>(100);

    // Methods Start
    private static void addTask(Task task) {
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println(String.format("Now you have %s task(s) in the list", list.size()));
    }

    private static void deleteTask(int index) {
        System.out.println("Noted, I've removed this task:");
        System.out.println(list.get(index).toString());
        list.remove(index);
        System.out.println(String.format("Now you have %s task(s) in the list", list.size()));

    }

    private static void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void list() {
        System.out.println("Here are the tasks in your list:");
        for (int x = 0; x < list.size(); x++) {
            System.out.println(String.format("%s.%s", x+1, list.get(x).toString()));
        }
    }

    private static void mark (int index) throws IllegalIndexException {
        //  error checking
        if (index < 0 || list.get(index) == null) {
            throw new IllegalIndexException("Index invalid!");
        } else {
        list.get(index).setDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(String.format("%s.%s", index+1, list.get(index).toString()));
        }
    }

    public static void unmark(int index) throws IllegalIndexException {
        //  error checking
        if (index < 0 || list.get(index) == null) {
            throw new IllegalIndexException("Index invalid!");
        } else {
        list.get(index).setNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(String.format("%s.%s", index+1, list.get(index).toString()));
        }
    }

    // Methods End
    public static void main(String[] args) {
        //  Opening statements
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        // initialise scanner
        Scanner sc = new Scanner(System.in);
        //  program start
        while (true) {
            try {
                //  User Input
                String str = sc.nextLine();
                //  Bye
                if (str.equals("bye")) {
                    sayBye();
                    break;
                }
                //  List
                if (str.equals("list")) {
                    list();
                    continue;
                }
                //  Marking
                if ((str.length() >= 4) && (str.substring(0, 4).equals("mark"))) {
                    //  error checking
                    if (str.length() == 4 || str.substring(5).equals("")) {
                        throw new IllegalIndexException("OOPS!!! The index of a mark cannot be empty.");
                    }
                    String remainder = str.substring(5);
                    int index = Integer.valueOf(remainder) - 1;
                    mark(index);
                    continue;
                }
                //  Unmarking
                if ((str.length() >= 6) && (str.substring(0, 6).equals("unmark"))) {
                    //  error checking
                    if (str.length() == 6 || str.substring(7).equals("")) {
                        throw new IllegalIndexException("OOPS!!! The index of an unmark cannot be empty.");
                    }
                    String remainder = str.substring(7);
                    int index = Integer.valueOf(remainder) - 1;
                    unmark(index);
                    continue;
                }
                //  Add Todo Task
                if (str.length() >= 4 && str.substring(0, 4).equals("todo")) {
                    //  error checking
                    if (str.length() == 4 || str.substring(5).equals("")) {
                        throw new EmptyDescriptionException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    addTask(new ToDo(str.substring(5)));
                    continue;
                }
            //  Add Deadline Task
            if (str.length() >= 8 && str.substring(0, 8).equals("deadline")) {
                //  error checking
                if (str.length() == 8 || str.substring(9).equals("")) {
                    throw new EmptyDescriptionException("OOPS!!! The description of a deadline cannot be empty.");
                }
                String remainder = str.substring(9);
                String[] arr = remainder.split("/by");
                String description = arr[0];
                String deadline = arr[1];
                addTask(new Deadline(description, deadline));
                continue;
            }
            //  Add Event Task
            if (str.length() >= 5 && str.substring(0, 5).equals("event")) {
                // error checking
                if (str.length() == 5 || str.substring(6).equals("0")) {
                    throw new EmptyDescriptionException("OOPS!!! The description of a event cannot be empty.");
                }
                String remainder = str.substring(6);
                String[] arr = remainder.split("/at");
                String description = arr[0];
                String time = arr[1];
                addTask(new Event(description, time));
                continue;
            }
            //  Delete Tasks
            if (str.length() >= 6 && str.substring(0, 6).equals("delete")) {
                String remainder = str.substring(7);
                int index = Integer.valueOf(remainder) - 1;
                deleteTask(index);
                continue;
            }
            //  if loop reaches here, raise error
            throw new InvalidTaskException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (InvalidTaskException e) {
                System.out.println(e);
                continue;
            } catch (IllegalIndexException e) {
                System.out.println(e);
                continue;
            } catch (EmptyDescriptionException e) {
                System.out.println(e);
                continue;
            }
        }
    }
}


