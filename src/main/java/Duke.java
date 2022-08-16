import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "______________________________________ \n";

        String firstText = horizontalLine
                + "hi... I'm Karen \n"
                + "What do you want this time? \n"
                + horizontalLine;
        System.out.println(firstText);

        Scanner sc = new Scanner(System.in);
        String echoText = sc.nextLine();
        while (!echoText.equals("bye")) {
            System.out.println(horizontalLine + echoText + "\n" + horizontalLine);
            echoText = sc.nextLine();
        }
        System.out.println("K finally, good riddance!");
    }
}
