package com.library.user.rabbit;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.library.user.valueObject.BookRentInformation;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang.SerializationUtils;

public class Sender {

	private final static String QUEUE_NAME = "messagequeue";

	public String sendMessage(BookRentInformation bookRentInformation) throws IOException, TimeoutException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			byte[] data = SerializationUtils.serialize(bookRentInformation);
			channel.basicPublish("", QUEUE_NAME, null, data);
			System.out.println(" [x] Sent '" + bookRentInformation.getBookId() + "'");
			return " [x] Sent '" + bookRentInformation.getBookId() + "'";
		}

	}

}
