package cn.fnic.dhc.domain;

import java.util.Date;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity
public class AdminRole {

    @NotNull
    @Size(min = 1, max = 16)
    private String roleName;

    @Size(max = 256)
    private String description;

    @NotNull
    @Size(min = 1, max = 16)
    private String auth;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date createTime;

    @ManyToOne
    private AdminUser createdBy;
}
