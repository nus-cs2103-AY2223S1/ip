import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("_____________   _       _______________\n|___   __||  | | |     | |__  __| ____|" +
                "\n    | |  | __ ||  \\   /  |  | | | |___\n _  | | | |__| |   \\_/   |  | | |  ___|" +
                "\n| |_| | |  __  | |\\   /| |__| |_| |___\n|_____| |_|  |_|_| \\_/ |_|______|_____|" );
        System.out.println("Hi, I'm Jamie.\nWhat do you want?");


        for (String input = sc.nextLine(); !input.equals("bye"); input = sc.nextLine()) {
            if (input.equals("list")) {
                Task.printTaskList();
            } else if (input.startsWith("mark ")) {
                try{
                    int number = Integer.parseInt(input.substring(5));
                    Task.mark(number);
                } catch (Exception e) {
                    System.out.println("Please enter numbers after mark");
                }
            } else if (input.startsWith("unmark ")) {
                try{
                    int number = Integer.parseInt(input.substring(7));
                    Task.unMark(number);
                } catch (Exception e) {
                    System.out.println("Please enter numbers after unmark");
                }
            } else if (input.startsWith("todo ")) {
                try {
                    input = input.substring(5);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("The description of todo cannot be empty >:(");
                    continue;
                }
                Task.addTask(new ToDo(input));
            } else if (input.startsWith("deadline ")) {
                int end = input.indexOf("/by ");
                String textInput;
                String byInput;
                try {
                    textInput = input.substring(10, end);
                    byInput = input.substring(end + 4);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Something is missing in the description of this deadline >:(");
                    continue;
                }
                Task.addTask(new Deadline(textInput, byInput));
            } else if (input.startsWith("event ")) {
                int end = input.indexOf("/at ");
                String textInput;
                String atInput;
                try {
                    textInput = input.substring(7, end);
                    atInput = input.substring(end + 4);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Something is missing in the description of this event >:(");
                    continue;
                }
                Task.addTask(new Event(textInput, atInput));
            } else if (input.startsWith("delete ")) {
                try{
                    int number = Integer.parseInt(input.substring(7));
                    Task.deleteTask(number);
                } catch (Exception e) {
                    System.out.println("Please enter numbers after delete");
                }
            } else {
                System.out.println("Please enter a valid command");
            }

        }

        System.out.println("bye :(");

    }
}
