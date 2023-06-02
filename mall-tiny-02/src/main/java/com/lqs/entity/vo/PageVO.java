package com.lqs.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by 刘千山 on 2023/6/1/001-17:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVO {
    private List rows;
    private Long total;
}
