package com.pix.forum.service;

import com.pix.forum.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

/**
 * @Author pix
 * @Date 2023/5/1 18:52
 */
@Service
public class LikeService {
    @Autowired
    private RedisTemplate redisTemplate;

    public void like(int userId,int entityType,int entityId,int entityUserId){
//        String entityLikeKey = RedisKeyUtil.getEntityLike(entityType,entityId);
//        Boolean isMember = redisTemplate.opsForSet().isMember(entityLikeKey, userId);
//        if(isMember){
//            redisTemplate.opsForSet().remove(entityLikeKey,userId);
//        }else{
//            redisTemplate.opsForSet().add(entityLikeKey,userId);
//        }
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType,entityId);
                String userLikerKey = RedisKeyUtil.getUserLikeKey(entityUserId);

                Boolean isMember = operations.opsForSet().isMember(entityLikeKey,userId);
                operations.multi();
                if(isMember){
                    operations.opsForSet().remove(entityLikeKey,userId);
                    operations.opsForValue().decrement(userLikerKey);
                }else{
                    operations.opsForSet().add(entityLikeKey,userId);
                    operations.opsForValue().increment(userLikerKey);
                }
                
                return operations.exec();
            }
        });

    }

    //查询实体点赞的数量
    public long findEntityLikeCount(int entityType,int entityId){
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType,entityId);
        return redisTemplate.opsForSet().size(entityLikeKey);
    }

    //查询某人对某实体点赞状态
    public int findEntityLikeStatus(int userId,int entityType,int entityId){
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType,entityId);
        return redisTemplate.opsForSet().isMember(entityLikeKey, userId)?1:0;
    }
    
    public int findUserLikeCount(int UserId){
        String userLikeKey = RedisKeyUtil.getUserLikeKey(UserId);
        Integer count = (Integer)redisTemplate.opsForValue().get(userLikeKey);
        return count == null?0:count.intValue();
    }

}
