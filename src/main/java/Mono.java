import mono.MonoBot;
import java.util.Scanner;

public class Mono {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MonoBot bot = new MonoBot();

        bot.greet();

        try {
            while (true) {
                String input = "";
                input = scanner.nextLine();

                if (input.equals("bye")) {
                    bot.exit();
                    break;
                }

                if (input.equals("list")) {
                    bot.list();
                } else if (input.startsWith("mark")) {
                    int id = Integer.parseInt(input.substring(5));
                    bot.markTaskDone(id);
                } else if (input.startsWith("unmark")) {
                    int id = Integer.parseInt(input.substring(7));
                    bot.unmarkTaskDone(id);
                } else {
                    bot.add(input);
                }
            }
        } finally {
            scanner.close();
        }
    }
}
