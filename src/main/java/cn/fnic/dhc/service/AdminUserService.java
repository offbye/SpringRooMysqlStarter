package cn.fnic.dhc.service;

import java.util.List;

import org.springframework.roo.addon.layers.service.RooService;

import cn.fnic.dhc.domain.AdminUser;

@RooService(domainTypes = { cn.fnic.dhc.domain.AdminUser.class })
public interface AdminUserService {
	public List<AdminUser> findAdminUsersByPasswordAndUsernameEquals(String username, String password);
}
