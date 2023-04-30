package com.pix.forum.dao;

import com.pix.forum.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author pix
 * @Date 2023/4/30 18:43
 */
@Mapper
public interface CommentMapper {
    List<Comment> selectCommentsByEntity(int entityType, int entityId, int offset, int limit);

    int selectCommentByEntity(int entityType, int entityId);

    int insertComment(Comment comment);

    int selectPostCommentCountByUserId(int userId, int entityType);

    Comment selectCommentById(int id);
}
