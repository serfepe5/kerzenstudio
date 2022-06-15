package com.tfg.kerzenstudio.websecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.tfg.kerzenstudio.services.UsuarioService;

@SuppressWarnings("deprecation")
@Configuration
//@EnableWebMvc
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
	@Autowired
    UsuarioService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    @Bean
    public InMemoryUserDetailsManager memoryuser() {
    	UserDetails user = User
                .withUsername("administrador")
                .password(passwordEncoder().encode("administrador"))
                .roles("ADMINISTRADOR")
                .build();
       return new InMemoryUserDetailsManager(user);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        auth.userDetailsService(memoryuser());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/","login", "/index", "/mujer", "/hombre", "/accesorios", "/contacto", "/registrarse","/guardarregistrarse","/politica", "/modificarproducto","/css/**","/img/**").permitAll()
                    .anyRequest().authenticated()
                    .and()   
                    .formLogin().defaultSuccessUrl("/acceso",true)
                    .and()
                    .logout().logoutSuccessUrl("/").permitAll();
            
    }
    
   
}
