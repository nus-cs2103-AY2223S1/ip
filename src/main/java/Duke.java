import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Eh hello, my name is Uncle Cheong. \n" +
                                "What you want?");
        String echo = "";
        while (!echo.equals("bye")) {
           echo = sc.next();
           if (echo.equals("bye")) {
               break;
           }
           System.out.println(echo);
        }
        System.out.println("Eh you leaving me so soon?");
    }
}
