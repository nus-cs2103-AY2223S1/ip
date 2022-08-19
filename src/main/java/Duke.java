import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        printResponse("hi...");
        String input = reader.nextLine();
        while (!input.equals("bye")) {
            printResponse(input);
            input = reader.nextLine();
        }
        printResponse("bye...");
        
        reader.close();
    }

    private static void printResponse(String input) {
        System.out.println("\t----------------------------------");
        System.out.println("\t" + input);
        System.out.println("\t----------------------------------");
    }

}
