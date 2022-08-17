import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String input;
        String[] segments;
        String[] segments2;
        ArrayList<Task> items = new ArrayList<Task>();
        String space = " ";

        Scanner sc= new Scanner(System.in);

        String line = "________________________________________________________________\n";
        System.out.println(line + "Hello! I'm Shanice:)");
        System.out.println("What can I do for you?\n" + line);

        //level 5:
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            segments = input.split("/");
            segments2 = segments[0].split(space);

            //if input is bye, end while loop
            if (input.equals("bye")) {
                System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
                break;
            }
            else if (input.equals("list")) {
                if (items.size() != 0) {
                    System.out.println(line + "Here are the tasks in your list: ");
                    for (int i = 0; i < items.size(); i++) {
                        System.out.println(i+1 + ". " + items.get(i));
                    }
                    System.out.println(line);
                }
                else {
                    System.out.println(line + new DukeException("The list is currently empty. Please add some tasks"));
                    System.out.println(line);
                }
            }
            else if (segments2[0].equals("mark") || segments2[0].equals("unmark")) {
                if (!input.replace(segments2[0],"").equals("")) {
                    int index = Integer.parseInt(segments2[1]);
                    if (index <= items.size()) {
                        Task t = items.get(index - 1);
                        if (segments2[0].equals("mark")) {
                            System.out.println(line + "Nice! I've marked this task as done: ");
                            t.markAsDone();
                        }
                        else {
                            System.out.println(line + "OK, I've marked this task as not done yet:");
                            t.markAsNotDone();
                        }
                        System.out.println(t);
                        System.out.println(line);
                    }
                    else {
                        System.out.println(line + new DukeException("There is no such task available."));
                        System.out.println(line);
                    }
                }
                else {
                    System.out.println(line + new DukeException("Please specify which tasks to mark / unmark."));
                    System.out.println(line);
                }
            }
            else if (segments2[0].equals("todo") || segments2[0].equals("deadline") || segments2[0].equals("event")) {
                Task t;
                segments[0] = segments[0].replace(segments2[0], "");
                if  (segments[0].equals("")) {
                    System.out.println(line + new DukeException("the description of a " + segments2[0] + " cannot be empty."));
                    System.out.println(line);
                }
                else {
                    if (segments2[0].equals("todo")) {
                        t = new Todo(segments[0]);
                    }
                    else if (segments2[0].equals("deadline")) {
                        segments[1] = segments[1].replace("by ", "");
                        t = new Deadline(segments[0], segments[1]);
                    }
                    else {
                        segments[1] = segments[1].replace("at ", "");
                        t = new Events(segments[0], segments[1]);
                    }

                    items.add(t);
                    System.out.println(line + "Got it. I've added this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + items.size() + " tasks in the list.\n" + line);
                }
            }
            else if (segments2[0].equals("delete")) {
                if (!input.replace(segments2[0],"").equals("")) {
                    int index = Integer.parseInt(segments2[1]);
                    if (index <= items.size()) {
                        Task t = items.get(index - 1);
                        items.remove(t);
                        System.out.println(line + "Noted. I've removed this task:");
                        System.out.println(t);
                        System.out.println("Now you have " + items.size() + " tasks in the list.\n" + line);
                    }
                    else {
                        System.out.println(line + new DukeException("There is no such task available."));
                        System.out.println(line);
                    }
                }
                else {
                    System.out.println(line + new DukeException("Please specify which tasks to delete."));
                    System.out.println(line);
                }
            }
            else {
                System.out.println(line + new DukeException("I'm sorry, but I don't know what that means :-("));
                System.out.println(line);
            }
        }
    }

}

