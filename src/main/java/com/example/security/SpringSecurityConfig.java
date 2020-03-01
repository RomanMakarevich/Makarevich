package com.example.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.security.Roles.EMPLOYEE;
import static com.example.security.Roles.USER;


/**
 * @author Wladimir Litvinov
 */
@EnableWebSecurity
@AllArgsConstructor
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final LoadUserDetailService userDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.httpBasic()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/product-factory-app/products").hasRole(USER.name())
                .antMatchers(HttpMethod.POST, "/product-factory-app/user/*").hasRole(USER.name())
                .antMatchers(HttpMethod.PUT, "/product-factory-app/products/*").hasRole(EMPLOYEE.name())
                .antMatchers(HttpMethod.GET, "/product-factory-app/orders").hasAnyRole(EMPLOYEE.name())
                .antMatchers(HttpMethod.POST, "/product-factory-app/orders/*").hasAnyRole(EMPLOYEE.name())
                .antMatchers(HttpMethod.POST, "/user/sign-in", "/user/sign-up").permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable();
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}