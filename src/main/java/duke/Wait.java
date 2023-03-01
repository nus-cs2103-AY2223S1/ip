package duke;

public class Wait {

    public Wait(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            return;
        }
    }
}
