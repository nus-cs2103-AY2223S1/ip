import java.util.Scanner;
import java.util.ArrayList;

public class Scruffles {
    public static void main(String[] args) {

        System.out.println("woof! I'm scruffles the task tracking doggo\n" + "what can I woof for you today?");

        Scanner sc = new Scanner(System.in);
        String input = "";

        ArrayList<Task> list = new ArrayList<Task>(100);
        int taskCount = 0;

        while (true) {

            input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("woof see you again woof!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    String output = (i + 1) + "." + list.get(i).toString();
                    System.out.println(output);
                }
            } else if (input.startsWith("mark")) {
                try {
                    if (input.equals("mark") || input.equals("mark ")) {
                        throw new DescriptionEmptyException("grrrr >:( you need to mark something woof woof!");
                    } else {
                        input = input.replaceFirst("mark ", "");
                        int listRef = Integer.parseInt(input) - 1;
                        if (listRef >= list.size() || listRef < 0) {
                            throw new OutOfBoundsException(listRef + 1);
                        } else {
                            list.get(listRef).setDone();
                            System.out.println("woof! the task is now marked as done woof!");
                            System.out.println(list.get(listRef).toString());
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("grrrr >:( you need to input an integer woof woof!");
                } catch (DescriptionEmptyException | OutOfBoundsException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("todo")) {
                try {
                    if (input.equals("todo") || input.equals("todo ")) {
                        throw new DescriptionEmptyException();
                    } else {
                        input = input.replaceFirst("todo ", "");
                        list.add(new Todo(input));

                        System.out.println("woof! the task is added woof!");
                        System.out.println(list.get(taskCount).toString());
                        System.out.println("you now have " + (taskCount + 1) + " tasks in the list woof!");

                        taskCount++;
                    }
                } catch (DescriptionEmptyException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("deadline")) {
                try {
                    if (input.equals("deadline") || input.equals("deadline ")) {
                        throw new DescriptionEmptyException();
                    } else if (!input.contains(" /by ")) {
                        throw new DescriptionEmptyException("grrrr >:( when is your deadline?? woof woof!");
                    } else {
                        input = input.replaceFirst("deadline ", "");
                        String[] inputArray = input.split(" /by ");
                        list.add(new Deadline(inputArray[0], inputArray[1]));

                        System.out.println("woof! the task is added woof!");
                        System.out.println(list.get(taskCount).toString());
                        System.out.println("you now have " + (taskCount + 1) + " tasks in the list woof!");

                        taskCount++;
                    }
                } catch (DescriptionEmptyException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("event")) {
                try {
                    if (input.equals("event") || input.equals("event ")) {
                        throw new DescriptionEmptyException();
                    } else if (!input.contains(" /at ")) {
                        throw new DescriptionEmptyException("grrrr >:( when is your event?? woof woof!");
                    } else {
                        input = input.replaceFirst("event ", "");
                        String[] inputArray = input.split(" /at ");
                        list.add(new Event(inputArray[0], inputArray[1]));

                        System.out.println("woof! the task is added woof!");
                        System.out.println(list.get(taskCount).toString());
                        System.out.println("you now have " + (taskCount + 1) + " tasks in the list woof!");

                        taskCount++;
                    }
                } catch (DescriptionEmptyException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    throw new UnknownArgumentException(input);
                } catch (UnknownArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}