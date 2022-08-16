import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        event_dispatcher worker_1=new event_dispatcher(new calendar(), new IO_handler());
        worker_1.start_working();
    }
}
