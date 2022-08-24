package duke;

import duke.Deadline;
import duke.EmptyDescriptionException;
import duke.Event;
import duke.OutOfRangeException;

import java.util.ArrayList;


public class Parser {


    int count = 0;
    TaskList tasks = new TaskList();
    boolean terminator = false;
    boolean checker = false;
    Ui ui = new Ui();

    public Parser() {

    }

    /**
     * Processes the input given by the user of Duke
     *
     * @param input what the user typed
     */
    public void parse(String input) {

        if (input.equals("bye")) {
            this.terminator = true;
            System.out.println(ui.print(1));
        } else if (input.equals("list")) {
            System.out.println(ui.print(2));
            for (int i = 0; i < count; i ++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        } else if (input.contains("mark")) {
            int index = -1;
            try {
                if (input.equals("mark")) {
                    throw new EmptyDescriptionException("Please select a task to mark");
                } else {
                    input = input.replaceAll("mark ", "");
                    index = Integer.parseInt(input) - 1;
                }

                if (index > count - 1 || index < 0) {
                    throw new OutOfRangeException(index + 1);
                } else {
                    tasks.get(index).setDone();
                }

                if (checker) {
                    System.out.println(ui.print(3) +
                            tasks.get(index).toString());
                }

            } catch (EmptyDescriptionException | OutOfRangeException e) {
                System.out.println(e.getMessage());
            }
        } else if (input.contains("todo")) {
            try {
                if (input.equals("todo")) {
                    throw new EmptyDescriptionException();
                }
                input = input.replaceAll("todo ", "");
                tasks.add(new Todo(input));

                //output
                if (checker) {
                    System.out.println(ui.print(4));
                    System.out.println(tasks.get(count).toString());
                    System.out.printf( "Now you have %d tasks in the list.", count + 1);
                }


                count++;
            } catch (EmptyDescriptionException e) {
                System.out.println(e.getMessage());
            }
        } else if (input.contains("deadline")) {

            input = input.replaceAll("deadline ", "");

            //string manipulation
            String[] s_arr = input.split("/", -1); //split array
            s_arr[1] = s_arr[1].replaceAll("by ", "");
            tasks.add(new Deadline(s_arr[0], s_arr[1]));

            //output
            if (checker) {
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(count).toString());
                System.out.printf("Now you have %d tasks in the list.", count + 1);
            }

            count++;

        } else if (input.contains("event")) {

            //string manipulation
            input = input.replaceAll("event ", "");
            String[] s_arr = input.split("/", -1); //split array
            s_arr[1] = s_arr[1].replaceAll("at ", "");

            tasks.add(new Event(s_arr[0], s_arr[1]));

            //output
            if (checker) {
                System.out.println("\n" + ui.print(4));
                System.out.println(tasks.get(count).toString());
                System.out.printf("Now you have %d tasks in the list.", count + 1);
            }
            count++;
        } else if (input.contains("delete")) {
            int index = -1;

            //string manipulation
            input = input.replaceAll("delete ", "");
            index = Integer.parseInt(input) - 1;

            //output
            System.out.println(ui.print(7));
            System.out.println(tasks.get(index).toString());

            tasks.remove(index);
            count--;

            System.out.printf( "Now you have %d tasks in the list.", count);
        } else if (input.contains("find")) {
            //string manipulation
            int count = 1;
            input = input.replaceAll("find ", "");
            ArrayList<Task> filtered_List = new ArrayList<>();
            for (Task item: tasks.getArr()) {
                if (item.description.contains(input)) {
                    filtered_List.add(item);
                }
            }
            System.out.println("Here are the matching tasks in your list:");
            for (Task item: filtered_List) {
                System.out.println(count + ". " + item.toString());
                count++;
            }
        } else {
            try {
                throw new UnknownCommandException();
            } catch (UnknownCommandException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public boolean terminate() {
        return this.terminator;
    }

    public ArrayList<Task> getArr() {
        return tasks.getArr();
    }

    public void setChecker() {
        checker = true;
    }
}