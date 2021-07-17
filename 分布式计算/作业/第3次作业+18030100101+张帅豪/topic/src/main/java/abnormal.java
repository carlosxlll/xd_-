import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.ArrayList;


class MyListener_abnormal implements MessageListener {

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
            System.out.print("目前异常值：");
            for (double list : lists) {
                if(list>(aver+3*var) || list <(aver-3*var)){
                    System.out.print(list + " ");
                }
            }
            System.out.println();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

public class abnormal {

    public static void main(String[] args) throws JMSException {
        String brokerURL = "tcp://localhost:61616";
        ConnectionFactory factory = null;
        Connection connection = null;
        Session session = null;
        Topic topic = null;
        MessageConsumer messageConsumer = null;
        MyListener_abnormal listener = null;


        try {
            factory = new ActiveMQConnectionFactory(brokerURL);
            connection = factory.createConnection();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            topic = session.createTopic("MYTOPIC");

            messageConsumer = session.createConsumer(topic);



            System.out.println("目前异常值：");
            listener = new MyListener_abnormal();
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

