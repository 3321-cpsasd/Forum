package com.pix.forum.util;

/**
 * @Author pix
 * @Date 2023/5/1 18:46
 */
public class RedisKeyUtil {
    private static final String SPLIT = ":";

    private static final String PREFIX_USER_LIKE = "like:user";
    private static final String PREFIX_ENTITY_LIKE = "like:entity";
    private static final String PREFIX_FOLLOWEE = "followee";
    private static final String PREFIX_FOLLOWER = "follower";
    private static final String PREFIX_KAPTCHA = "kaptcha";
    private static final String PREFIX_TICKET = "ticket";
    private static final String PREFIX_USER = "user";
    public static String getEntityLikeKey(int entityType,int entityId){
         return PREFIX_ENTITY_LIKE + SPLIT + entityType + entityId;
    }
    public static String getUserLikeKey(int userId){
        return PREFIX_USER_LIKE + SPLIT + userId;
    }
    public static String getFolloweeKey(int userId, int entityType){
        return PREFIX_FOLLOWEE+SPLIT+userId+SPLIT+entityType;
    }

    //某个用户拥有的粉丝
    //follower:entityType:entityId->zset(userId,nowTime)
    public static String getFollowerKey(int entityType, int entityId){
        return PREFIX_FOLLOWER+SPLIT+entityType+SPLIT+entityId;
    }

    //验证码key
    public static String getKaptchaKey(String owner){
        return PREFIX_KAPTCHA+SPLIT+owner;
    }

    //登录凭证key
    public static String getTicketKey(String ticket){
        return PREFIX_TICKET+SPLIT+ticket;
    }

    //用户key
    public static String getUserKey(int userId){
        return PREFIX_USER+SPLIT+userId;
    }
}
