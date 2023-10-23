package CoffeeShopGroup.CoffeeShop.config;


import CoffeeShopGroup.CoffeeShop.security.jwt.JwtConfigurer;
import CoffeeShopGroup.CoffeeShop.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Security configuration class for JWT based Spring Security application.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
    private static final String LOGIN_ENDPOINT = "/api/v1/auth/login";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/client_Payment").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/static/***").permitAll()
                .antMatchers("/main").permitAll()
                .antMatchers("/api/v1/auth/").permitAll()
                .antMatchers(HttpMethod.POST, "/**").permitAll()
//                .antMatchers("/menu").permitAll()
                .antMatchers("/client").hasRole("USER")
                .antMatchers("/***").hasRole("ADMIN")


//                .and()
//                .authorizeRequests()
//                .antMatchers("/").hasAnyAuthority("USER", "ADMIN")
//                .anyRequest().authenticated()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/main")
                .and()
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("token")
                .deleteCookies("name")
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}


//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//
//    @Override
//    protected void  configure (AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication()
//                .withUser("Yuri")
//                .password("1111")
//                .roles("ADMIN")
//                .and()
//                .withUser("John")
//                .password("1111")
//                .roles("BARISTA")
//                .and()
//                .withUser("Alex")
//                .password("1111")
//                .roles("USER");
//
//    }
//
//    @Bean PasswordEncoder encoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Override
//    protected void  configure (HttpSecurity http) throws Exception{          // распределение доступа
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/admin").hasRole("ADMIN")
//                .antMatchers("/barista").hasAnyRole("ADMIN","BARISTA")
//                .antMatchers("/client").hasAnyRole("ADMIN","USER")
//                .antMatchers("/main").permitAll()
////                .antMatchers("/menu/add").permitAll()
////                .antMatchers("/add").permitAll()
//                .antMatchers("/static/***").permitAll()
//                .and().formLogin();
//    }
//
//
//}
//
