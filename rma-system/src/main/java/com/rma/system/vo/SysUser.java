package com.rma.system.vo;

import java.io.Serializable;
import java.util.List;

public class SysUser extends SysUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色
     */
    private List<SysRole> sysRoles;

    public List<SysRole> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(List<SysRole> sysRoles) {
        this.sysRoles = sysRoles;
    }
}