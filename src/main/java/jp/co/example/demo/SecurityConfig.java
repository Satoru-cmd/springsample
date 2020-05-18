package jp.co.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@EnableWebSecurity	//セキュリティ設定用クラスにつける
@Configuration	//Bean定義設定
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	//PasswordEncoder -> パスワードの暗号化、復元するインターフェイス
	//BcryptPasswordEncorder -> PasswordEncorderを実装してる
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private DataSource datasource;
	
	//ユーザーIDとパスワードを取得するSQL
	private static final String User_SQL = "SELECT user_id, password, true FROM m_user WHERE user_id=?";
	
	//ユーザーのロールを取得
	private static final String ROLE_SQL = "SELECT user_id, role FROM m_user WHERE user_id=?";
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		//静的リソースを削除
		//静的リソースへのアクセスにはセキュリティを適用しない
		web.ignoring().antMatchers("/webjars/**", "/css/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//直リンク禁止
		//ログイン不要ページの設定
		//webjars, css, /loing, /signupへのアクセス許可、それ以外は禁止
		//.antMatchers("<リンク先>").permitAll() -> アクセスできる
		http.authorizeRequests().antMatchers("/webjars/**").permitAll()
		.antMatchers("/css/**").permitAll().antMatchers("/login").permitAll()
		.antMatchers("/signup").permitAll().antMatchers("/rest/**").permitAll().antMatchers("/admin").hasAnyAuthority("ROLE_ADMIN")
		.anyRequest().authenticated();							//ROLE_ADMINしか/adminにアクセスできない
		//anyResuest() ->全てのリンク先
		//.authenticated() ->認証しないとアクセスできない
		
		
	//ログイン処理
		/*
		formLogin() -> ログイン処理
		loginProcessingUrl -> ログイン処理のパス
		loginPage -> ログインページの指定
		failureUrl -> ログイン失敗時の遷移先
		usernameParameter -> ログインページのユーザーID
		passwordParameter -> パスワード
		defaultSuccessUrl -> ログイン成功後の遷移先
		*/
		http.formLogin().loginProcessingUrl("/login").loginPage("/login").failureUrl("/login")
		.usernameParameter("userId")
		.passwordParameter("password")
		.defaultSuccessUrl("/home", true);
	
		
	//ログアウト処理
		/*　ユーザーセッションが破棄される
		 * .logoutUrl(<パス>) -> POSTメソッドでログアウト
		 * .logoutSuccessUrl(<パス>) -> ログアウト成功後の遷移先
		 */
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutUrl("/logout")
		.logoutSuccessUrl("/login");
		
		//CSRF対策を無効に設定　(デフォルトで有効になってる）
		//http.csrf().disable();
		
		//CSRGを無効にするURLを設定
		RequestMatcher csrfMatcher =new RestMatcher("/rest/**");
		//RestのみCSRF対策を無効
		http.csrf().requireCsrfProtectionMatcher(csrfMatcher);

	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		//ユーザーデータの取得
		//ログイン処理時のユーザー情報をDBから取得
		auth.jdbcAuthentication().dataSource(datasource).usersByUsernameQuery(User_SQL)
		.authoritiesByUsernameQuery(ROLE_SQL)
		.passwordEncoder(passwordEncoder());	//ログイン時のパスワードを復元
	}
}
