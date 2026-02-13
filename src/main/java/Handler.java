import javax.swing.*;
import java.awt.*;

/**
 * An MQTT client publishing and subscribing to topics.
 * It publishes messages on two topics and listens to one.
 *
 * @author javiergs
 * @version 2.0
 */
public class Handler extends JFrame {
	
	private final static String BROKER_URL = "tcp://test.mosquitto.org:1883";
	private final static String[] TOPICS = {
		"threadsareawesome"
	};
	
	public static void main(String[] args) {

		// Start a subscriber thread
		/* Subscriber subscriber = new Subscriber(BROKER_URL, TOPICS);
		Thread threadSubscriber = new Thread(subscriber);
		threadSubscriber.start();*/
		// Start a publisher thread
		Publisher publisher = new Publisher(BROKER_URL, TOPICS, new Point(5,5));
		Thread threadPublisher = new Thread(publisher);
		threadPublisher.start();

		Handler app = new Handler(publisher);
		app.setSize(800, 800);
		app.setTitle("Concurrent Grid Pathfinder");
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setResizable(false);
		app.setVisible(true);
	}

	public Handler(Publisher pub){
		JPanel windowView = new WindowView(pub);

		JPanel top = new JPanel(new BorderLayout());
		setLayout(new BorderLayout());

		add(top, BorderLayout.NORTH);
		add(windowView, BorderLayout.CENTER);
	}
	
}