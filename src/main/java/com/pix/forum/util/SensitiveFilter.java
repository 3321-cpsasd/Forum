package com.pix.forum.util;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author pix
 * @Date 2023/4/30 14:44
 */
@Component
@Slf4j
public class SensitiveFilter {
    //替换符
    private static final String REPLACEMENT = "***";

    private TrieNode rootNode = new TrieNode();

    @PostConstruct
    public void init(){
        try(InputStream is = this.getClass().getClassLoader().getResourceAsStream("sensitive-words.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            ) {
            String keyword;
            while((keyword = bufferedReader.readLine()) != null){
                this.addKeyword(keyword);
            }
        }catch (IOException e){
            log.error("敏感词文件加载失败：" + e.getMessage());
        }
    }

    private void addKeyword(String keyword) {
        TrieNode tmpNode = rootNode;
        for(int i = 0;i<keyword.length();i++){
            char c = keyword.charAt(i);
            TrieNode subNode = tmpNode.getSubNode(c);
            if(subNode == null){
                subNode = new TrieNode();
                tmpNode.addSubNode(c,subNode);
            }
            tmpNode = subNode;

            if(i == keyword.length()-1){
                tmpNode.setKeyWordEnd(true);
            }
        }
    }

    public String filter(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        }

        TrieNode tempNode = rootNode;
        int begin = 0;
        int position = 0;

        // 返回的结果
        StringBuilder sb = new StringBuilder();

        while (begin < text.length()) {
            if (position < text.length()) {
                char c = text.charAt(position);

                // 跳过符号
                if (isSymbol(c)) {
                    if (tempNode == rootNode) {
                        sb.append(c);
                        ++begin;
                    }
                    ++position;
                    continue;
                }

                // 检查下级节点
                tempNode = tempNode.getSubNode(c);

                if (tempNode == null) {
                    sb.append(text.charAt(begin));
                    position = ++begin;
                    tempNode = rootNode;
                } else if (tempNode.isKeywordEnd()) {
                    sb.append(REPLACEMENT);
                    begin = ++position;
                    tempNode = rootNode;
                } else {
                    ++position;
                }
            } else {

                sb.append(text.charAt(begin));
                position = ++begin;
                tempNode = rootNode;
            }
        }

        return sb.toString();
    }

    private boolean isSymbol(Character c){
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF);
    }


    private class TrieNode{
        //guanjiancijieshudebiaoshi
        private boolean isKeyWordEnd = false;
        //子节点
        private Map<Character,TrieNode> subNodes = new HashMap<>();

        public TrieNode() {
        }

        public boolean isKeywordEnd() {
            return isKeyWordEnd;
        }

        public void setKeyWordEnd(boolean keyWordEnd) {
            isKeyWordEnd = keyWordEnd;
        }

        public void addSubNode(Character key,TrieNode value){
            subNodes.put(key,value);
        }

        public TrieNode getSubNode(Character key){
            return subNodes.get(key);
        }
    }
}
