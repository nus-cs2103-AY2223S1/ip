import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" +  "What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input;
        input = sc.nextLine();
        List<String> list = new ArrayList<>();

        while(!input.equals("bye")){
            if(input.equals("list")){
                for(int i = 0; i < list.size(); i++)
                    System.out.println(i+1 + ". " + list.get(i));
            } else {
                list.add(input);
                System.out.println("added: " + input);
            }
            input = sc.nextLine();

        }
        System.out.print("Bye. Hope to see you again soon!");



    }
}
