import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<String> strArray = new ArrayList<String>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________");
        String strInput = sc.nextLine();
        while (!strInput.equals("")) {
            if (strInput.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________\n"
                        + " Bye. Hope to see you again soon!"
                        + "\n____________________________________________________________");
            } else if ((strInput.equalsIgnoreCase("list"))){
                System.out.println("____________________________________________________________"
                        + enumerateList()
                        + "\n____________________________________________________________");
            } else {
                addToList(strInput);
            }
            strInput = sc.nextLine();
        }
        sc.close();
    }

    /**
     * Adds user input in the form of String into the strArray.
     *
     * @param s Input from user.
     */
    public static void addToList(String s) {
        strArray.add(s);
        System.out.println("____________________________________________________________\n"
                + " added: "
                + s
                + "\n____________________________________________________________");
    }

    /**
     * Returns a string representation of the list.
     *
     * @return String.
     */
    public static String enumerateList() {
        //StringBuilder over string concat for better performance
        StringBuilder s = new StringBuilder(" ");
        for (int i = 1; i <= strArray.size(); i++) {
            s.append("\n" + i + ". " + strArray.get(i - 1));
        }
        return s.toString();
    }
}
