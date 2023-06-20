package com.lqs.mall.nosql.mongodb.repository;

import com.lqs.mall.nosql.mongodb.document.MemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 会员商品浏览记录repository
 * Created by 刘千山 on 2023/6/20/020-15:25
 */
public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory,String> {
    /**
     * 根据会员的id按时间倒序获取浏览记录
     * @param memberId 会员id
     * @return 浏览记录
     */
    List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId);
}
