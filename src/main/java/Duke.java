import java.util.*;

public class Duke {

    private static void listOut(String[] stringList, int i) {
        int j = 0;
        System.out.println("_________________________");
        while (j < i) {
            System.out.println(j + ". " +stringList[j]);
            j++;
        }
        System.out.println("_________________________");
    }

    private static void addString(String[] stringList, String str, int i) {
        stringList[i] = str;
    }

    public static void main(String[] args) {

        String stringList[] = new String[100];
        Scanner sc = new Scanner(System.in);
        int i = 0;

        System.out.println("_________________________");
        System.out.println("    Hi, my name is Doke");
        System.out.println("    What can I do for you?");
        System.out.println("    Enter a String!!");
        System.out.println("_________________________");

        String str= sc.nextLine();

        while (!str.equals("bye")) {
            if (str.equals("list")) {
                listOut(stringList, i);
                str = sc.nextLine();
                continue;
            }

            addString(stringList, str, i);
            i++;
            System.out.println("_________________________");
            System.out.println("added: " + str);
            System.out.println("_________________________");
            System.out.println();

            str = sc.nextLine();
        }

        System.out.println("_________________________");
        System.out.println("    Bye bye!");
        System.out.println("_________________________");

    }
}