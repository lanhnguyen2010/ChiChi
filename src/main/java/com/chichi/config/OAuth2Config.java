//package com.chichi.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.client.OAuth2ClientContext;
//import org.springframework.security.oauth2.client.OAuth2RestOperations;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
//import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
//import org.springframework.security.oauth2.common.AuthenticationScheme;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
//import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
//import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.annotation.Resource;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Arrays;
//
///**
// * Created by lanhnguyen on 09/08/2016.
// */
//@Configuration
//@EnableResourceServer
//public class OAuth2Config extends ResourceServerConfigurerAdapter {
//    private TokenExtractor tokenExtractor = new BearerTokenExtractor();
//    @Autowired
//    OAuth2ClientContext oauth2ClientContext;
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.addFilterAfter(new OncePerRequestFilter() {
//            @Override
//            protected void doFilterInternal(HttpServletRequest request,
//                                            HttpServletResponse response, FilterChain filterChain)
//                    throws ServletException, IOException {
//                // We don't want to allow access to a resource with no token so clear
//                // the security context in case it is actually an OAuth2Authentication
//                if (tokenExtractor.extract(request) == null) {
//                    SecurityContextHolder.clearContext();
//                }
//                filterChain.doFilter(request, response);
//            }
//        }, AbstractPreAuthenticatedProcessingFilter.class);
//        http.csrf().disable();
//        http.antMatcher("/**")
//                .authorizeRequests()
//                .antMatchers("/api/authenticate").permitAll()
//                .antMatchers("/api/**").permitAll();
//        http.authorizeRequests().anyRequest().authenticated();
//    }
//
//    @Bean
//    public OAuth2ProtectedResourceDetails googleOAuth2Details() {
//        AuthorizationCodeResourceDetails googleOAuth2Details = new AuthorizationCodeResourceDetails();
//
//        googleOAuth2Details.setAuthenticationScheme(AuthenticationScheme.form);
//        googleOAuth2Details.setClientAuthenticationScheme(AuthenticationScheme.form);
//        googleOAuth2Details.setClientId("469088736350-v9rs3fliesbf6dqf7evqds1o7jnq8084.apps.googleusercontent.com");
//        googleOAuth2Details.setClientSecret("1YXVaGaYZsEzJkt3yUqimpr5");
//        googleOAuth2Details.setUserAuthorizationUri("https://accounts.google.com/o/oauth2/auth");
//        googleOAuth2Details.setAccessTokenUri("https://accounts.google.com/o/oauth2/token");
//        googleOAuth2Details.setScope(Arrays.asList("openid","https://www.googleapis.com/auth/userinfo.profile"));
//        return googleOAuth2Details;
//    }
//
//    @SuppressWarnings("SpringJavaAutowiringInspection")
//    @Resource
//    private OAuth2ClientContext oAuth2ClientContext;
//
//    @Bean
//    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
//    public OAuth2RestOperations googleOAuth2RestTemplate() {
//        return new OAuth2RestTemplate(googleOAuth2Details(), oAuth2ClientContext);
//    }
//
//
//
//
//
//}
