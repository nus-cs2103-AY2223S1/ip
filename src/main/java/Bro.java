import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Bro {

    static ArrayList<Task> list1 = new ArrayList<>();
    static FileOp fileOperator = new FileOp("./Bro.txt");

    public static void main(String[] args) {
        try {
            list1 = fileOperator.createFile(list1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Hello! I'm THE BRO\n" + "What can I do for you?");
        Bro d = new Bro();
        Scanner sc= new Scanner(System.in);
        d.output1(sc);

    }
    public void output1(Scanner sc){
        String str= sc.nextLine();

        if(str.equals("bye")){
            System.out.println("Bye. See you later broo!");
        }

        else if(str.equals("list")){
            int count = 1;
            for(Task x: list1){
                System.out.println(count + "." + x.toString());
                count++;
            }
            this.output1(sc);
        }

        else if(str.startsWith("mark ")){
            int k = Integer.parseInt(str.substring(5));
            list1.get(k - 1).markAsDone();
            try {
                fileOperator.modifyTaskFile(list1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("I have marked this task\n" + (list1.get(k-1)).toString());
            System.out.println("You have " + list1.size() + " tasks left!");
            this.output1(sc);
        }

        else if(str.startsWith("unmark ")){
            int k = Integer.parseInt(str.substring(7));
            list1.get(k - 1).markAsNotDone();
            try {
                fileOperator.modifyTaskFile(list1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("I have unmarked this task\n" + (list1.get(k-1)).toString());
            System.out.println("You have " + list1.size() + " tasks left!");
            this.output1(sc);
        }

        else if(str.startsWith("todo ")){
            Task t = new Todo(str.substring(5));
            t.markAsNotDone();
            list1.add(t);
            try {
                fileOperator.writeToFile(t);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(t.toString());
            System.out.println("You have " + list1.size() + " tasks left!");
            this.output1(sc);
        }

        else if(str.startsWith("deadline ")){
            Task t = new Deadline(str.substring(9, str.indexOf("by")-1), str.substring(str.indexOf("by") + 2));
            t.markAsNotDone();
            list1.add(t);
            try {
                fileOperator.writeToFile(t);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(t.toString());
            System.out.println("You have " + list1.size() + " tasks left!");
            this.output1(sc);
        }

        else if(str.startsWith("event ")){
            Task t = new Event(str.substring(6, str.indexOf("at")-1), str.substring(str.indexOf("at") + 2));
            t.markAsNotDone();
            list1.add(t);
            try {
                fileOperator.writeToFile(t);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(t.toString());
            System.out.println("You have " + list1.size() + " tasks left!");
            this.output1(sc);
        }

        else if(str.startsWith("delete")){
            int g = Integer.parseInt(str.substring(7));
            System.out.println("I have removed this task.\n" + (list1.get(g-1)).toString());
            list1.remove(g-1);
            try {
                fileOperator.modifyTaskFile(list1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("You have " + list1.size() + " tasks left!");
            this.output1(sc);
        }

        else{
            try {
                checkEmptyInput(str);
            } catch (BroException e) {
                System.out.println(e.getMessage());
            }
            this.output1(sc);
        }
    }

    public static void checkEmptyInput(String str) throws BroException {
        String[] li = new String[]{"todo", "deadline", "event", "mark", "unmark"};
        List<String> checkList = new ArrayList<>(Arrays.asList(li));
        if (checkList.contains(str)) {
            throw new BroException("The description cannot be empty.");
        } else {
            throw new BroException("I don't know what it means");
        }
    }
}
