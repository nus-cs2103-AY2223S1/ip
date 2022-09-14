package duke.inputoutput;

import java.util.List;

/**
 * Abstract class of DukeIo interface to handle some basic functionalities
 */
public abstract class DukeAbstractIo implements DukeIo {
    private static final String EMPTY_LIST = "The current list is empty!";

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
    public <U> void printList(U[] list) {
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
}
