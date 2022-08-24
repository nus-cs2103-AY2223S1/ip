import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) throws EmptyDescriptionException, OutOfRangeException, UnknownCommandException {

        ArrayList<Task> arr = new ArrayList<>();


        System.out.println("Hello I'm Duke" + "\nWhat can I do for you?");

        //scanner
        Scanner sc = new Scanner(System.in);
        String input = "";

        //duke.Storage
        Storage storage = new Storage("duke.txt");
        String s = storage.reader();

        int counter_mark = 1;
        boolean checker = false;

        //duke.Parser
        Parser parser = new Parser();

        //process string
        s = s.replace("[T]", "todo");
        s = s.replace("[D]", "deadline");
        s = s.replace("[E]", "event");
        s = s.replace("[ ]", "");
        s = s.replace("[X]", "");
        if (s.contains(":")) {
            s = s.replace("[ ]", "");
            s = s.replace("(", "/");
            s = s.replace(":", "");
            s = s.replace(")", "");
        }

        //code mechanics
        while (true) {
            if (parser.terminator) {
                break;
            } else if (s.equals("")) {
                parser.setChecker();
                input = sc.nextLine();
                parser.parse(input);
            } else {
                int temp = s.indexOf("%");
                input = s.substring(0, temp);
                parser.parse(input);
                s = s.replaceAll(input + "%", ""); //remove old string
            }

<<<<<<< HEAD
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                System.out.println("\nHere are the tasks in your list:");
                for (int i = 0; i < count; i ++) {
                    System.out.println((i + 1) + ". " + arr.get(i).toString());
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
                        arr.get(index).setDone();
                    }

                    if (checker) {
                        System.out.println("Nice! I've marked this task as done:\n" +
                                arr.get(index).toString());
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
                    arr.add(new Todo(input));

                    //output
                    if (checker) {
                        System.out.println("Got it. I've added this task:");
                        System.out.println(arr.get(count).toString());
                        System.out.printf( "Now you have %d tasks in the list.", count + 1);
                    }

                    
                    count++;
                } catch (EmptyDescriptionException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.contains("deadline")) {

                input = input.replaceAll("deadline ", "");

                //string manipulation
                String[] s_arr = input.split("/", 2); //split array
                s_arr[1] = s_arr[1].replaceAll("by ", "");
                arr.add(new Deadline(s_arr[0], s_arr[1]));

                //output
                if (checker) {
                    System.out.println("Got it. I've added this task:");
                    System.out.println(arr.get(count).toString());
                    System.out.printf("Now you have %d tasks in the list.", count + 1);
                }

                count++;

            } else if (input.contains("event")) {

                //string manipulation
                input = input.replaceAll("event ", "");
                String[] s_arr = input.split("/", -1); //split array
                s_arr[1] = s_arr[1].replaceAll("at ", "");

                arr.add(new Event(s_arr[0], s_arr[1]));

                //output
                if (checker) {
                    System.out.println("\nGot it. I've added this task:");
                    System.out.println(arr.get(count).toString());
                    System.out.printf("Now you have %d tasks in the list.", count + 1);
                }
                count++;
            } else if (input.contains("delete")) {
                int index = -1;

                //string manipulation
                input = input.replaceAll("delete ", "");
                index = Integer.parseInt(input) - 1;

                //output
                System.out.println("Noted. I've removed this task:");
                System.out.println(arr.get(index).toString());

                arr.remove(index);
                count--;

                System.out.printf( "Now you have %d tasks in the list.", count);
            } else {
                try {
                    throw new UnknownCommandException();
                } catch (UnknownCommandException e) {
                    System.out.println(e.getMessage());
                }
            }

=======
>>>>>>> branch-Level-7
        }

        sc.close();

        // write to file once scanner closes
        storage.writer(parser.getArr());

    }


}

