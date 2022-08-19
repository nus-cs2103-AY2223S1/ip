import java.util.*;
public class Neo {
    public static void main(String[] args) throws NeoException {

        List<String> arrayList = new ArrayList<String>();
        List<Task> arrayL = new ArrayList<Task>();
        String userText;

        Task[] tasks = new Task[100];

        System.out.println("");
        System.out.println("Hello! I'm Neo");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Please enter items you want to add to the list, if you want to quit enter bye");
            System.out.println("");
            System.out.print("Enter Your command: ");
            userText = sc.nextLine();
            System.out.println("");

            if (userText.equals("bye") || userText.equals("Bye")) {
                System.out.println("Exiting chatbot! Hope to see you again");
                break;
            }
            if (userText.equals("list") || userText.equals("List")) {
                for (int i = 0; i < arrayL.size(); i++) {
                    //System.out.println("check");
                    int j = i + 1;
                    //System.out.println(j + ". " + arrayList.get(i));
                    Task temp = arrayL.get(i);
                    //System.out.println(j + ". [" + temp.getIsDone() + "] " + temp.description);
                    System.out.println(j + "." + temp.toString());
                }
            }
            String arr[];
            arr = userText.split(" ", 2);
            if (arr.length>1 && arr[0].equals("mark")){
                int tempi = Integer.valueOf(arr[1]);
                arrayL.get(tempi -1).setIsDone(true);
                System.out.println("Nice! I've marked this task as done");
                System.out.println("[" + arrayL.get(tempi-1).getIsDone() + "] " + arrayL.get(tempi-1).description);
            }
            if (arr.length>1 && arr[0].equals("unmark")){
                int tempi = Integer.valueOf(arr[1]);
                arrayL.get(tempi - 1).setIsDone(false);
                System.out.println("ok, I've marked this task as not done yet");
                System.out.println("[" + arrayL.get(tempi-1).getIsDone() + "] " + arrayL.get(tempi-1).description);
            }
            else {
                //System.out.println(userText);
                try {
                    if (!userText.equals("list") && !userText.equals("List") && !arr[0].equals("unmark") && !arr[0].equals("mark") && !arr[0].equals("delete")) {
                        if (arr.length > 1 && arr[0].equals("deadline")) {
                            String tempi = arr[1];
                            String arri[];
                            arri = tempi.split("/by");
                            String temp2 = arri[0];
                            String temp3 = arri[1];
                            Deadline d = new Deadline(temp2, temp3);
                            arrayL.add(d);
                            System.out.println("Added: " + d.toString());
                        }

                        if (arr.length > 1 && arr[0].equals("event")) {
                            String tempi = arr[1];
                            String arri[];
                            arri = tempi.split("/at");
                            String temp2 = arri[0];
                            String temp3 = arri[1];
                            Event e = new Event(temp2, temp3);
                            arrayL.add(e);
                            System.out.println("Added: " + e.toString());
                        }
                        if (arr.length > 1 && arr[0].equals("todo")) {
                            String tempi = arr[1];
                            ToDo td = new ToDo(tempi);
                            arrayL.add(td);
                            System.out.println("Added: " + td.toString());
                        }
                        if (arr.length == 1 && arr[0].equals("todo")) {
                            throw new NeoException("sorry todo cannot be empty");
                        }
                        if (!arr[0].equals("todo") && !arr[0].equals("event") && !arr[0].equals("deadline")) {
                            throw new NeoException("Sorry I don't know what that means");
                        }
                    }
                } catch (NeoException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}

