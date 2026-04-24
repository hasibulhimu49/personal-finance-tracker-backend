package com.example.personal_finance_tracker_api.common.util;

import com.example.personal_finance_tracker_api.common.exception.UnauthorizedException;
import com.example.personal_finance_tracker_api.security.UserPrincipal;
import com.example.personal_finance_tracker_api.user.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    private SecurityUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserPrincipal) {
            return ((UserPrincipal) authentication.getPrincipal()).getUser();
        }
        throw new UnauthorizedException("User not authenticated");
    }
}
