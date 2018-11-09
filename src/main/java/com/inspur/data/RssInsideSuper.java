package com.inspur.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;

@ApiModel(value="com.example.data.RssInsideSuper")
@Table(name = "rss_inside_super")
public class RssInsideSuper implements Serializable {
    /**
     * 主键
     */
    @Id
    @ApiModelProperty(value="id主键")
    private String id;

    /**
     * 监督报告表ID
     */
    @Column(name = "present_id")
    @ApiModelProperty(value="presentId监督报告表ID")
    private String presentId;

    /**
     * 姓名
     */
    @Column(name = "inside_name")
    @ApiModelProperty(value="insideName姓名")
    private String insideName;

    /**
     * 职务
     */
    @Column(name = "inside_post")
    @ApiModelProperty(value="insidePost职务")
    private String insidePost;

    /**
     * 监督记录
     */
    @Column(name = "super_record")
    @ApiModelProperty(value="superRecord监督记录")
    private String superRecord;

    /**
     * 创建人
     */
    @Column(name = "crt_user")
    @ApiModelProperty(value="crtUser创建人")
    private String crtUser;

    /**
     * 创建时间
     */
    @Column(name = "crt_time")
    @ApiModelProperty(value="crtTime创建时间")
    private String crtTime;

    /**
     * 修改人
     */
    @Column(name = "upd_user")
    @ApiModelProperty(value="updUser修改人")
    private String updUser;

    /**
     * 修改时间
     */
    @Column(name = "upd_time")
    @ApiModelProperty(value="updTime修改时间")
    private String updTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public RssInsideSuper setId(String id) {
        this.id = id == null ? null : id.trim();
        return this;
    }

    /**
     * 获取监督报告表ID
     *
     * @return present_id - 监督报告表ID
     */
    public String getPresentId() {
        return presentId;
    }

    /**
     * 设置监督报告表ID
     *
     * @param presentId 监督报告表ID
     */
    public RssInsideSuper setPresentId(String presentId) {
        this.presentId = presentId == null ? null : presentId.trim();
        return this;
    }

    /**
     * 获取姓名
     *
     * @return inside_name - 姓名
     */
    public String getInsideName() {
        return insideName;
    }

    /**
     * 设置姓名
     *
     * @param insideName 姓名
     */
    public RssInsideSuper setInsideName(String insideName) {
        this.insideName = insideName == null ? null : insideName.trim();
        return this;
    }

    /**
     * 获取职务
     *
     * @return inside_post - 职务
     */
    public String getInsidePost() {
        return insidePost;
    }

    /**
     * 设置职务
     *
     * @param insidePost 职务
     */
    public RssInsideSuper setInsidePost(String insidePost) {
        this.insidePost = insidePost == null ? null : insidePost.trim();
        return this;
    }

    /**
     * 获取监督记录
     *
     * @return super_record - 监督记录
     */
    public String getSuperRecord() {
        return superRecord;
    }

    /**
     * 设置监督记录
     *
     * @param superRecord 监督记录
     */
    public RssInsideSuper setSuperRecord(String superRecord) {
        this.superRecord = superRecord == null ? null : superRecord.trim();
        return this;
    }

    /**
     * 获取创建人
     *
     * @return crt_user - 创建人
     */
    public String getCrtUser() {
        return crtUser;
    }

    /**
     * 设置创建人
     *
     * @param crtUser 创建人
     */
    public RssInsideSuper setCrtUser(String crtUser) {
        this.crtUser = crtUser == null ? null : crtUser.trim();
        return this;
    }

    /**
     * 获取创建时间
     *
     * @return crt_time - 创建时间
     */
    public String getCrtTime() {
        return crtTime;
    }

    /**
     * 设置创建时间
     *
     * @param crtTime 创建时间
     */
    public RssInsideSuper setCrtTime(String crtTime) {
        this.crtTime = crtTime == null ? null : crtTime.trim();
        return this;
    }

    /**
     * 获取修改人
     *
     * @return upd_user - 修改人
     */
    public String getUpdUser() {
        return updUser;
    }

    /**
     * 设置修改人
     *
     * @param updUser 修改人
     */
    public RssInsideSuper setUpdUser(String updUser) {
        this.updUser = updUser == null ? null : updUser.trim();
        return this;
    }

    /**
     * 获取修改时间
     *
     * @return upd_time - 修改时间
     */
    public String getUpdTime() {
        return updTime;
    }

    /**
     * 设置修改时间
     *
     * @param updTime 修改时间
     */
    public RssInsideSuper setUpdTime(String updTime) {
        this.updTime = updTime == null ? null : updTime.trim();
        return this;
    }
}