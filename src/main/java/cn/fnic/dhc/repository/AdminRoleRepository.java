package cn.fnic.dhc.repository;

import cn.fnic.dhc.domain.AdminRole;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = AdminRole.class)
public interface AdminRoleRepository {
}
