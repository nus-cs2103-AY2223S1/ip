import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        //Initialize Scanner and ToDoList
        ToDoList list = new ToDoList();


        try {
            FileReader fr = new FileReader(list);
            fr.read();
        } catch (FileNotFoundException e) {
            System.out.print(e.getMessage());
        }

        Scanner sc = new Scanner(System.in);

        //Welcome message
        String logo = "*\\(^o^)/*";
        System.out.println(logo);
        System.out.println("Bonjour~~ I'm Byu! How can I help you?");

        String response = sc.nextLine();

        while (!response.equals("bye")) {
            String[] arr = response.split(" ");
            try {
                Instruction instruct = Instruction.valueOf(arr[0].toUpperCase(Locale.ROOT));
                list.execute(instruct, response);
            } catch (IllegalArgumentException e) {
                DukeException de = new InvalidInstruction();
                System.out.println(de.getMessage());
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                response = sc.nextLine();
            }
        }


        list.save();

        System.out.print("Awww see you soon!!");
        sc.close();
    }

}


