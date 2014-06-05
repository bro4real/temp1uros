package com.b4r.learnmom;

import javax.jms.*;
import javax.naming.*;

public class MessageSender {

	public void sendMessage(String greeting) {

		Session session = null;
		ConnectionFactory factory = null;
		Connection connection = null;

		try {
			// Find the JNDI context
			Context jndiContext = new InitialContext();
			// Look up the factory and the queue
			factory = (ConnectionFactory) jndiContext
					.lookup("MyLearningConnectionFactory");
			Queue ioQueue = (Queue) jndiContext.lookup("MyLearningQueue");
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer queueSender = session.createProducer(ioQueue);
			// Salute a friend:
			TextMessage outMsg = session.createTextMessage(greeting);
			queueSender.send(outMsg);
			queueSender.close();
			System.out.println("Just added message to the Queue!");
		} catch (JMSException e) {
			System.out.println("error: " + e.getMessage());
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			try {
				session.close();
				connection.close();
			} catch (Exception e) {
				System.out.println("Can’t close JMS connection/session "
						+ e.getMessage());
			}
		}

	}

}
