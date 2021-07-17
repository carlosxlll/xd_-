import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import static java.lang.Thread.sleep;

public class Publisher {

    private static String brokerURL = "tcp://localhost:61616";
    private static ConnectionFactory factory;
    private Connection connection;
    private Session session;
    private MessageProducer producer;
	private Topic topic;
    
    public Publisher(String topicName) throws JMSException {
		
    	factory = new ActiveMQConnectionFactory(brokerURL);
    	connection = factory.createConnection();
        
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		topic = session.createTopic(topicName);
        producer = session.createProducer(topic);
		
		connection.start();
    }    
    
    public void close() throws JMSException {
        if (connection != null) {
            connection.close();
        }
    }    
    
	public static void main(String[] args) throws JMSException, InterruptedException {
    	Publisher publisher = new Publisher("MYTOPIC");
    	int times  = 1000;
    	int i =1;
    	while(times !=0){
    	    sleep(10L);
            publisher.sendMessage(i);
            i++;
            times--;
        }
        publisher.close();


	}
	
    public void sendMessage(int i) throws JMSException {
        double u = 100.0, v = 2.3;
        java.util.Random random = new java.util.Random();
        double value = v*random.nextGaussian()+u;
        String s = Double.toString(value);
        Message message = session.createTextMessage(s);
		producer.send(message);
		System.out.println("Sent a message" +" number: "+ Integer.toString(i));
    }


}
