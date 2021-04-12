package com.rma.system.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色
 */
public class SysRole  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String code;

    private String name;

    private Date createTime;

    private Date updateTime;
    /**
     * 角色对应的权限
     */
    private List<SysMenuPermission> sysMenuPermissions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<SysMenuPermission> getSysMenuPermissions() {
        return sysMenuPermissions;
    }

    public void setSysMenuPermissions(List<SysMenuPermission> sysMenuPermissions) {
        this.sysMenuPermissions = sysMenuPermissions;
    }
}