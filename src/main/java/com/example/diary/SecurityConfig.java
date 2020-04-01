package com.example.diary;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers("/webjars/**", "/css/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .authorizeRequests()
//                .antMatchers("/webjars/**").permitAll() //アクセス許可
//                .antMatchers("/css/**").permitAll() //アクセス許可
//                .antMatchers("/login").permitAll() //login.htmlへの直リンク許可
//                .anyRequest().authenticated();//上記以外へのアクセス不許可
//
//        httpSecurity
//                .formLogin()
//                .loginProcessingUrl("/login") //ログイン処理のパス
//                .loginPage("/login") //ログインページの指定
//                .failureUrl("/login") //ログイン失敗時の遷移先
//                .usernameParameter("userId") //ログインページのユーザーID
//                .passwordParameter("password") //ログインページのパスワード
//                .defaultSuccessUrl("/main", true); //ログイン成功時の遷移先

        //一時的に
        httpSecurity.csrf().disable();
    }
}
