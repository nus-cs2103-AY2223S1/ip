import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        IO_handler.cout(IO_handler.get_greeting());
        Scanner sc=new Scanner(System.in);
        while (true){
            IO_handler.cout("> ");
            String command=sc.nextLine();
            if (command.equals("bye")){
                IO_handler.print_status_msg(0);
                break;
            }
            IO_handler.cout(IO_handler.generate_section(command+"\n"));

            /*
            int status=event_dispatcher.dispatch_command(IO_handler.get_command());
            IO_handler.print_status_msg(status);
            if (status==0){
                break;
            }*/
        }
    }
}
