package com.commons.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
/**
 * 发送邮件工具类
 * @author csxx_wmw
 *
 */
public class MailSendUtil {

	public static void sendMail(JavaMailSenderImpl mailSender,String toUser,String subject,String content){
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setFrom(mailSender.getUsername());
		simpleMessage.setTo(toUser);
		simpleMessage.setSubject(subject);
		simpleMessage.setText(content);
		mailSender.send(simpleMessage);
	}
	
	public static void sendMail(String toUser,String subject,String content){
		JavaMailSenderImpl mailSender = getBySpring();
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setFrom(mailSender.getUsername());
		simpleMessage.setTo(toUser);
		simpleMessage.setSubject(subject);
		simpleMessage.setText(content);
		mailSender.send(simpleMessage);
	}
	
	public static void sendMail(JavaMailSenderImpl mailSender,SimpleMailMessage[] messages){
		for (SimpleMailMessage simpleMailMessage : messages) {
			simpleMailMessage.setFrom(mailSender.getUsername());
		}
		mailSender.send(messages);
	}
	
	public static JavaMailSenderImpl getBySpring(){
		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
		return (JavaMailSenderImpl) wac.getBean("mailSender");
	}
}
