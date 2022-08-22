import java.util.*;
public class Duke {

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Duke newDuke = new Duke();
        newDuke.chatDuke();


        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
    }

    public void chatDuke(){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> strArray = new ArrayList<>();
        String str = sc.nextLine();
        if (str.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon");
        }
        while (!str.equals("bye")){
            if (str.equals("list")) {
                returnArrayStrings(strArray);
                str = sc.nextLine();
            } else {
                strArray.add(str);
                System.out.println("added: " + str);
                str = sc.nextLine();
            }



        }
        if (str.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon");
        } else {
            strArray.add(str);
            str = sc.nextLine();
        }




    }

    public void returnArrayStrings(ArrayList<String> strArray){
        for (int i = 0; i < strArray.size(); i++){
            if (strArray.get(i) != null) {
                System.out.println(Integer.toString(i + 1) + ". " + strArray.get(i));
            } else {
                return;
            }
        }
    }


}

