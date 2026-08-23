package mono.ui;

import mono.task.Task;

import java.util.List;

/**
 * Formats and prints Mono's conversation responses.
 */
public class ConversationUi {
    /** Prints Mono's greeting. */
    public void showGreeting() {
        String banner = "███╗   ███╗ ██████╗ ███╗   ██╗ ██████╗ \n" +
                "████╗ ████║██╔═══██╗████╗  ██║██╔═══██╗\n" +
                "██╔████╔██║██║   ██║██╔██╗ ██║██║   ██║\n" +
                "██║╚██╔╝██║██║   ██║██║╚██╗██║██║   ██║\n" +
                "██║ ╚═╝ ██║╚██████╔╝██║ ╚████║╚██████╔╝\n" +
                "╚═╝     ╚═╝ ╚═════╝ ╚═╝  ╚═══╝ ╚═════╝ ";
        System.out.print("____________________________________________________________\n" +
                banner + "\nHello! I'm Mono.\nWhat can I do for you?\n" +
                "____________________________________________________________\n");
    }

    /** Prints Mono's farewell. */
    public void showExit() {
        System.out.print("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

    /** Prints the response for an added task. */
    public void showTaskAdded(Task task, int count) {
        System.out.print("____________________________________________________________\n" +
                "Got it. I've added this task:\n" + task + "\nNow you have " + count +
                " tasks in the list.\n____________________________________________________________\n");
    }

    /** Prints the response for a deleted task. */
    public void showTaskDeleted(Task task, int count) {
        System.out.print("____________________________________________________________\n" +
                "Noted. I've removed this task:\n" + task + "\nNow you have " + count +
                " tasks in the list.\n____________________________________________________________\n");
    }

    /** Prints all tasks with one-based IDs. */
    public void showTaskList(List<Task> tasks) {
        System.out.print("____________________________________________________________\n" +
                "Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    /** Prints the response for marking a task done. */
    public void showTaskMarkedDone(Task task) {
        System.out.print("____________________________________________________________\n" +
                "Nice! I've marked this task as done:\n" + task +
                "\n____________________________________________________________\n");
    }

    /** Prints the response for unmarking a task. */
    public void showTaskUnmarked(Task task) {
        System.out.print("____________________________________________________________\n" +
                "OK, I've marked this task as not done yet:\n" + task +
                "\n____________________________________________________________\n");
    }
}
