/**
 * Company: SWORD Copyright (c) 2014
 */
package com.boardgame.sevenwonders.security;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * Authentication Provider. <br/>
 *
 */
@Slf4j
@Service
public class CustomAuthenticationProvider implements AuthenticationProvider, InitializingBean, Ordered {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        if (null == authentication.getPrincipal()) {
            throw new BadCredentialsException("No user principal");
        }

//        String username = SecurityUtils.getCurrentLogin(authentication.getPrincipal());
//
//        if (StringUtils.isEmpty(username)) {
//            throw new BadCredentialsException("No user");
//        }
//
//        String domain = SecurityUtils.getCurrentDomain(authentication.getPrincipal());
//
//        if (authentication instanceof UsernamePasswordAuthenticationToken) {
//            User user = userService.getUserByPkWithFunctions(username);
//            String password = authentication.getCredentials().toString();
//            if (user == null) {
//                throw new UsernameNotFoundException("User " + username + " was not found in the database");
//            } else if (!user.getActivated()) {
//                throw new DisabledException("User " + username + " was not activated");
//            } else {
//                log.info("User {} found in database", username);
//            }
//            
//            if (!hasFunctionConnection(user)) {
//                throw new InsufficientAuthenticationException("Unauthorized profile");
//            }
//
//            if (user.getLdap() != null && user.getLdap() == true) {
//                if (StringUtils.isBlank(password)) {
//                    throw new BadCredentialsException("No password supplied");
//                }
//
//                String filter = ldapConfig.getFilterUserString().replace("?", username);
//                try {
//                    if (!ldapTemplate.authenticate(LdapUtils.emptyLdapName(), filter, password)) {
//                        throw new BadCredentialsException("Invalid password");
//                    }
//                } catch (Exception e) {
//                    throw new BadCredentialsException("Error LDAP");
//                }
//            } else if (!StringUtils.equals(HashUtils.hashPassword(password, null), user.getPassword())) {
//                throw new BadCredentialsException("Invalid password");
//            }
//
//            Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getProfile().getId());
//            grantedAuthorities.add(grantedAuthority);
//
//            UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
//                    authentication.getPrincipal(),
//                    authentication.getCredentials(),
//                    grantedAuthorities);
//
//            result.setDetails(new org.springframework.security.core.userdetails.User(
//                    user.getLogin(),
//                    passwordEncoder.encode(password),
//                    grantedAuthorities));
//
//            return result;
//
//        } else if (authentication instanceof PreAuthenticatedAuthenticationToken) {
//            if (StringUtils.isEmpty(domain)) {
//                throw new UsernameNotFoundException("No domain associated with user " + username);
//            } else {
//                log.info("Domain {} associated with user {}", domain, username);
//            }
//
//            User user = userService.getUserByPkWithFunctions(username);
//            String password = authentication.getCredentials().toString();
//            if (user == null) {
//                throw new UsernameNotFoundException("User " + username + " was not found in the database");
//            } else if (!user.getActivated()) {
//                throw new DisabledException("User " + username + " was not activated");
//            }
//
//            if (!hasFunctionConnection(user)) {
//                throw new InsufficientAuthenticationException("Unauthorized profile");
//            }
//
//            Assert.notNull(user.getLdap(), "User must be declared in LDAP");
//
//            String filter = ldapConfig.getFilterUserString().replace("?", username);
//            List<String> res = ldapTemplate.search(LdapUtils.emptyLdapName(), filter,
//                    (AttributesMapper<String>) (Attributes atrbts) -> atrbts.get(ldapConfig.getDistinguishedNameProperty()).toString());
//
//            Assert.notEmpty(res, "User not found in LDAP");
//
//            String distinguishedName = res.get(0);
//
//            log.info("User {} found in LDAP", distinguishedName);
//
//            Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//            Profile authority = user.getProfile();
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getId());
//            grantedAuthorities.add(grantedAuthority);
//
//            UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
//                    authentication.getPrincipal(),
//                    authentication.getCredentials(),
//                    grantedAuthorities);
//
//            result.setDetails(new org.springframework.security.core.userdetails.User(
//                    user.getLogin(),
//                    passwordEncoder.encode(password),
//                    grantedAuthorities));
//
//            return result;
//        }

        throw new BadCredentialsException("Bad AuthenticationToken");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication) || PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }

	@Override
	public int getOrder() {
		return 0;
	}
    
}
