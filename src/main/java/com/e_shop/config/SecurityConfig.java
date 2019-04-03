package com.e_shop.config;

import com.e_shop.config.CustomLogoutSuccessHandler;
import com.e_shop.services.ClientService;
import com.e_shop.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ClientService clientService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                    .antMatchers("/admin", "/addproducts").hasRole("ADMIN")
                    .antMatchers("/**", "/ajax", "/basket", "/confirm", "/delete", "/registration", "/catalog", "/phones", "/resources/**").permitAll()
                    .antMatchers("/delete").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/",true)
                    .failureUrl("/")
                    .permitAll()
                .and()
//                    .logout()
//                    .invalidateHttpSession(true)
//                    .logoutSuccessUrl("/")
//                    .permitAll();
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessHandler(logoutSuccessHandler())
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(false)
                    .permitAll();

//        AntPathRequestMatcher pathRequestMatcher = new AntPathRequestMatcher("/logout");
//        http.logout().logoutRequestMatcher(pathRequestMatcher);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .withUser("khan")
//                .password(encoder.encode("solo"))
//                .roles("ADMIN");
        auth.userDetailsService(clientService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());

    }

    //FIX Password Encoder
//    @Autowired
//    public void configureUsers(AuthenticationManagerBuilder auth) throws Exception {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password(encoder.encode("user"))
//                .roles("USER")
//                .and()
//                .withUser("Khan")
//                .password(encoder.encode("solo"))
//                .roles("ADMIN");
//    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

}

