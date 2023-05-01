package com.pix.forum.util;

/**
 * @Author pix
 * @Date 2023/4/29 14:20
 */
public interface CommunityConstant {
     final int ACTIVATION_SUCCESS = 0;
     final int ACTIVATION_REPEAT = 1;
     final int ACTIVATION_FAILURE = 2;

     /*
     * 默认状态的超时时间
     * */
     final int DEFAULT_EXPIRED_SECONDS = 3600 * 12;

     /*
      *记录状态下的凭证超时时间
      *  */
     final int REMEMBER_EXPIRED_SECONDS = 3600 * 24*7;

     /**
      * 实体类型:帖子
      */
     int ENTITY_TYPE_POST = 1;

     /**
      * 实体类型:帖子
      */
     int ENTITY_TYPE_COMMENT = 2;
     /**
      * 主题: 评论
      */
     String TOPIC_COMMENT = "comment";

     /**
      * 主题: 点赞
      */
     String TOPIC_LIKE = "like";

     /**
      * 主题: 关注
      */
     String TOPIC_FOLLOW = "follow";

     /**
      * 主题：发帖
      */
     String TOPIC_PUBLISH = "publish";

     /**
      * 主题：分享
      */
     String TOPIC_SHARE = "share";

     /**
      * 主题: 删帖
      */
     String TOPIC_DELETE = "delete";

     /**
      * 系统用户ID
      */
     int SYSTEM_USER_ID = 1;

     /**
      * 权限: 普通用户
      */
     String AUTHORITY_USER = "user";

     /**
      * 权限: 管理员
      */
     String AUTHORITY_ADMIN = "admin";

     /**
      * 权限: 版主
      */
     String AUTHORITY_MODERATOR = "moderator";

}
