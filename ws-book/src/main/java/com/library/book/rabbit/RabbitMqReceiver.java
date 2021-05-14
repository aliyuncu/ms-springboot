package com.library.book.rabbit;

import com.library.book.entity.Book;
import com.library.book.service.BookService;
import com.library.book.valueObject.BookRentInformation;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class RabbitMqReceiver implements RabbitListenerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqReceiver.class);
    
    @Autowired
	private BookService bookService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(BookRentInformation bookRentInformation) {

        logger.info("User Details Received is.. " + bookRentInformation.getUserId());
        Book book = bookService.findByBookId(bookRentInformation.getBookId());
        book.setUserId(bookRentInformation.getUserId());
        bookService.saveBook(book);
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}