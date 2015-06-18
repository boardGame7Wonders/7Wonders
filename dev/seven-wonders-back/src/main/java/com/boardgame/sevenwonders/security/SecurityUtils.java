package com.boardgame.sevenwonders.security;

import java.security.Principal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Utility class for Spring Security.
 */
@Slf4j
public final class SecurityUtils {
    
    public static String ANONYMOUS_USER = "anonymousUser";

    /**
     * Get the login of the current authenticated user.
     *
     * @return username
     */
    public static String getCurrentAuthenticatedLogin() {
        String login = ANONYMOUS_USER;

        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext == null) {
            log.debug("No security context found");
        } else if (securityContext.getAuthentication() == null) {
            log.debug("No authentication found");
        } else {
            if (securityContext.getAuthentication().isAuthenticated()) {
                login = getLoginFromAuthentication(securityContext.getAuthentication());
                log.debug("User {} authenticated", login);
            } else {
                log.debug("User {} not authenticated", login);
            }
        }
        return login;
    }

    /**
     * Get the user name from principal.
     *
     * @param authentication authentication
     * @return username
     */
    public static String getLoginFromAuthentication(Authentication authentication) {
        if (authentication == null) {
            log.debug("No authentication found");
            return null;
        }
        return getLoginFromPrincipal(authentication.getPrincipal());
    }
    
    /**
     * Get the user name from principal.
     *
     * @param principal principal
     * @return username
     */
    public static String getLoginFromPrincipal(Object principal) {
        log.debug("User principal={}", principal);
        if (principal == null) {
            return null;
        }
        if (principal instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) principal;
            return springSecurityUser.getUsername();
        } else if (principal instanceof String) {
            return (String) principal;
        } else if (principal instanceof Principal) {
            return ((Principal) principal).getName();
        } else {
            return principal.toString();
        }
    }

}
