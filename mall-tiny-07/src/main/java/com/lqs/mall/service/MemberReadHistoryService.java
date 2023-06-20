package com.lqs.mall.service;

import com.lqs.mall.nosql.mongodb.document.MemberReadHistory;

import java.util.List;

/**
 * 浏览记录管理
 * Created by 刘千山 on 2023/6/20/020-15:29
 */
public interface MemberReadHistoryService {

//    增加记录
    int create(MemberReadHistory memberReadHistory);

//    批量删除记录
    int delete(List<String> ids);

//    获取浏览记录
    List<MemberReadHistory> list(Long memberId);

}
