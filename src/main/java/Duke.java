import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String lines = "     ____________________________________________________________";
        System.out.println(lines);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(lines);

        Scanner sc = new Scanner(System.in);
        List<String> history = new ArrayList<>();

        while(true) {
            String input = sc.nextLine();
            if(input.equals("bye")) {
                System.out.println(lines);
                System.out.println("     Bye! Hope to see you again soon!");
                System.out.println(lines);
                sc.close();
                break;
            } else if(input.equals("list")){
                System.out.println(lines);
                ListIterator<String> listIterator = history.listIterator();
                while(listIterator.hasNext()) {
                    int index = listIterator.nextIndex()+1;
                    System.out.println("     " +  +index + ". "+ listIterator.next());
                }
                System.out.println(lines);

            } else {
                history.add(input);
                System.out.println(lines);
                System.out.println("     " + "added: " + input);
                System.out.println(lines);
            }
        }
    }
}
