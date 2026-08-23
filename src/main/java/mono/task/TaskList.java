package mono.task;

import mono.exception.NonExistentException;
import mono.storage.Storage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Owns Mono's task collection and all operations that change or display it.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private final Storage storage;

    /**
     * Creates a task list initialized from persisted tasks.
     */
    public TaskList() {
        this.storage = new Storage();
        this.tasks = new ArrayList<>(Arrays.asList(this.storage.readStorage()));
    }

    /**
     * Adds and persists a task.
     *
     * @param task task to add
     */
    public Task addTask(Task task) {
        this.tasks.add(task);
        saveTasks();
        return task;
    }

    /**
     * Deletes a task using a one-based ID.
     *
     * @param id one-based task ID
     * @throws NonExistentException if the ID is invalid
     */
    public Task delete(int id) throws NonExistentException {
        validateTaskId(id);
        Task task = this.tasks.remove(id - 1);
        saveTasks();
        return task;
    }

    /**
     * Prints the current tasks with one-based IDs.
     */
    public List<Task> getTasks() {
        return List.copyOf(this.tasks);
    }

    /**
     * Marks a task as completed and persists the change.
     *
     * @param id one-based task ID
     * @throws NonExistentException if the ID is invalid
     */
    public Task markTaskDone(int id) throws NonExistentException {
        validateTaskId(id);
        Task task = this.tasks.get(id - 1);
        task.markDone();
        saveTasks();
        return task;
    }

    /**
     * Marks a task as incomplete and persists the change.
     *
     * @param id one-based task ID
     * @throws NonExistentException if the ID is invalid
     */
    public Task unmarkTaskDone(int id) throws NonExistentException {
        validateTaskId(id);
        Task task = this.tasks.get(id - 1);
        task.unmarkDone();
        saveTasks();
        return task;
    }

    /**
     * Checks a one-based task ID.
     *
     * @param id one-based task ID
     * @throws NonExistentException if no task has that ID
     */
    private void validateTaskId(int id) throws NonExistentException {
        if (id < 1 || id > this.tasks.size()) {
            throw new NonExistentException("Task " + id + " does not exist.");
        }
    }

    /**
     * Persists the complete current task collection.
     */
    private void saveTasks() {
        this.storage.saveTasks(this.tasks.toArray(new Task[0]));
    }
}
