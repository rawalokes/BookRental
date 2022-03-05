package com.infodev.bookrental.components;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Component;

/**
 * @author rawalokes
 * Date:3/4/22
 * Time:2:39 PM
 */
@Component
public class SendEmailComponents {
    public void sendEmail(String emailAdress)  {

        try{
            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("tryingdemo65@gmail.com","Helloworld@#12"));
            email.setSSLOnConnect(true);
            email.setFrom("tryingdemo65@gmail.com");
            email.setSubject("Registration");
            email.setMsg("congratulation you have registered as Author.");
            email.addTo(emailAdress);
            email.send();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error");


        }


//        Email email = new SimpleEmail();
//        email.setHostName("smtp.googlemail.com");
//        email.setSmtpPort(465);
//        email.setAuthenticator(new DefaultAuthenticator("username", "password"));
//        email.setSSLOnConnect(true);
//        email.setFrom("user@gmail.com");
//        email.setSubject("TestMail");
//        email.setMsg("This is a test mail ... :-)");
//        email.addTo("foo@bar.com");
//        email.send();
    }

}
