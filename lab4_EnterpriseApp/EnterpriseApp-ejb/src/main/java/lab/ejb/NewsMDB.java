
package lab.ejb;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@JMSDestinationDefinition(name = "java:app/jms/NewsQueue",
        interfaceName = "jakarta.jms.Queue", resourceAdapter = "jmsra",
        destinationName = "NewsQueue")
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName =
                "destinationLookup", propertyValue = "java:app/jms/NewsQueue"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "jakarta.jms.Queue")
})
public class NewsMDB implements MessageListener {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void onMessage(Message message) {
        ObjectMessage msg = null;
        try {
            if (message instanceof ObjectMessage) {
                msg = (ObjectMessage) message;
                NewsItem e = (NewsItem) msg.getObject();
                em.persist(e);
            }

            if (message instanceof TextMessage) {
                TextMessage tm = (TextMessage) message;
                NewsItem e = new NewsItem();
                e.setHeading(tm.getText().split("\\|")[0]);
                e.setBody(tm.getText().split("\\|")[1]);
                em.persist(e);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
