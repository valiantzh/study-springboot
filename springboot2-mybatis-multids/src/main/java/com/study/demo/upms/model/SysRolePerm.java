package com.study.demo.upms.model;

import java.io.Serializable;

public class SysRolePerm implements Serializable {
    private String roleId;

    private String permVal;

    private Integer permType;

    private static final long serialVersionUID = 1L;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermVal() {
        return permVal;
    }

    public void setPermVal(String permVal) {
        this.permVal = permVal;
    }

    public Integer getPermType() {
        return permType;
    }

    public void setPermType(Integer permType) {
        this.permType = permType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", roleId=").append(roleId);
        sb.append(", permVal=").append(permVal);
        sb.append(", permType=").append(permType);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysRolePerm other = (SysRolePerm) that;
        return (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getPermVal() == null ? other.getPermVal() == null : this.getPermVal().equals(other.getPermVal()))
            && (this.getPermType() == null ? other.getPermType() == null : this.getPermType().equals(other.getPermType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getPermVal() == null) ? 0 : getPermVal().hashCode());
        result = prime * result + ((getPermType() == null) ? 0 : getPermType().hashCode());
        return result;
    }
}