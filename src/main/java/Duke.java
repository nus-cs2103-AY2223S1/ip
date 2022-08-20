import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke What can I do for you?");
        ArrayList<Task> array = new ArrayList<>(100);
        int counter = 0;
        boolean flag = false;
        Scanner in = new Scanner(System.in);
        DukeOperations ops = new DukeOperations();


        File f = new File("data/duke.txt");

        while (!flag) {
            String output = in.nextLine();
            String arr[] = output.split(" ",2);
            String firstword = arr[0];

            if (output.equals("bye")) {
                System.out.println("_________________________________________________________________________");
                System.out.println("        Bye. Hope to see you again soon!");
                System.out.println("_________________________________________________________________________");
                flag = true;

            }
            else if(output.equals("list")) {
                ops.displayList(array,counter);

            }
            else if(firstword.equals("todo")) {
                if(!f.exists()) {
                    System.out.println("File doesnt exist yet");
                }
                try {
                    FileWriter fw = new FileWriter("data/duke.txt",true);
                    System.out.println("_________________________________________________________________________");
                    ops.todo(array, arr, counter);
                    System.out.println("_________________________________________________________________________");
                    fw.write(array.get(counter).toString() + System.lineSeparator());
                    fw.close();
                    counter++;
                }
                catch (DukeException e1) {
                    System.out.println(e1.toString());
                }

                catch(IOException e1) {
                    System.out.println(e1.toString());
                }

            }

            else if(firstword.equals("deadline")) {
<<<<<<< HEAD
                System.out.println("_________________________________________________________________________");
                String arr2[] = arr[1].split("/by ",2);
                String arr3[] = arr2[1].split(" ",2);
                ops.deadline(array,arr2[0],arr3[0],arr3[1],counter);
                System.out.println("_________________________________________________________________________");
                counter++;
            }
            else if(firstword.equals("event")) {
                System.out.println("_________________________________________________________________________");
                String arr2[] = arr[1].split("/at ",2);
                String arr3[] = arr2[1].split(" ",2);
                ops.event(array,arr2[0],arr3[0],arr3[1],counter);
                System.out.println("_________________________________________________________________________");
                counter++;
=======
                if(!f.exists()) {
                    System.out.println("File doesnt exist yet");
                }
                try {
                    FileWriter fw = new FileWriter("data/duke.txt",true);
                    System.out.println("_________________________________________________________________________");
                    String arr2[] = arr[1].split("/by", 2);
                    ops.deadline(array, arr2[0], arr2[1], counter);
                    System.out.println("_________________________________________________________________________");
                    fw.write(array.get(counter).toString() + System.lineSeparator());
                    fw.close();
                    counter++;
                }
                catch(IOException e1) {
                    System.out.println(e1.toString());
                }
            }
            else if(firstword.equals("event")) {
                if(!f.exists()) {
                    System.out.println("File doesnt exist yet");
                }
                try {
                    FileWriter fw = new FileWriter("data/duke.txt",true);
                    System.out.println("_________________________________________________________________________");
                    String arr2[] = arr[1].split("/at", 2);
                    ops.event(array, arr2[0], arr2[1], counter);
                    fw.write(array.get(counter).toString() + System.lineSeparator());
                    fw.close();
                    System.out.println("_________________________________________________________________________");
                    counter++;
                }
                catch(IOException e2) {
                    System.out.println(e2.toString());
                }
>>>>>>> branch-Level-7
            }

            else if(firstword.equals("mark")) {
                int num = Integer.parseInt(arr[1]);
                ops.mark(array,num);
            }
            else if(firstword.equals("unmark")) {
                int num = Integer.parseInt(arr[1]);
                ops.mark(array,num);
            }
            else if(firstword.equals("delete")) {
                System.out.println("_________________________________________________________________________");
                int num = Integer.parseInt(arr[1]);
                ops.delete(array,num,counter);
                counter--;
                System.out.println("_________________________________________________________________________");
            }
            else {
                try {
                    ops.randomword(output);
                }
                catch(DukeException e2) {
                    System.out.println("_________________________________________________________________________");
                    System.out.println(e2.toString());
                    System.out.println("_________________________________________________________________________");
                }
            }
        }
    }
}
