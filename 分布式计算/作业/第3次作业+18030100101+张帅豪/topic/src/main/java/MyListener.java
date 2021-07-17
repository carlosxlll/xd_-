import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Message;
import javax.jms.TextMessage;

public class MyListener implements MessageListener {

	public void onMessage(Message message) {
		try {
			System.out.println("Received a message: "+((TextMessage)message).getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
