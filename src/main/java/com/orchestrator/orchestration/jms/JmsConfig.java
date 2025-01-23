//package com.orchestrator.orchestration.jms;
//
//import com.orchestrator.orchestration.objects.vms.PlaylistEndJobVM;
//import jakarta.jms.ConnectionFactory;
//import jakarta.jms.JMSException;
//import jakarta.jms.Message;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.annotation.EnableJms;
//import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
//import org.springframework.jms.support.converter.MessageConversionException;
//import org.springframework.jms.support.converter.MessageConverter;
//import org.springframework.jms.support.converter.MessageType;
//
//import java.util.Map;
//
//@Slf4j
//@Configuration
//@EnableJms
//public class JmsConfig {
//    @Value("${spring.activemq.broker-url}")
//    private String brokerUrl;
//
//    @Value("${spring.activemq.user}")
//    private String user;
//
//    @Value("${spring.activemq.password}")
//    private String password;
//
//    @Bean
//    public ActiveMQConnectionFactory connectionFactory() {
//        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerUrl);
//        factory.setUserName(user);
//        factory.setPassword(password);
//        return factory;
//    }
//
//
//    @Bean
//    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory,
//                                                                          MessageConverter messageConverter) {
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter(messageConverter);
//        return factory;
//    }
//
//    @Bean
//    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
//        JmsTemplate jmsTemplate = new JmsTemplate();
//        jmsTemplate.setConnectionFactory(connectionFactory);
//        jmsTemplate.setMessageConverter(messageConverter);
//        return jmsTemplate;
//    }
//
//    @Bean
//    public MessageConverter jacksonJmsMessageConverter() {
//        return new CustomMappingJackson2MessageConverter();
//    }
//
//    public static class CustomMappingJackson2MessageConverter extends MappingJackson2MessageConverter {
//        public CustomMappingJackson2MessageConverter() {
//            setTargetType(MessageType.TEXT);
//            setTypeIdPropertyName("_type");
//            setTypeIdMappings(Map.of("playlistEndJob", PlaylistEndJobVM.class));
//        }
//
//        @Override
//        public Object fromMessage(Message message) throws JMSException, MessageConversionException {
//            String typeId = message.getStringProperty("_type");
//            if (typeId == null) {
//                log.info("No type id in message");
//                return super.fromMessage(message);
//            }
//            return super.fromMessage(message);
//        }
//    }
//}