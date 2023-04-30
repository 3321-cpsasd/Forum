package com.pix.forum.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author pix
 * @Date 2023/4/29 13:19
 */
public class CommunityUtil {

    //生成激活码，随机字符串
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    //MD5
    //
    public static String md5(String key){
        if(StringUtils.isBlank(key)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

    public static String getJsonString(int code, String msg, Map<String,Object> map){
        JSONObject json = new JSONObject();
        json.put("code",code);
        json.put("msg",msg);
        if(map != null){
            for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
                json.put(stringObjectEntry.getKey(),stringObjectEntry.getValue());
            }
        }
        return json.toJSONString();
    }

    public static String getJsonString(int code, Map<String,Object> map){
        JSONObject json = new JSONObject();
        json.put("code",code);
        if(map != null){
            for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
                json.put(stringObjectEntry.getKey(),stringObjectEntry.getValue());
            }
        }
        return json.toJSONString();
    }

    public static String getJsonString(int code, String msg){
        JSONObject json = new JSONObject();
        json.put("code",code);
        json.put("msg",msg);
        return json.toJSONString();
    }

    public static String getJsonString(int code){
        JSONObject json = new JSONObject();
        json.put("code",code);
        return json.toJSONString();
    }
}
