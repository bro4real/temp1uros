package com.b4r.learnmom;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MessageReceiver implements MessageListener{

	Session session = null;
	ConnectionFactory factory;
	Connection connection = null;
	MessageConsumer consumer = null;

	MessageReceiver(){
		try {
			
			Context jndiContext = new InitialContext();
			// Look up the factory and the queue
			factory = (ConnectionFactory) jndiContext.lookup("MyLearningConnectionFactory");
			connection = factory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue ioQueue = (Queue) jndiContext.lookup("MyLearningQueue");
			consumer = session.createConsumer(ioQueue);
			consumer.setMessageListener(this);
			System.out.println("Receiver ready.");
			// Don’t finish - wait 10 sec. for messages
			/*Thread.sleep(10000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());*/
		} catch (JMSException e) {
			System.out.println(e.getMessage());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			System.out.println("naming ex: " + e.getMessage());
			
		} /*finally {
			try {
				connection.close();
			} catch (Exception e) {
				System.out.println("Can’t close JMS connection/session "
						+ e.getMessage());
			}
		}*/
	}

	public void onMessage(Message msg) {
		String msgText;
		try {
			if (msg instanceof TextMessage) {
				msgText = ((TextMessage) msg).getText();
				System.out.println("just got from the queue: " + msgText);
			} else {
				System.out.println("cant handle non text msg");
			}
		} catch (JMSException e) {
			System.out.println("Error while consuming a message: "
					+ e.getMessage());
		}
	}

}
