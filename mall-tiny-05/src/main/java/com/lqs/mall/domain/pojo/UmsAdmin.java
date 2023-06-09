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
import java.util.Date;

/**
* 后台用户表
* @TableName ums_admin
*/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_admin")
@ApiModel(value="UmsAdmin对象", description="后台用户表")
public class UmsAdmin implements Serializable {

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
    private String username;
    /**
    * 
    */
    @Size(max= 64,message="编码长度不能超过64")
    @ApiModelProperty("")
    @Length(max= 64,message="编码长度不能超过64")
    private String password;
    /**
    * 头像
    */
    @Size(max= 500,message="编码长度不能超过500")
    @ApiModelProperty("头像")
    @Length(max= 500,message="编码长度不能超过500")
    private String icon;
    /**
    * 邮箱
    */
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("邮箱")
    @Length(max= 100,message="编码长度不能超过100")
    private String email;
    /**
    * 昵称
    */
    @Size(max= 200,message="编码长度不能超过200")
    @ApiModelProperty("昵称")
    @Length(max= 200,message="编码长度不能超过200")
    private String nickName;
    /**
    * 备注信息
    */
    @Size(max= 500,message="编码长度不能超过500")
    @ApiModelProperty("备注信息")
    @Length(max= 500,message="编码长度不能超过500")
    private String note;
    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
    * 最后登录时间
    */
    @ApiModelProperty("最后登录时间")
    private Date loginTime;
    /**
    * 帐号启用状态：0->禁用；1->启用
    */
    @ApiModelProperty("帐号启用状态：0->禁用；1->启用")
    private Integer status;

    /**
    * 
    */
    public void setId(Long id){
    this.id = id;
    }

    /**
    * 
    */
    public void setUsername(String username){
    this.username = username;
    }

    /**
    * 
    */
    public void setPassword(String password){
    this.password = password;
    }

    /**
    * 头像
    */
    public void setIcon(String icon){
    this.icon = icon;
    }

    /**
    * 邮箱
    */
    public void setEmail(String email){
    this.email = email;
    }

    /**
    * 昵称
    */
    public void setNickName(String nickName){
    this.nickName = nickName;
    }

    /**
    * 备注信息
    */
    public void setNote(String note){
    this.note = note;
    }

    /**
    * 创建时间
    */
    public void setCreateTime(Date createTime){
    this.createTime = createTime;
    }

    /**
    * 最后登录时间
    */
    public void setLoginTime(Date loginTime){
    this.loginTime = loginTime;
    }

    /**
    * 帐号启用状态：0->禁用；1->启用
    */
    public void setStatus(Integer status){
    this.status = status;
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
    public String getUsername(){
    return this.username;
    }

    /**
    * 
    */
    public String getPassword(){
    return this.password;
    }

    /**
    * 头像
    */
    public String getIcon(){
    return this.icon;
    }

    /**
    * 邮箱
    */
    public String getEmail(){
    return this.email;
    }

    /**
    * 昵称
    */
    public String getNickName(){
    return this.nickName;
    }

    /**
    * 备注信息
    */
    public String getNote(){
    return this.note;
    }

    /**
    * 创建时间
    */
    public Date getCreateTime(){
    return this.createTime;
    }

    /**
    * 最后登录时间
    */
    public Date getLoginTime(){
    return this.loginTime;
    }

    /**
    * 帐号启用状态：0->禁用；1->启用
    */
    public Integer getStatus(){
    return this.status;
    }

}
