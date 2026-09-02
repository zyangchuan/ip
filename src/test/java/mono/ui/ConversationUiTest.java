package mono.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import mono.task.ToDo;

/**
 * Tests that conversation responses can be sent to a non-console destination.
 */
public class ConversationUiTest {
    @Test
    public void showGreeting_sendsOneCompleteResponse() {
        ArrayList<String> responses = new ArrayList<>();
        ConversationUi ui = new ConversationUi(responses::add);

        ui.showGreeting();

        assertEquals(1, responses.size());
        assertTrue(responses.get(0).contains("Hello! I'm Mono."));
    }

    @Test
    public void showTaskList_sendsTasksInOneResponse() {
        ArrayList<String> responses = new ArrayList<>();
        ConversationUi ui = new ConversationUi(responses::add);

        ui.showTaskList(java.util.List.of(new ToDo("read a book")));

        assertEquals(1, responses.size());
        assertTrue(responses.get(0).contains("1.[T][ ] read a book"));
    }
}
