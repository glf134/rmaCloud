package com.rma.system.vo;

import java.io.Serializable;

/**
 * 角色权限表
 */
public class SysRolePermission  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String roleId;

    private String menuPermissionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getMenuPermissionId() {
        return menuPermissionId;
    }

    public void setMenuPermissionId(String menuPermissionId) {
        this.menuPermissionId = menuPermissionId == null ? null : menuPermissionId.trim();
    }
}