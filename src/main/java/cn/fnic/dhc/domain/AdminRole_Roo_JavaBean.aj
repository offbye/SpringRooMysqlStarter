// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cn.fnic.dhc.domain;

import cn.fnic.dhc.domain.AdminRole;
import cn.fnic.dhc.domain.AdminUser;
import java.util.Date;

privileged aspect AdminRole_Roo_JavaBean {
    
    public String AdminRole.getRoleName() {
        return this.roleName;
    }
    
    public void AdminRole.setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public String AdminRole.getDescription() {
        return this.description;
    }
    
    public void AdminRole.setDescription(String description) {
        this.description = description;
    }
    
    public String AdminRole.getAuth() {
        return this.auth;
    }
    
    public void AdminRole.setAuth(String auth) {
        this.auth = auth;
    }
    
    public Date AdminRole.getCreateTime() {
        return this.createTime;
    }
    
    public void AdminRole.setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public AdminUser AdminRole.getCreatedBy() {
        return this.createdBy;
    }
    
    public void AdminRole.setCreatedBy(AdminUser createdBy) {
        this.createdBy = createdBy;
    }
    
}
