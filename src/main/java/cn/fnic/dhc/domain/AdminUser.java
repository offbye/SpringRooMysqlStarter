package cn.fnic.dhc.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;


@RooJavaBean
@RooToString
@RooJpaEntity
public class AdminUser {

	@Column(unique = true)
    @NotNull
    private String username;

	@Column(unique = true)
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+")
    private String email;

    @NotNull
    @Size(min = 6, max = 32)
    private String password;

    @Size(max = 32)
    private String realName;

    @Size(max = 16)
    private String officePhone;

    @Size(max = 16)
    private String mobilePhone;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date lastLoginTime;

    @Size(max = 16)
    private String lastLoginIp;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AdminRole> adminRoles = new HashSet<AdminRole>();
}
