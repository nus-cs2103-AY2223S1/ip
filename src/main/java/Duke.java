import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hi, I'm Jamie.\nWhat do you want?");

        String input = sc.nextLine();
        while (!input.equals("bye")) {
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
                Task.addTask(new ToDo(input.substring(5)));
            }
            if (input.startsWith("deadline")) {
                int end = input.indexOf("/by ");
                Task.addTask(new Deadline(input.substring(9, end), input.substring(end + 4)));
            }
            if (input.startsWith("event")) {
                int end = input.indexOf("/at");
                Task.addTask(new Event(input.substring(6, end), input.substring(end + 4)));
            }
            System.out.println("ok next");
            input = sc.nextLine();
        }

        System.out.println("bye >:(");

    }
}
