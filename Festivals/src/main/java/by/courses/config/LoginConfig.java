package by.courses.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class LoginConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/start", "/", "/festivals").permitAll()
				// .antMatchers("/login").hasAnyRole("USER", "ADMIN")
				// .antMatchers("/getEmployees").hasAnyRole("USER",
				// "ADMIN").antMatchers("/addNewEmployee").hasAnyRole("ADMIN")
				.anyRequest().authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/festivals").permitAll().and().logout().permitAll();

		// http.csrf().disable();
	}

}
