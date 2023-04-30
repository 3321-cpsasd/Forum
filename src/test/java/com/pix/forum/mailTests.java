package com.pix.forum;

/**
 * @Author pix
 * @Date 2023/4/28 22:35
 */
import com.pix.forum.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.xml.transform.Templates;

@SpringBootTest
public class mailTests {
    private MailClient mailClient;

    @Autowired
    public mailTests(MailClient mailClient) {
        this.mailClient = mailClient;
    }

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testTextMail(){
        mailClient.sendMail("1196499401@qq.com","Test","Hello");
    }

    @Test
    public void testHtmlMail(){
        Context context = new Context();
        context.setVariable("username", "ddd");

        String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);

        mailClient.sendMail("1196499401@qq.com", "HTML", content);

    }

}
