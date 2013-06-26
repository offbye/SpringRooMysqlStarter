package cn.fnic.dhc.web;

import cn.fnic.dhc.domain.AdminUser;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/adminusers")
@Controller
@RooWebScaffold(path = "adminusers", formBackingObject = AdminUser.class)
public class AdminUserController {
}
