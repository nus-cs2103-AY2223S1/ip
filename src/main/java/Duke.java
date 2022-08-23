import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Duke {
    public static void main(String[] args) throws EmptyDescriptionException, OutOfRangeException, UnknownCommandException {

        ArrayList<Task> arr = new ArrayList<>();
        int count = 0;

        System.out.println("Hello I'm Duke" + "\nWhat can I do for you?");

        //scanner
        Scanner sc = new Scanner(System.in);
        String input = "";

        String s = reader();
        int counter_mark = 1;
        boolean checker = false;


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

            if (s.equals("")) {
                checker = true;
                input = sc.nextLine();
            } else {
                int temp = s.indexOf("%");
                input = s.substring(0, temp);
                s = s.replaceAll(input + "%", ""); //remove old string
            }

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
                String[] s_arr = input.split("/", -1); //split array
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

        }

        sc.close();

        // write to file once scanner closes
        writer(arr);

    }

    //file writer function
    public static void writer(ArrayList<Task> arr) {
        String temp = "";

        for (Task item: arr) {
            temp += (item.toString() + "\n");
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("duke.txt"));
            writer.write(temp);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //file reader function
    public static String reader() {
        String s = "";
        int counter_mark = 1;
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("duke.txt"));
            while ((s = reader.readLine()) != null) {
                    builder.append(s).append("%");
                    if (s.contains("X")) {
                        builder.append("mark ").append(counter_mark).append("%");
                    }
                    counter_mark++;
            }
                reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return builder.toString();
    }

}

