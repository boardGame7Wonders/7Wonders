package com.boardgame.sevenwonders.config;


import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

import com.boardgame.sevenwonders.security.AjaxAuthenticationEntryPoint;
import com.boardgame.sevenwonders.security.AjaxAuthenticationFailureHandler;
import com.boardgame.sevenwonders.security.AjaxAuthenticationSuccessHandler;
import com.boardgame.sevenwonders.security.AjaxLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Value("${code.failure.authentication}")
    private int authenticationFailureCode;

	@Value("${code.failure.ajax}")
    private int ajaxFailureCode;

    @Resource
    private AuthenticationProvider authenticationProvider;
    
    @Resource
    private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;

    public SecurityConfiguration() {
        super();
        log.info("Initialize SecurityConfiguration");
    }

    protected void configureAuthorizeRequests(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests) {
        authorizeRequests
                .regexMatchers(".*rest.*").authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .exceptionHandling()
            .authenticationEntryPoint(new AjaxAuthenticationEntryPoint(ajaxFailureCode))
            .and()
            .formLogin()
            .loginProcessingUrl("/app/login")
            .usernameParameter("j_username")
//            .passwordParameter("j_password")
            .failureHandler(new AjaxAuthenticationFailureHandler(authenticationFailureCode))
            .successHandler(new AjaxAuthenticationSuccessHandler())
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/app/logout")
            .deleteCookies("JSESSIONID")
//            .addLogoutHandler(new CustomLogoutHandler())
            .logoutSuccessHandler(ajaxLogoutSuccessHandler)
            .permitAll()
            .and()
            .sessionManagement()
            .maximumSessions(1)
            .sessionRegistry(sessionRegistry())
            .and()
            .and()
            .csrf()
            .disable()
            .headers()
            .frameOptions().disable();

        http.authorizeRequests()
                .regexMatchers(".*rest/authenticate(.*)?").permitAll();

        configureAuthorizeRequests(http.authorizeRequests());

        http.authorizeRequests().regexMatchers(".*").denyAll();
    }
    
    @Bean
    public SessionRegistry sessionRegistry() {
    	SessionRegistry sessionRegistry = new SessionRegistryImpl();
    	return sessionRegistry;
    }
}
