package cn.fnic.dhc.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.FlushMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fnic.dhc.domain.AdminUser;
import cn.fnic.dhc.repository.AdminUserRepository;

@Service
@Transactional
public class AdminUserServiceImpl implements AdminUserService {

	@PersistenceContext()
    private EntityManager entityManager;  
	private EntityManager getEntityManager(){
		//entityManager.
		return this.entityManager;
	}
	
	@Override
	public List<AdminUser> findAdminUsersByPasswordAndUsernameEquals(String username, String password){
		String sql = "SELECT t FROM AdminUser AS t WHERE t.username = :username and t.password = :password";
		Query query = entityManager.createQuery(sql, AdminUser.class).setParameter("username", username).setParameter("password", password);
		
		return (List<AdminUser>)query.getResultList();
//		return null;
	}

	
}
