import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "Turtle";
        System.out.println("Hello from " + logo +"!");
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        String input;

        while(run) {
            System.out.println("\n--------------------");
            input = sc.nextLine();

            if(input.equals("bye")) {
                System.out.println(input);
                run = false;
            } else {
                System.out.print(input);
            }
        }
    }
}
