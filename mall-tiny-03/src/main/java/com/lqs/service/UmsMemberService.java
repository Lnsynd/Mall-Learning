package com.lqs.service;

import com.lqs.common.CommonResult;

/**
 * Created by 刘千山 on 2023/6/2/002-17:39
 */
public interface UmsMemberService {
    CommonResult generateAuthCode(String telephone);

    CommonResult verifyAuthCode(String telephone, String authCode);
}
