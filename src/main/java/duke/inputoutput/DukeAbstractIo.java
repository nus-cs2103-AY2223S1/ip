package duke.inputoutput;

import java.util.Arrays;
import java.util.List;

/**
 * Abstract class of DukeIo interface to handle some basic functionalities
 */
public abstract class DukeAbstractIo implements DukeIo {
    private static final String EMPTY_LIST = "I didn't find anything!";

    /**
     * {@inheritDoc}
     *
     * @param <U>
     * @param list
     */
    @Override
    public <U> void printNumberedList(List<U> list) {
        if (list.isEmpty()) {
            printTask(EMPTY_LIST);
            return;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            sb.append(String.format("%d. %s%n", i + 1, list.get(i).toString()));
        }
        printTask(sb.toString());
    }

    /**
     * {@inheritDoc}
     *
     * @param <U>
     * @param list
     */
    @Override
    public <U> void printNumberedList(U[] list) {
        if (list.length == 0) {
            printTask(EMPTY_LIST);
            return;
        }
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < list.length; i++) {
            sb.append(String.format("%d. %s%n", i + 1, list[i].toString()));
        }
        printTask(sb.toString());
    }

    /**
     * {@inheritDoc}
     *
     * @param <U>
     * @param list
     */
    @Override
    public <U> void printList(List<U> list) {
        if (list.isEmpty()) {
            printTask(EMPTY_LIST);
            return;
        }

        StringBuilder printableList = list.stream().collect(StringBuilder::new, (sb, txt) -> {
            sb.append(String.format(" - %s%n", txt.toString()));
        }, StringBuilder::append);

        printTask(printableList.toString());
    }

    /**
     * {@inheritDoc}
     *
     * @param <U>
     * @param list
     */
    @Override
    public <U> void printList(U[] list) {
        if (list.length == 0) {
            printTask(EMPTY_LIST);
            return;
        }
        StringBuilder printableList = Arrays.stream(list).collect(StringBuilder::new, (sb, txt) -> {
            sb.append(String.format(" - %s%n", txt.toString()));
        }, StringBuilder::append);

        printTask(printableList.toString());
    }

    @Override
    public void printTask(String txt, DukeCliSettings featuresEnum) {
        printTask(txt, featuresEnum.value);
    }

    /**
     * {@inheritDoc}
     *
     * @param e
     */
    @Override
    public void printError(Exception e) {
        printTask(String.format("🙄 OOPS!!! %s", e.getMessage()));
    }

    @Override
    public void printError(String msg) {
        printError(new Exception(msg));
    }

    protected boolean isBitFlag(int bitsValue, DukeCliSettings flagEnum) {
        return (bitsValue & flagEnum.value) == flagEnum.value;
    }
}
