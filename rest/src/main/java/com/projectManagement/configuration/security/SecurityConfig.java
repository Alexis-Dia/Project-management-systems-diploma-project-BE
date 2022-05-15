package com.projectManagement.configuration.security;

import static com.projectManagement.consts.Common.ROLE_ADMIN;
import static com.projectManagement.consts.Common.ROLE_DRIVER;
import static com.projectManagement.rest.Navigation.LOAD;
import static com.projectManagement.rest.Navigation.PATH_AUTH_AUTHENTICATE;
import static com.projectManagement.rest.Navigation.PATH_PROJECT_CREATE;
import static com.projectManagement.rest.Navigation.PATH_PROJECT_LOAD;
import static com.projectManagement.rest.Navigation.PATH_PROJECT_LOAD_ALL_MINE;
import static com.projectManagement.rest.Navigation.PATH_REPORT_LOAD;
import static com.projectManagement.rest.Navigation.PATH_TASK_CREATE;
import static com.projectManagement.rest.Navigation.PATH_TASK_LOAD;
import static com.projectManagement.rest.Navigation.PATH_TASK_LOAD_ALL_MINE;
import static com.projectManagement.rest.Navigation.PATH_USER_ALL;
import static com.projectManagement.rest.Navigation.PATH_USER_ALL_DRIVERS;
import static com.projectManagement.rest.Navigation.PATH_USER_CHANGE_STATUS;
import static com.projectManagement.rest.Navigation.PATH_USER_DELETE;
import static com.projectManagement.rest.Navigation.PATH_USER_EDIT;
import static com.projectManagement.rest.Navigation.PATH_USER_EDIT_ME;
import static com.projectManagement.rest.Navigation.PATH_USER_GET_ADMIN;
import static com.projectManagement.rest.Navigation.PATH_USER_GET_BY_ID;
import static com.projectManagement.rest.Navigation.PATH_USER_GET_ME;
import static com.projectManagement.rest.Navigation.PATH_USER_SIGN_UP;

import com.projectManagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@EnableOAuth2Client
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProvider authProvider;

    @Autowired
    private UnauthorizedEntryPoint unauthorizedEntryPoint;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Value("${spring.security.origins.allow}")
    private String allowOrigins;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                    .addMapping("/**")
                    .allowedMethods("*")
                    .allowedHeaders("*")
                    .allowCredentials(false)
                    .allowedOrigins(allowOrigins);
            }
        };
    }

    @Autowired
    @Qualifier("oauth2ClientContext")
    private OAuth2ClientContext oAuth2ClientContext;

    @Bean
    @ConfigurationProperties("google.client")
    public AuthorizationCodeResourceDetails google()
    {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("google.resource")
    public ResourceServerProperties googleResource()
    {
        return new ResourceServerProperties();
    }

    @Bean
    public FilterRegistrationBean oAuth2ClientFilterRegistration(OAuth2ClientContextFilter oAuth2ClientContextFilter)
    {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(oAuth2ClientContextFilter);
        registration.setOrder(-100);
        return registration;
    }

    private Filter ssoFilter()
    {
        OAuth2ClientAuthenticationProcessingFilter googleFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/google");
        OAuth2RestTemplate googleTemplate = new OAuth2RestTemplate(google(), oAuth2ClientContext);
        googleFilter.setRestTemplate(googleTemplate);
        CustomUserInfoTokenServices tokenServices = new CustomUserInfoTokenServices(googleResource().getUserInfoUri(), google().getClientId());
        tokenServices.setRestTemplate(googleTemplate);
        googleFilter.setTokenServices(tokenServices);
        tokenServices.setUserService(userService);
        tokenServices.setPasswordEncoder(bCryptPasswordEncoder);
        return googleFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint)
            .and()
                .httpBasic()
            .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, PATH_USER_SIGN_UP).anonymous()
                .antMatchers(HttpMethod.PUT, PATH_USER_EDIT_ME).hasAuthority(ROLE_DRIVER)
                .antMatchers(HttpMethod.GET, PATH_USER_GET_ME).hasAuthority(ROLE_DRIVER)
                //.antMatchers(HttpMethod.GET, PATH_USER_ALL).hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET, PATH_USER_ALL).anonymous()
                .antMatchers(HttpMethod.POST, PATH_USER_ALL_DRIVERS).hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET, PATH_USER_GET_BY_ID).hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET, PATH_USER_GET_ADMIN).hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.PUT, PATH_USER_EDIT).hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.PUT, PATH_USER_CHANGE_STATUS).hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.DELETE, PATH_USER_DELETE).hasAuthority(ROLE_ADMIN)

                //TODO: Fix it after chacking that login is working
                .antMatchers(HttpMethod.POST, PATH_PROJECT_LOAD).hasAnyAuthority(ROLE_DRIVER, ROLE_ADMIN)
                .antMatchers(HttpMethod.POST, PATH_TASK_LOAD).hasAnyAuthority(ROLE_DRIVER, ROLE_ADMIN)
                .antMatchers(HttpMethod.GET, PATH_TASK_LOAD_ALL_MINE).hasAnyAuthority(ROLE_DRIVER, ROLE_ADMIN)
                .antMatchers(HttpMethod.GET, PATH_PROJECT_LOAD_ALL_MINE).hasAnyAuthority(ROLE_DRIVER, ROLE_ADMIN)
                .antMatchers(HttpMethod.POST, PATH_REPORT_LOAD).hasAnyAuthority(ROLE_DRIVER, ROLE_ADMIN)
                //.antMatchers(HttpMethod.POST, PATH_PROJECT_LOAD).anonymous()
                .antMatchers(HttpMethod.PUT, PATH_PROJECT_CREATE).hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.PUT, PATH_PROJECT_CREATE).hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.PUT, PATH_PROJECT_CREATE).hasAnyAuthority(ROLE_DRIVER, ROLE_ADMIN)

                .antMatchers(HttpMethod.GET, PATH_AUTH_AUTHENTICATE).anonymous();
                //.antMatchers(HttpMethod.POST, PATH_USER_SIGN_UP).anonymous();

                /* From my point of view it means that any request except all above will demand basic auth
                Also this line disables object-info with timestamp, status, error, message, path -fields */
                //.anyRequest().authenticated();

        http
            .addFilterBefore(ssoFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
