package scottie.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import scottie.storage.Storage;

class AutoSavingTaskListTest {
    private static final String SAMPLE_TODO_DATA = "T|0|Todo Description";
    private static final String SAMPLE_DEADLINE_DATA = "D|1|Deadline Description|1/2/34 1234";
    private static final String SAMPLE_EVENT_DATA = "E|0|Event Description|5/6/78";
    private static final String SAMPLE_TASK_STUB_DATA = "T|0|Task Stub";
    private static final Task SAMPLE_TASK_STUB = new TaskStub("Task Stub", false);

    @Test
    void testIsEmpty() {
        assertTrue(new AutoSavingTaskList(new StorageMock(List.of())).isEmpty());
        assertFalse(new AutoSavingTaskList(new StorageMock(List.of(SAMPLE_TASK_STUB_DATA))).isEmpty());
    }

    @Test
    void testSize() {
        assertEquals(0, new AutoSavingTaskList(new StorageMock(List.of())).size());

        int correctSize = 123;
        List<String> taskDataList = new ArrayList<>();
        for (int i = 0; i < correctSize; i++) {
            taskDataList.add(SAMPLE_TODO_DATA);
        }
        assertEquals(correctSize, new AutoSavingTaskList(new StorageMock(taskDataList)).size());
    }

    @Test
    void testGetAndAddTask() {
        StorageMock storageMock = new StorageMock(List.of(SAMPLE_DEADLINE_DATA, SAMPLE_EVENT_DATA));
        TaskList taskList = new AutoSavingTaskList(storageMock);
        taskList.addTask(SAMPLE_TASK_STUB);

        assertSame(SAMPLE_TASK_STUB, taskList.getTask(2));
        assertEquals(SAMPLE_DEADLINE_DATA, storageMock.getSavedData(0));
        assertEquals(SAMPLE_EVENT_DATA, storageMock.getSavedData(1));
        assertEquals(SAMPLE_TASK_STUB_DATA, storageMock.getSavedData(2));
    }

    @Test
    void testDeleteTask() {
        StorageMock storageMock = new StorageMock(List.of(SAMPLE_DEADLINE_DATA, SAMPLE_EVENT_DATA));
        TaskList taskList = new AutoSavingTaskList(storageMock);
        assertFalse(taskList.isEmpty());
        assertEquals(2, taskList.size());

        taskList.deleteTask(0);
        assertFalse(taskList.isEmpty());
        assertEquals(1, taskList.size());
        assertEquals(SAMPLE_EVENT_DATA, storageMock.getSavedData(0));

        taskList.deleteTask(0);
        assertTrue(taskList.isEmpty());
        assertEquals(0, taskList.size());
        assertTrue(storageMock.isSavedDataEmpty());
    }

    @Test
    void testTaskMarking() {
        StorageMock storageMock = new StorageMock(List.of(SAMPLE_TASK_STUB_DATA));
        TaskList taskList = new AutoSavingTaskList(storageMock);
        assertFalse(taskList.isMarked(0));
        assertEquals(SAMPLE_TASK_STUB_DATA, storageMock.getSavedData(0));

        taskList.markTask(0);
        assertTrue(taskList.isMarked(0));
        assertEquals(SAMPLE_TASK_STUB_DATA.replace('0', '1'), storageMock.getSavedData(0));

        taskList.unmarkTask(0);
        assertFalse(taskList.isMarked(0));
        assertEquals(SAMPLE_TASK_STUB_DATA, storageMock.getSavedData(0));
    }

    @Test
    void testFilterTasks() {
        List<String> taskDatas =
                List.of(SAMPLE_TODO_DATA, SAMPLE_DEADLINE_DATA, SAMPLE_EVENT_DATA, SAMPLE_TASK_STUB_DATA);
        TaskList taskList = new AutoSavingTaskList(new StorageMock(taskDatas));
        assertEquals(3, taskList.filterTasks("deScrIPtiOn").size());
        assertEquals(2, taskList.filterTasks("a").size());
        assertEquals(1, taskList.filterTasks("TODO").size());
        assertEquals(0, taskList.filterTasks("None").size());
    }

    @Test
    void testSortByDescription() {
        List<String> taskDatas =
                List.of(SAMPLE_TODO_DATA, SAMPLE_DEADLINE_DATA, SAMPLE_EVENT_DATA, SAMPLE_TASK_STUB_DATA);
        List<String> taskDatasSorted =
                List.of(SAMPLE_DEADLINE_DATA, SAMPLE_EVENT_DATA, SAMPLE_TASK_STUB_DATA, SAMPLE_TODO_DATA);
        List<String> taskDatasReverseSorted =
                List.of(SAMPLE_TODO_DATA, SAMPLE_TASK_STUB_DATA, SAMPLE_EVENT_DATA, SAMPLE_DEADLINE_DATA);
        StorageMock storageMock = new StorageMock(taskDatas);
        TaskList taskList = new AutoSavingTaskList(storageMock);

        taskList.sortByDescription(false);
        for (int i = 0; i < 4; i++) {
            assertEquals(taskDatasSorted.get(i), storageMock.getSavedData(i));
        }

        taskList.sortByDescription(true);
        for (int i = 0; i < 4; i++) {
            assertEquals(taskDatasReverseSorted.get(i), storageMock.getSavedData(i));
        }
    }

    @Test
    void testSortByDate() {
        List<String> taskDatas =
                List.of(SAMPLE_TODO_DATA, SAMPLE_DEADLINE_DATA, SAMPLE_EVENT_DATA, SAMPLE_TASK_STUB_DATA);
        List<String> taskDatasSorted =
                List.of(SAMPLE_DEADLINE_DATA, SAMPLE_EVENT_DATA);
        List<String> taskDatasReverseSorted =
                List.of(SAMPLE_EVENT_DATA, SAMPLE_DEADLINE_DATA);
        StorageMock storageMock = new StorageMock(taskDatas);
        TaskList taskList = new AutoSavingTaskList(storageMock);

        taskList.sortByDate(false);
        for (int i = 0; i < 2; i++) {
            assertEquals(taskDatasSorted.get(i), storageMock.getSavedData(i));
        }

        taskList.sortByDate(true);
        for (int i = 0; i < 2; i++) {
            assertEquals(taskDatasReverseSorted.get(i), storageMock.getSavedData(i));
        }
    }

    private static class StorageMock implements Storage {
        private List<String> data;

        private StorageMock(List<String> initialData) {
            this.data = initialData;
        }

        @Override
        public List<String> loadData() {
            return List.copyOf(this.data);
        }

        @Override
        public void saveData(Iterable<String> lines) {
            this.data = new ArrayList<>();
            lines.forEach(this.data::add);
        }

        private boolean isSavedDataEmpty() {
            return this.data.isEmpty();
        }

        private String getSavedData(int index) {
            return this.data.get(index);
        }
    }

    private static class TaskStub extends Task {
        private TaskStub(String description, boolean isDone) {
            super(description, isDone);
        }

        @Override
        LocalDateTime getDateTime() {
            return null;
        }
        @Override
        String toEncodedString() {
            return "T|" + super.toEncodedString();
        }
    }
}
