import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        IO_handler.cout(IO_handler.get_greeting());
        event_dispatcher worker_1=new event_dispatcher(new calendar());
        worker_1.start_working();
    }
}
