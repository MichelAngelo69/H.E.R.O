package pl.mb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.sql.DataSource;


@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private AccessDeniedHandler accessDeniedHandler;


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**").anyRequest();
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers( "/about", "/allHeroes").permitAll()
                .antMatchers("/admin/**", "/addHero","/", "/home").hasAnyRole("ADMIN")
                .antMatchers("/user/**", "/addHero", "/", "/home").hasAnyRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/home")
                .loginPage("/login").failureUrl("/login?error=true")
         //       .loginProcessingUrl("/Login")
                .usernameParameter("email")
                .passwordParameter("name")
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("pass1").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("user").password("pass2").roles("USER");
}}