// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cn.fnic.dhc.domain;

import cn.fnic.dhc.domain.AdminRole;
import cn.fnic.dhc.domain.AdminUser;
import java.util.Date;
import java.util.Set;

privileged aspect AdminUser_Roo_JavaBean {
    
    public String AdminUser.getUsername() {
        return this.username;
    }
    
    public void AdminUser.setUsername(String username) {
        this.username = username;
    }
    
    public String AdminUser.getEmail() {
        return this.email;
    }
    
    public void AdminUser.setEmail(String email) {
        this.email = email;
    }
    
    public String AdminUser.getPassword() {
        return this.password;
    }
    
    public void AdminUser.setPassword(String password) {
        this.password = password;
    }
    
    public String AdminUser.getRealName() {
        return this.realName;
    }
    
    public void AdminUser.setRealName(String realName) {
        this.realName = realName;
    }
    
    public String AdminUser.getOfficePhone() {
        return this.officePhone;
    }
    
    public void AdminUser.setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }
    
    public String AdminUser.getMobilePhone() {
        return this.mobilePhone;
    }
    
    public void AdminUser.setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    
    public Date AdminUser.getCreateTime() {
        return this.createTime;
    }
    
    public void AdminUser.setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Date AdminUser.getLastLoginTime() {
        return this.lastLoginTime;
    }
    
    public void AdminUser.setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    
    public String AdminUser.getLastLoginIp() {
        return this.lastLoginIp;
    }
    
    public void AdminUser.setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }
    
    public Set<AdminRole> AdminUser.getAdminRoles() {
        return this.adminRoles;
    }
    
    public void AdminUser.setAdminRoles(Set<AdminRole> adminRoles) {
        this.adminRoles = adminRoles;
    }
    
}