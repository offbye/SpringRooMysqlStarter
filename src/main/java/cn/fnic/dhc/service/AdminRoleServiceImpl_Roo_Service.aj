// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cn.fnic.dhc.service;

import cn.fnic.dhc.domain.AdminRole;
import cn.fnic.dhc.repository.AdminRoleRepository;
import cn.fnic.dhc.service.AdminRoleServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect AdminRoleServiceImpl_Roo_Service {
    
    declare @type: AdminRoleServiceImpl: @Service;
    
    declare @type: AdminRoleServiceImpl: @Transactional;
    
    @Autowired
    AdminRoleRepository AdminRoleServiceImpl.adminRoleRepository;
    
    public long AdminRoleServiceImpl.countAllAdminRoles() {
        return adminRoleRepository.count();
    }
    
    public void AdminRoleServiceImpl.deleteAdminRole(AdminRole adminRole) {
        adminRoleRepository.delete(adminRole);
    }
    
    public AdminRole AdminRoleServiceImpl.findAdminRole(Long id) {
        return adminRoleRepository.findOne(id);
    }
    
    public List<AdminRole> AdminRoleServiceImpl.findAllAdminRoles() {
        return adminRoleRepository.findAll();
    }
    
    public List<AdminRole> AdminRoleServiceImpl.findAdminRoleEntries(int firstResult, int maxResults) {
        return adminRoleRepository.findAll(new org.springframework.data.domain.PageRequest(firstResult / maxResults, maxResults)).getContent();
    }
    
    public void AdminRoleServiceImpl.saveAdminRole(AdminRole adminRole) {
        adminRoleRepository.save(adminRole);
    }
    
    public AdminRole AdminRoleServiceImpl.updateAdminRole(AdminRole adminRole) {
        return adminRoleRepository.save(adminRole);
    }
    
}