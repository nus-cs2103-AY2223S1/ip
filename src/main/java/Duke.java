import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hi, I'm Jamie.\nWhat do you want?");

        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                Tasks.printTaskList();
                input = sc.nextLine();
                continue;
            }
            if (input.startsWith("mark")) {
                try{
                    int number = Integer.parseInt(input.substring(5));
                    Tasks.mark(number);
                    input = sc.nextLine();
                    continue;
                } catch (NumberFormatException e) {
                    //Continue
                }
            }
            if (input.startsWith("unmark")) {
                try{
                    int number = Integer.parseInt(input.substring(7));
                    Tasks.unMark(number);
                    input = sc.nextLine();
                    continue;
                } catch (NumberFormatException e) {
                    //Continue
                }
            }
            Tasks.taskList.add(new Tasks(input));
            System.out.println("added: " + input);
            input = sc.nextLine();
        }

        System.out.println("bye >:(");

    }
}
