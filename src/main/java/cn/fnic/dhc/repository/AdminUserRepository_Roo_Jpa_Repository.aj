// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cn.fnic.dhc.repository;

import cn.fnic.dhc.domain.AdminUser;
import cn.fnic.dhc.repository.AdminUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

privileged aspect AdminUserRepository_Roo_Jpa_Repository {
    
    declare parents: AdminUserRepository extends JpaRepository<AdminUser, Long>;
    
    declare parents: AdminUserRepository extends JpaSpecificationExecutor<AdminUser>;
    
    declare @type: AdminUserRepository: @Repository;
    
}
