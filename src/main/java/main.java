import java.util.*;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String[] arr = new String[101];
        String input = sc.nextLine();
        int curr = 0;
        arr[curr] = input;
        while(!input.equals("bye")) {
            if(!input.equals("list")) {
                //System.out.println(input); level 1
                System.out.println(String.format("added: %s", input));
            }
            else {
                for(int i = 0; i < curr; i++) {
                    System.out.println(String.format("%s. %s", i + 1, arr[i]));
                }
            }
            input = sc.nextLine();
            arr[++curr] = input;
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
