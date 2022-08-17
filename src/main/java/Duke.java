import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hi, I'm Jamie.\nWhat do you want?");


        for (String input = sc.nextLine(); !input.equals("bye"); input = sc.nextLine()) {
            if (input.equals("list")) {
                Task.printTaskList();
            }
            if (input.startsWith("mark")) {
                try{
                    int number = Integer.parseInt(input.substring(5));
                    Task.mark(number);
                } catch (NumberFormatException e) {
                    //Continue
                }
            }
            if (input.startsWith("unmark")) {
                try{
                    int number = Integer.parseInt(input.substring(7));
                    Task.unMark(number);
                } catch (NumberFormatException e) {
                    //Continue
                }
            }
            if (input.startsWith("todo")) {
                try {
                    input = input.substring(5);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("The description of todo cannot be empty >:(");
                    continue;
                }
                Task.addTask(new ToDo(input));
            }
            if (input.startsWith("deadline")) {
                int end = input.indexOf("/by ");
                String textInput;
                String byInput;
                try {
                    textInput = input.substring(9, end);
                    byInput = input.substring(end + 4);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Something is missing in the description of this deadline >:(");
                    continue;
                }
                Task.addTask(new Deadline(textInput, byInput));
            }
            if (input.startsWith("event")) {
                int end = input.indexOf("/at ");
                String atInput;
                try {
                    input = input.substring(6, end);
                    atInput = input.substring(end + 4);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Something is missing in the description of this event >:(");
                    continue;
                }
                Task.addTask(new Event(input, atInput));
            }

        }

        System.out.println("bye :(");

    }
}
