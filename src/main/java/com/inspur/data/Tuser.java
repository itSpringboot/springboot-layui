package com.inspur.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;

@ApiModel(value="com.inspur.data.Tuser")
@Table(name = "t_user")
public class Tuser implements Serializable {
    @Id
    @ApiModelProperty(value="id")
    private String id;

    /**
     * 用户名
     */
    @ApiModelProperty(value="name用户名")
    private String name;

    /**
     * 密码
     */
    @ApiModelProperty(value="pass密码")
    private String pass;

    /**
     * 手机号
     */
    @ApiModelProperty(value="phone手机号")
    private String phone;

    /**
     * 用户状态
     */
    @ApiModelProperty(value="status用户状态")
    private String status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="created创建时间")
    private String created;

    /**
     * 更新时间
     */
    @ApiModelProperty(value="updated更新时间")
    private String updated;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public Tuser setId(String id) {
        this.id = id == null ? null : id.trim();
        return this;
    }

    /**
     * 获取用户名
     *
     * @return name - 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名
     *
     * @param name 用户名
     */
    public Tuser setName(String name) {
        this.name = name == null ? null : name.trim();
        return this;
    }

    /**
     * 获取密码
     *
     * @return pass - 密码
     */
    public String getPass() {
        return pass;
    }

    /**
     * 设置密码
     *
     * @param pass 密码
     */
    public Tuser setPass(String pass) {
        this.pass = pass == null ? null : pass.trim();
        return this;
    }

    /**
     * 获取手机号
     *
     * @return phone - 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public Tuser setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
        return this;
    }

    /**
     * 获取用户状态
     *
     * @return status - 用户状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置用户状态
     *
     * @param status 用户状态
     */
    public Tuser setStatus(String status) {
        this.status = status == null ? null : status.trim();
        return this;
    }

    /**
     * 获取创建时间
     *
     * @return created - 创建时间
     */
    public String getCreated() {
        return created;
    }

    /**
     * 设置创建时间
     *
     * @param created 创建时间
     */
    public Tuser setCreated(String created) {
        this.created = created == null ? null : created.trim();
        return this;
    }

    /**
     * 获取更新时间
     *
     * @return updated - 更新时间
     */
    public String getUpdated() {
        return updated;
    }

    /**
     * 设置更新时间
     *
     * @param updated 更新时间
     */
    public Tuser setUpdated(String updated) {
        this.updated = updated == null ? null : updated.trim();
        return this;
    }
}