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
}
