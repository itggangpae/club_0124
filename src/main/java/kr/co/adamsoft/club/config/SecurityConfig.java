package kr.co.adamsoft.club.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder(){
        //암호화해서 평문과 비교는 할 수 있지만 복호화는 할 수 없는
        //클래스의 인스턴스를 생성해서 리턴
        return new BCryptPasswordEncoder();
    }
    //인가 설정 메서드 오버라이딩
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //sample/all 은 로그인 여부 와 상관없이 접근 가능
        //sample/member 는 USER 권한이 있어야 만 접근 가능
        http.authorizeRequests()
                .antMatchers("/sample/all").permitAll()
                .antMatchers("/sample/member").hasRole("USER");

        //권한이 없는 경우 로그인 페이지로 이동
        //로그인 요청 URL은 customlogin 이고 처리 URL은 login
        /*
        http.formLogin()
                .loginPage("/customlogin")
                .loginProcessingUrl("/login");
             
         */
        http.formLogin();

        //CSRF 토큰 비교하는 작업을 수행하지 않음
        http.csrf().disable();

    }

    //설정 메서드 - Jpa를 사용하면 주석 처리
    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception{
        //메모리에 유저 생성
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password("$2a$10$bR2rWdpBDabTyC4F8PZ41uHtbNIgyhztadk9erpt1SRcSOpjJ53aq")
                .roles("USER");
    }
    */

}
