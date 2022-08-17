import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String[] arr = new String[100];
        int count=0;
        String str = scanner.nextLine();
        while(!str.equals("bye")) {
            if(str.equals("list")){
                for(int i=0;i<count;i++){
                    System.out.println(i+1 +". "+arr[i]);
                }
            } else{
                arr[count] = str;
                count++;
                System.out.println("added: "+str);
            }

            str = scanner.nextLine();
        }

        if (str.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }

    }
}
