import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.ArrayList;
import java.util.Scanner;


class MyListener_aver_n implements MessageListener {

    double sum = 0;
    double aver = 0.0;
    int  n= 0;
    double var = 0.0;
    ArrayList<Double> lists = new ArrayList<Double>();
    MyListener_aver_n(int n){
        this.n = n;
    }
    @Override
    public void onMessage(Message message) {
        try {
            String s = ((TextMessage)message).getText();
            double value = Double.parseDouble(s);
            lists.add(value);
            sum = 0.0;
            aver = 0.0;
            var = 0.0;
            if(lists.size()>=n){
                for (int i = lists.size()-1; i >=lists.size()-n ; i--) {
                    sum += lists.get(i);
                }
                aver = sum/n;
                double sum_error = 0.0;
                for (int i = lists.size()-1; i >=lists.size()-n ; i--) {
                    sum_error += (aver - lists.get(i))  * (aver - lists.get(i));
                }
                var = sum_error/lists.size();
                System.out.println("  过去"+n+"个数字均值为" + aver + "  过去"+n+"个数字方差为" + var);
            }




        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

public class aver_n {

    public static void main(String[] args) throws JMSException {
        String brokerURL = "tcp://localhost:61616";
        ConnectionFactory factory = null;
        Connection connection = null;
        Session session = null;
        Topic topic = null;
        MessageConsumer messageConsumer = null;
        MyListener_aver_n listener = null;


        try {
            factory = new ActiveMQConnectionFactory(brokerURL);
            connection = factory.createConnection();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            topic = session.createTopic("MYTOPIC");

            messageConsumer = session.createConsumer(topic);



            System.out.println("请输入你要找的过去均值的数目：");
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            listener = new MyListener_aver_n(n);
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

