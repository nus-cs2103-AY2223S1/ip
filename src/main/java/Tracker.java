import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class Tracker {
    public static List<Task> list = new ArrayList<>();

    public void printList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }


    public void markDone(String num){
        int index = Integer.parseInt(num);

        Task task = list.get(index-1);
        task.complete();
    }

    public void markUndone(String num){
        int index = Integer.parseInt(num);
        Task task = list.get(index-1);
        task.undo();
    }



    public Tracker() {
    }

    public void simulate() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printList();
            } else {
                String[] command = input.split(" ");
                    if (command.length > 1) {
                        switch (command[0]) {
                            case "mark":
                                markDone(command[1]);
                                break;
                            case "unmark":
                                markUndone(command[1]);
                                break;
                            default:
                                break;
                        }
                    } else {
                        list.add(new Task(input));
                    }
            }
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();

    }

}
