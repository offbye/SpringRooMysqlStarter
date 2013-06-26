package cn.fnic.dhc.repository;

import cn.fnic.dhc.domain.AdminUser;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = AdminUser.class)
public interface AdminUserRepository {
}
