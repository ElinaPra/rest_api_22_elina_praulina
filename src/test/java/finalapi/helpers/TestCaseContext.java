package finalapi.helpers;

import finalapi.domain.Folder;
import finalapi.domain.List;
import finalapi.domain.Task;

public class TestCaseContext {

    private static Folder folder;

    private static Task task;

    private static List list;

    public static void init() {
        folder = null;
        task = null;
        list = null;
    }

    public static void setFolder(Folder folder) {
        TestCaseContext.folder = folder;
    }

    public static Folder getFolder() {
        return folder;
    }

    public static void setList(List list) {
        TestCaseContext.list = list;
    }

    public static List getList() {
        return list;
    }

    public static void setTask(Task task) { TestCaseContext.task = task; }

    public static Task getTask() { return task;}
}
