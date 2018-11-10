package by.courses.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import by.courses.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class LoginConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetails;

	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetails).passwordEncoder(NoOpPasswordEncoder.getInstance()); // without passwordEncoder appears Exc
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/start", "/", "/festivals/**/**", "/css/**", "/register").permitAll() // acsses to everybody
				.antMatchers("/user/**").hasAnyRole("ROLE_USER") // users and admins
				.antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN") // only admins
				.anyRequest().authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/festivals").permitAll().and().logout().and().csrf().disable();

	}

}
