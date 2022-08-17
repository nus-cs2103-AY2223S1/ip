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
        Task[] arr = new Task[100];
        int count=0;
        String str = scanner.nextLine();
        while(!str.equals("bye")) {
            if(str.equals("list")){
                System.out.println("Here are the tasks in your list");
                for(int i=0;i<count;i++){
                    System.out.println(i+1 +".["+arr[i].getStatusIcon()+"] "+ arr[i].description);
                }
            }
            else if(str.contains("unmark")){
                String strnum = str.substring(7,8);
                int num = Integer.valueOf(strnum);
                arr[num-1].isDone = false;
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("["+arr[num-1].getStatusIcon()+"] "+ arr[num-1].description);

            }else if(str.contains("mark")){
                String strnum = str.substring(5,6);
                int num = Integer.valueOf(strnum);
                arr[num-1].isDone = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("["+arr[num-1].getStatusIcon()+"] "+ arr[num-1].description);
            }
            else{
                arr[count] = new Task(str);
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
