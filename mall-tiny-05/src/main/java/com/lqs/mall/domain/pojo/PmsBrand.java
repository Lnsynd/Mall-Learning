package com.lqs.mall.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
* 品牌表
* @TableName pms_brand
*/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("pms_brand")
@ApiModel(value="PmsBrand对象", description="品牌表")
public class PmsBrand implements Serializable {

    /**
    * 
    */
    @NotNull(message="[]不能为空")
    @ApiModelProperty("")
    private Long id;
    /**
    * 
    */
    @Size(max= 64,message="编码长度不能超过64")
    @ApiModelProperty("")
    @Length(max= 64,message="编码长度不能超过64")
    private String name;
    /**
    * 首字母
    */
    @Size(max= 8,message="编码长度不能超过8")
    @ApiModelProperty("首字母")
    @Length(max= 8,message="编码长度不能超过8")
    private String firstLetter;
    /**
    * 
    */
    @ApiModelProperty("")
    private Integer sort;
    /**
    * 是否为品牌制造商：0->不是；1->是
    */
    @ApiModelProperty("是否为品牌制造商：0->不是；1->是")
    private Integer factoryStatus;
    /**
    * 
    */
    @ApiModelProperty("")
    private Integer showStatus;
    /**
    * 产品数量
    */
    @ApiModelProperty("产品数量")
    private Integer productCount;
    /**
    * 产品评论数量
    */
    @ApiModelProperty("产品评论数量")
    private Integer productCommentCount;
    /**
    * 品牌logo
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("品牌logo")
    @Length(max= 255,message="编码长度不能超过255")
    private String logo;
    /**
    * 专区大图
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("专区大图")
    @Length(max= 255,message="编码长度不能超过255")
    private String bigPic;
    /**
    * 品牌故事
    */
    @Size(max= -1,message="编码长度不能超过-1")
    @ApiModelProperty("品牌故事")
    @Length(max= -1,message="编码长度不能超过-1")
    private String brandStory;

    /**
    * 
    */
    public void setId(Long id){
    this.id = id;
    }

    /**
    * 
    */
    public void setName(String name){
    this.name = name;
    }

    /**
    * 首字母
    */
    public void setFirstLetter(String firstLetter){
    this.firstLetter = firstLetter;
    }

    /**
    * 
    */
    public void setSort(Integer sort){
    this.sort = sort;
    }

    /**
    * 是否为品牌制造商：0->不是；1->是
    */
    public void setFactoryStatus(Integer factoryStatus){
    this.factoryStatus = factoryStatus;
    }

    /**
    * 
    */
    public void setShowStatus(Integer showStatus){
    this.showStatus = showStatus;
    }

    /**
    * 产品数量
    */
    public void setProductCount(Integer productCount){
    this.productCount = productCount;
    }

    /**
    * 产品评论数量
    */
    public void setProductCommentCount(Integer productCommentCount){
    this.productCommentCount = productCommentCount;
    }

    /**
    * 品牌logo
    */
    public void setLogo(String logo){
    this.logo = logo;
    }

    /**
    * 专区大图
    */
    public void setBigPic(String bigPic){
    this.bigPic = bigPic;
    }

    /**
    * 品牌故事
    */
    public void setBrandStory(String brandStory){
    this.brandStory = brandStory;
    }


    /**
    * 
    */
    public Long getId(){
    return this.id;
    }

    /**
    * 
    */
    public String getName(){
    return this.name;
    }

    /**
    * 首字母
    */
    public String getFirstLetter(){
    return this.firstLetter;
    }

    /**
    * 
    */
    public Integer getSort(){
    return this.sort;
    }

    /**
    * 是否为品牌制造商：0->不是；1->是
    */
    public Integer getFactoryStatus(){
    return this.factoryStatus;
    }

    /**
    * 
    */
    public Integer getShowStatus(){
    return this.showStatus;
    }

    /**
    * 产品数量
    */
    public Integer getProductCount(){
    return this.productCount;
    }

    /**
    * 产品评论数量
    */
    public Integer getProductCommentCount(){
    return this.productCommentCount;
    }

    /**
    * 品牌logo
    */
    public String getLogo(){
    return this.logo;
    }

    /**
    * 专区大图
    */
    public String getBigPic(){
    return this.bigPic;
    }

    /**
    * 品牌故事
    */
    public String getBrandStory(){
    return this.brandStory;
    }

}
