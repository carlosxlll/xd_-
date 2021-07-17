import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.ArrayList;


class MyListener_aver implements MessageListener {

    double sum = 0;
    double aver = 0.0;
    double var = 0.0;
    ArrayList<Double> lists = new ArrayList<Double>();
    @Override
    public void onMessage(Message message) {
        try {
            String s = ((TextMessage)message).getText();
            double value = Double.parseDouble(s);
            lists.add(value);
            sum = 0.0;
            var = 0.0;
            aver = 0.0;
            for (double list : lists) {
                sum += list;
            }
            aver = sum/lists.size();
            double sum_error = 0.0;
            for (double list : lists) {
                sum_error += (aver - list)  * (aver - list);
            }
            var = Math.sqrt(sum_error/lists.size());
            System.out.println("  当前"+lists.size()+"个数字均值为" + aver + " 标准差为：" + var);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

public class average {

    public static void main(String[] args) throws JMSException {
        String brokerURL = "tcp://localhost:61616";
        ConnectionFactory factory = null;
        Connection connection = null;
        Session session = null;
        Topic topic = null;
        MessageConsumer messageConsumer = null;
        MyListener_aver listener = null;


        try {
            factory = new ActiveMQConnectionFactory(brokerURL);
            connection = factory.createConnection();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            topic = session.createTopic("MYTOPIC");

            messageConsumer = session.createConsumer(topic);



            System.out.println("当前所有数字的均值为：");
            listener = new MyListener_aver();
            messageConsumer.setMessageListener(listener);

            connection.start();

            System.out.println("Press any key to exit.");
            System.in.read();   // Pause
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}

