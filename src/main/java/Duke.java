import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //each worker is attached to a calendar table to work on and an IO_handler to handle IO
        //this design would allow the program to handle multi-user with multi-input-source case (with multi-thread)
        //the IO_handler can be binded to a specific InputStream and a specific PrintStream (default to be stdin & stdout)
        event_dispatcher worker_1=new event_dispatcher(new calendar(), new IO_handler());
        worker_1.start_working();
    }
}
