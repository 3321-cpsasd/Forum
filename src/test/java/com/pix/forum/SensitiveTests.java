package com.pix.forum;

import com.pix.forum.util.SensitiveFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SensitiveTests {

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void setSensitiveFilter(){

        String text = "这里可以赌博，可以↓嫖(⊙_⊙)娼↑，可以吸毒，可以开票，哈哈哈!";
        text = sensitiveFilter.filter(text);
        System.out.println(text);
    }
}
