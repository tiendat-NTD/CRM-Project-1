package com.yan.crm_proj.util;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.*;

import com.yan.crm_proj.model.*;
import com.yan.crm_proj.service.*;

import static org.springframework.security.core.context.SecurityContextHolder.*;

@Component
public class UserUtil {
    @Autowired
    private UserService userService;

    // Get current user account from SecurityContextHolder
    public User getAccount() {
        var authentication = getContext().getAuthentication();
        return authentication instanceof AnonymousAuthenticationToken ? null
                : userService.getUser(authentication.getName());
    }
}
