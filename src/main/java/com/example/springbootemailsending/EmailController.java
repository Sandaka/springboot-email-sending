package com.example.springbootemailsending;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by Sandaka Wijesinghe.
 * Date: 8/14/21
 */
@RestController
public class EmailController {

    @Autowired
    private JavaMailSender sender;

    @RequestMapping("/sendMail")
    public String sendMail() {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo("demo@gmail.com");
            helper.setText("<html>\n" +
                    "<head>\n" +
                    "<style>\n" +
                    ".city {\n" +
                    "  background-color: tomato;\n" +
                    "  color: white;\n" +
                    "  border: 2px solid black;\n" +
                    "  margin: 20px;\n" +
                    "  padding: 20px;\n" +
                    "}\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "<div class=\"city\">\n" +
                    "<h2>London</h2>\n" +
                    "<p>London is the capital of England.</p>\n" +
                    "</div> \n" +
                    "\n" +
                    "<div class=\"city\">\n" +
                    "<h2>Paris</h2>\n" +
                    "<p>Paris is the capital of France.</p>\n" +
                    "</div>\n" +
                    "\n" +
                    "<div class=\"city\">\n" +
                    "<h2>Tokyo</h2>\n" +
                    "<p>Tokyo is the capital of Japan.</p>\n" +
                    "</div>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>");
            helper.setSubject("Spring Boot Test Application | Sandaka");
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error while sending mail ..";
        }
        sender.send(message);
        return "Mail Sent Success!";
    }

    @RequestMapping("/sendMailAtt")
    public String sendMailAttachment() throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        try {
            helper.setTo("demo@gmail.com");
            helper.setText("Greetings :)\n Please find the attached document for your reference.");
            helper.setSubject("Mail From Spring Boot");
            ClassPathResource file = new ClassPathResource("document.PNG");
            helper.addAttachment("document.PNG", file);
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error while sending mail ..";
        }
        sender.send(message);
        return "Mail Sent Success!";
    }
}
