package duke.tasklist;

import java.io.IOException;
import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;
    
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }


    public void add(Task newTask) {
        this.tasks.add(newTask);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void remove(int item) {
        tasks.remove(item);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task newTask) throws IOException {
        tasks.add(newTask);
//        System.out.println("Got it. I've added this task: " + System.lineSeparator() + newTask.toString());
//        System.out.println("Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.");
//        storage.Save(tasks);
    }
}
