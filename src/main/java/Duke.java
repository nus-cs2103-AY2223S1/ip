import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("What are your commands sir: ");
        String[] arr = new String[100];
        boolean pred = true;
        int i = 0;

        while(pred){
            if (input.hasNext()){
                String str = input.nextLine();

                if (str.contains("list")) {
                    System.out.println("list items -");
                    for (int j = 0; j < i; j++){
                        System.out.println("List " + j + ". " + arr[j]);
                    }
                } else if (str.contains("blah")) {
                    System.out.println("blah");
                } else if (str.contains("bye")) {
                    System.out.println("Bye. Hope to see you again");
                    pred = false;
                } else {
                    System.out.println("Storing this text in the list");
                    arr[i] = str;
                    i++;
                }
            }

        }

        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
