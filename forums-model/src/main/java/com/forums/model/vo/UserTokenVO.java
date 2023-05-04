package com.forums.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auther 尚智江
 * @Date 2023/4/17 22:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTokenVO {
    private String uid;
    /** 用户名 */
    private String username;

    /** 用户邮箱 */
    private String email;

    /** 用户头像链接 */
    private String headImage;

    /** token */
    private String token;
}
