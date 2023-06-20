package com.lqs.mall.service.impl;

import com.lqs.mall.nosql.mongodb.document.MemberReadHistory;
import com.lqs.mall.nosql.mongodb.repository.MemberReadHistoryRepository;
import com.lqs.mall.service.MemberReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 刘千山 on 2023/6/20/020-15:30
 */
@Service
public class MemberReadHistoryServiceImpl implements MemberReadHistoryService {

    @Autowired
    private MemberReadHistoryRepository repository;

    @Override
    public int create(MemberReadHistory memberReadHistory) {
        memberReadHistory.setId(null);
        memberReadHistory.setCreateTime(new Date());
        repository.save(memberReadHistory);
        return 1;
    }

    @Override
    public int delete(List<String> ids) {
        // 新建一个要删除的列表
        List<MemberReadHistory> deleteList = new ArrayList<>();
        ids.stream().peek(id->{
            MemberReadHistory memberReadHistory = new MemberReadHistory();
            memberReadHistory.setId(id);
            deleteList.add(memberReadHistory);
        }).collect(Collectors.toList());
        repository.deleteAll(deleteList);
        return ids.size();
    }

    @Override
    public List<MemberReadHistory> list(Long memberId) {
        return  repository.findByMemberIdOrderByCreateTimeDesc(memberId);
    }
}
