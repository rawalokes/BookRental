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
    public void sendEmail(String emailAddress, String role, String name)  {
        try{
            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("tryingdemo65@gmail.com","Helloworld@#12"));
            email.setSSLOnConnect(true);
            email.setFrom("tryingdemo65@gmail.com");
            email.setSubject("Registration");
            email.setMsg("Hi "+ name +" ,"+"\n Congratulation you have registered as "+ role);
            email.addTo(emailAddress);
            email.send();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error");


        }

    }

}
