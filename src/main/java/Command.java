import java.io.IOException;

abstract class Command {
    abstract void complete(String temp) throws NeoException, IOException;
}