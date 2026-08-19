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

                if (input.equals("list")) {
                    bot.list();
                    continue;
                }

                if (input.equals("bye")) {
                    bot.exit();
                    break;
                }

                bot.add(input);
            }
        } finally {
            scanner.close();
        }
    }
}
