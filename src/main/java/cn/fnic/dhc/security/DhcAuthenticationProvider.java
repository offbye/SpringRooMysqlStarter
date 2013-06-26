package cn.fnic.dhc.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NonUniqueResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.slf4j.*;
import org.springframework.util.AntPathMatcher; 

import cn.fnic.dhc.domain.AdminRole;
import cn.fnic.dhc.domain.AdminUser;
import cn.fnic.dhc.repository.AdminUserRepository;
import cn.fnic.dhc.service.AdminRoleService;
import cn.fnic.dhc.service.AdminRoleServiceImpl;
import cn.fnic.dhc.service.AdminUserService;

public class DhcAuthenticationProvider extends
		AbstractUserDetailsAuthenticationProvider {

	private static Logger logger = LoggerFactory.getLogger(DhcAuthenticationProvider.class);
	
//	@Autowired
//	private AdminRoleService adminRoleService;
//	
	@Autowired
	private AdminUserService adminUserService;
	
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		String password = (String) authentication.getCredentials();
		if (!StringUtils.hasText(password)) {
			throw new BadCredentialsException("Please enter password");
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		try {
			List<AdminUser> users = adminUserService.findAdminUsersByPasswordAndUsernameEquals(username, password);
			if(users == null || users.size() == 0){
				throw new UsernameNotFoundException("username is not exist");
			}
			if(users.size() > 1){
				throw new UsernameNotFoundException("too many user is found.");
			}
			
			AdminUser user = users.get(0);
			Set<AdminRole> roles = user.getAdminRoles();
			if (roles != null) {
				logger.info("foles size:" + roles.size());
				for (AdminRole role : roles) {
					authorities.add(new SimpleGrantedAuthority(role.getAuth()));
				}
			}
			
			AdminRoleService s = new AdminRoleServiceImpl();
			

		} catch (EmptyResultDataAccessException e) {
			throw new BadCredentialsException("Invalid username or password");
		} catch (EntityNotFoundException e) {
			throw new BadCredentialsException("Invalid user");
		} catch (NonUniqueResultException e) {
			throw new BadCredentialsException(
					"Non-unique user, contact administrator");
		}catch (Exception e){
			logger.error(e.getMessage(), e);
			//throw new Exception(e);
		}
		return new User(username, password, true, // enabled
				true, // account not expired
				true, // credentials not expired
				true, // account not locked
				authorities);
	}
}

class Test {  
    public static void main(String[] args) {  
        AntPathMatcher matcher = new AntPathMatcher();  
        String[] pattern = {"/","/*", "/**", "/em*", "/em**", "/em/*", "/em/**", "/em/**/*"};  
        String[] str = {"/em", "/ems", "/em/a", "/em/a/b", "/em/a/b/c/"};  
        
        for(String p: pattern){
        	insertBlank("");
        	System.out.print(p);
        	insertBlank(p);
        	
        }
        System.out.println("");
        for (String s : str) { 
        	System.out.print(s);
        	insertBlank(s);
            for (String p : pattern) {  
                System.out.print(matcher.match(p, s)); 
                insertBlank(p);
                
            }  
            System.out.println("");
        }  
    }
    private static void insertBlank(String p){
    	for(int i = 0; i < (20 - p.getBytes().length); i++){
    		System.out.print(" ");
    	}
    	//System.out.println(" ");
    }
}  
