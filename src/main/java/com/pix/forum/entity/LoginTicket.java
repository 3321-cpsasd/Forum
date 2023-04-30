package com.pix.forum.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author pix
 * @Date 2023/4/29 15:51
 */
@Data
public class LoginTicket {
    private int id;
    private int userId;
    private String ticket;
    private int status;
    private Date expired;
}
