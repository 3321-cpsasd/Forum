package com.pix.forum.dao;

import com.pix.forum.entity.LoginTicket;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @Author pix
 * @Date 2023/4/29 15:54
 */
@Mapper
@Deprecated
public interface LoginTicketMapper {
    @Insert({
            "insert into login_ticket(user_id,ticket,status,expired) ",
            "values(#{userId},#{ticket},#{status},#{expired})"
    })
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertLoginTicket(LoginTicket loginTicket);
    @Select({
            "select id,user_id,ticket,status,expired ",
            "from login_ticket where ticket = #{ticket}"
    })
    LoginTicket selectByTicket(String ticket);
    @Update({
            "update login_ticket set status = #{status} where ticket=#{ticket}"
    })
    int updateStatus(String ticket,int status);

}
