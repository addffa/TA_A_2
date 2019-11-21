package apap.tugas_akhir.siperpustakaan.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/**").permitAll()
                // tambah buku
                .antMatchers("/buku/tambah").hasAnyRole("Pustakawan")
                .antMatchers(HttpMethod.POST, "/buku/tambah").hasAnyRole("Pustakawan")
                // ubah jumlah buku
                .antMatchers("/buku/update-jumlah").hasAnyRole("Pustakawan")
                .antMatchers(HttpMethod.POST, "/buku/update-jumlah").hasAnyRole("Pustakawan")
                // hapus buku
                .antMatchers("/buku/hapus").hasAnyRole("Pustakawan")
                .antMatchers(HttpMethod.POST, "/buku/hapus").hasAnyRole("Pustakawan")
                // mengajukan peminjaman
                // menampilkan daftar pengajuan peminjaman
                // mengubah status pengajuan peminjaman
                // mengajukan pengadaan
                // menampilkan daftar pengajuan pengadaan
                // menghapus pengajuan pengadaan
                // membuat surat peringatan overdue ke si tu
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll()
                .and()
                .csrf().disable();;
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }
}
