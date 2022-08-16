import java.util.Scanner;

public abstract class Chatbot {
    public abstract void echo(String message);
    public abstract void sayHello();
    public abstract void sayGoodbye();

    public abstract String listen(Scanner scanner);
}
