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
* 后台用户权限表
* @TableName ums_permission
*/
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ums_permission")
@ApiModel(value="UmsPermission对象", description="后台用户权限表")
public class UmsPermission implements Serializable {

    /**
    * 
    */
    @NotNull(message="[]不能为空")
    @ApiModelProperty("")
    private Long id;
    /**
    * 父级权限id
    */
    @ApiModelProperty("父级权限id")
    private Long pid;
    /**
    * 名称
    */
    @Size(max= 100,message="编码长度不能超过100")
    @ApiModelProperty("名称")
    @Length(max= 100,message="编码长度不能超过100")
    private String name;
    /**
    * 权限值
    */
    @Size(max= 200,message="编码长度不能超过200")
    @ApiModelProperty("权限值")
    @Length(max= 200,message="编码长度不能超过200")
    private String value;
    /**
    * 图标
    */
    @Size(max= 500,message="编码长度不能超过500")
    @ApiModelProperty("图标")
    @Length(max= 500,message="编码长度不能超过500")
    private String icon;
    /**
    * 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
    */
    @ApiModelProperty("权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）")
    private Integer type;
    /**
    * 前端资源路径
    */
    @Size(max= 200,message="编码长度不能超过200")
    @ApiModelProperty("前端资源路径")
    @Length(max= 200,message="编码长度不能超过200")
    private String uri;
    /**
    * 启用状态；0->禁用；1->启用
    */
    @ApiModelProperty("启用状态；0->禁用；1->启用")
    private Integer status;
    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
    * 排序
    */
    @ApiModelProperty("排序")
    private Integer sort;

    /**
    * 
    */
    public void setId(Long id){
    this.id = id;
    }

    /**
    * 父级权限id
    */
    public void setPid(Long pid){
    this.pid = pid;
    }

    /**
    * 名称
    */
    public void setName(String name){
    this.name = name;
    }

    /**
    * 权限值
    */
    public void setValue(String value){
    this.value = value;
    }

    /**
    * 图标
    */
    public void setIcon(String icon){
    this.icon = icon;
    }

    /**
    * 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
    */
    public void setType(Integer type){
    this.type = type;
    }

    /**
    * 前端资源路径
    */
    public void setUri(String uri){
    this.uri = uri;
    }

    /**
    * 启用状态；0->禁用；1->启用
    */
    public void setStatus(Integer status){
    this.status = status;
    }

    /**
    * 创建时间
    */
    public void setCreateTime(Date createTime){
    this.createTime = createTime;
    }

    /**
    * 排序
    */
    public void setSort(Integer sort){
    this.sort = sort;
    }


    /**
    * 
    */
    public Long getId(){
    return this.id;
    }

    /**
    * 父级权限id
    */
    public Long getPid(){
    return this.pid;
    }

    /**
    * 名称
    */
    public String getName(){
    return this.name;
    }

    /**
    * 权限值
    */
    public String getValue(){
    return this.value;
    }

    /**
    * 图标
    */
    public String getIcon(){
    return this.icon;
    }

    /**
    * 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
    */
    public Integer getType(){
    return this.type;
    }

    /**
    * 前端资源路径
    */
    public String getUri(){
    return this.uri;
    }

    /**
    * 启用状态；0->禁用；1->启用
    */
    public Integer getStatus(){
    return this.status;
    }

    /**
    * 创建时间
    */
    public Date getCreateTime(){
    return this.createTime;
    }

    /**
    * 排序
    */
    public Integer getSort(){
    return this.sort;
    }

}
