import edu.jpa_study.config.SpringDataConfiguration;
import edu.jpa_study.entities.Message;
import edu.jpa_study.repositories.MessageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringDataConfiguration.class})
public class HelloWorldSpringDataJPATest {

    @Autowired
    private MessageRepository messageRepository;

    @Test
    public void storeLoadMessage() {
        Message message = new Message();
        message.setText("Hello World");
        messageRepository.save(message);

        List<Message> messages = (List<Message>) messageRepository.findAll();
        messages.get(0).setText("Hello Spring Data!");
        messageRepository.save(messages.get(0));

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, messages.size()),
                () -> Assertions.assertEquals("Hello Spring Data!", messages.get(0).getText())
        );
    }
}
