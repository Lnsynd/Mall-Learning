package com.lqs.mall.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 刘千山 on 2023/6/9/009-17:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UmsAdminLoginParam {
    private String username;
    private String password;


}
