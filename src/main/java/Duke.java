import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public Task[] tasks;

    private static final ArrayList<Task> collection = new ArrayList<>();

    public static final Scanner sc = new Scanner(System.in);


    public static boolean isNotValid(String str) {
        String[] s = str.split(" ");
        return (s.length < 1);
    }




    public static void main(String[] args) throws DukeException, IOException, IllegalCommandException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("___________________________________");
        System.out.println("Hello! I'm Duke\n What can I do for you?");
        System.out.println("____________________________________");

        String input = sc.nextLine();

        File file = new File("database/duke.txt");
        if (file.createNewFile()) {
            System.out.println("File has been created: " + file.getName());
        } else {
            System.out.println("File already exists.");
            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                reader.useDelimiter(" \\| ");
                Constants constant = Constants.valueOf(reader.next().toUpperCase());
                Task task;
                switch (constant) {
                    case TODO:
                        String tdDescription = reader.next();
                        Todo todo = new Todo(tdDescription);
                        collection.add(todo);
                        break;
                    case DEADLINE:
                        String dlDescription = reader.next();
                        Deadline deadline = new Deadline(dlDescription);
                        collection.add(deadline);
                        break;
                    case EVENT:
                        String evDescription = reader.next();
                        Event event = new Event(evDescription);
                        collection.add(event);
                        break;

                    default:
                        throw new IllegalCommandException("Illegal command given");
                }
            }
        }




        while (!input.equals("bye")) {

            if (input.equals("list")) {
                System.out.println("___________________________________");
                for (int i = 0; i < collection.size(); i++) {
                    System.out.println( (i+1) + "."
                            + collection.get(i).toString());
                }
                System.out.println("___________________________________");
                input = sc.nextLine();

            }

            if (input.startsWith("mark")) {
                int number = Integer.parseInt(input.substring(5));
                collection.get(number-1).mark();
                System.out.println("___________________________________");
                System.out.println("Nice! I've marked this task done: " + "\n"
                        + "[" + collection.get(number-1).getStatusIcon() + "] " +
                        collection.get(number-1).description);
                System.out.println("___________________________________");
                input = sc.nextLine();
            }

            if (input.equals("unmark")) {
                int number = Integer.parseInt(input.substring(7));
                collection.get(number-1).unmark();
                System.out.println("___________________________________");
                System.out.println("OK, I've marked this task as not done yet: " + "\n"
                        + "[" + collection.get(number-1).getStatusIcon() + "] " +
                        collection.get(number-1).description);
                System.out.println("___________________________________");
                input = sc.nextLine();
            }

            if (input.startsWith("deadline")) {
                try {
                    if (isNotValid(input))
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    Deadline deadline = new Deadline(input);
                    collection.add(deadline);
                    System.out.println("___________________________________");
                    System.out.println("Got it. I've added this task:" + "\n"
                            + "  " + deadline.toString() + "\n"
                            + "Now you have " + collection.size() + " tasks in the list.");
                    System.out.println("___________________________________");
                } catch(DukeException e) {
                    System.out.println(e.getMessage());

                } finally {
                    input = sc.nextLine();
                }
            }

            if (input.startsWith("todo")) {
                try {
                    if (isNotValid(input))
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    Todo todo = new Todo(input);
                    collection.add(todo);
                    System.out.println("___________________________________");
                    System.out.println("Got it. I've added this task:" + "\n"
                            + "  " + todo.toString() + "\n"
                            + "Now you have " + collection.size() + " tasks in the list.");
                    System.out.println("___________________________________");
                } catch(DukeException e) {
                    System.out.println(e.getMessage());

                } finally {
                    input = sc.nextLine();
                }
            }

            if (input.startsWith("event")) {
                try {
                    if (isNotValid((input)))
                        throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                    Event event = new Event(input);
                    collection.add(event);
                    System.out.println("___________________________________");
                    System.out.println("Got it. I've added this task:" + "\n"
                            + "  " + event.toString() + "\n"
                            + "Now you have " + collection.size() + " tasks in the list.");
                    System.out.println("___________________________________");
                } catch(DukeException e) {
                    System.out.println(e.getMessage());

                } finally {
                    input = sc.nextLine();
                }
            }


            if (input.startsWith("delete")) {
                int number = Integer.parseInt(input.substring(7));
                Task temp = collection.get(number-1);
                collection.remove(number-1);
                System.out.println("___________________________________");
                System.out.println("Noted. I've removed this task:" + "\n"
                        + "  " + temp.toString() + "\n"
                        + "Now you have " + collection.size() + " tasks in the list.");
                System.out.println("___________________________________");
                input = sc.nextLine();

            }

            else {
                try {
                    throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(.");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } finally {
                    input = sc.nextLine();
                }
            }
        }

        FileWriter fileWriter = new FileWriter(file);
        for (Task task : collection) {
            fileWriter.write(task.fileFormat() + System.lineSeparator());
        }
        fileWriter.close();

        System.out.println("___________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("___________________________________");
    }

}
