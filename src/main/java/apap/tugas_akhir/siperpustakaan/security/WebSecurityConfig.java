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
                // api
                .antMatchers("/api/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/**").permitAll()
                // tambah buku
                .antMatchers("/buku/tambah").hasAnyAuthority("Pustakawan")
                .antMatchers(HttpMethod.POST, "/buku/tambah").hasAnyAuthority("Pustakawan")
                // ubah jumlah buku
                .antMatchers("/buku/*/update-jumlah").hasAnyAuthority("Pustakawan")
                .antMatchers(HttpMethod.POST, "/buku/*/update-jumlah").hasAnyAuthority("Pustakawan")
                // hapus buku
                .antMatchers("/buku/*/hapus").hasAnyAuthority("Pustakawan")
                .antMatchers(HttpMethod.POST, "/buku/*/hapus").hasAnyAuthority("Pustakawan")
                // mengajukan peminjaman
                .antMatchers("/buku/pinjam/**").hasAnyAuthority("Guru", "Siswa")
                .antMatchers(HttpMethod.POST, "/buku/pinjam/*").hasAnyAuthority("Guru", "Siswa")
                // menampilkan daftar pengajuan peminjaman
                .antMatchers("/peminjaman").hasAnyAuthority("Pustakawan", "Guru", "Siswa")
                // mengubah status pengajuan peminjaman
                .antMatchers("/peminjaman/update-status/*").hasAnyAuthority("Pustakawan")
                .antMatchers(HttpMethod.POST, "/peminjaman/update-status/*").hasAnyAuthority("Pustakawan")
                // mengajukan pengadaan
                .antMatchers("/pengadaan/tambah").hasAnyAuthority("Pustakawan", "Guru", "Siswa")
                .antMatchers(HttpMethod.POST, "/pengadaan/tambah").hasAnyAuthority("Pustakawan", "Guru", "Siswa")
                // menampilkan daftar pengajuan pengadaan
                .antMatchers("/pengadaan").hasAnyAuthority("Pustakawan", "Guru", "Siswa")
                // menghapus pengajuan pengadaan
                .antMatchers("/pengadaan/*/hapus").hasAnyAuthority("Pustakawan", "Guru", "Siswa")
                .antMatchers(HttpMethod.POST, "/pengadaan/*/hapus").hasAnyAuthority("Pustakawan", "Guru", "Siswa")
                // membuat surat peringatan overdue ke si tu
                .antMatchers("/surat/pengajuan").hasAnyAuthority("Pustakawan")
                .antMatchers(HttpMethod.POST, "/surat/pengajuan").hasAnyAuthority("Pustakawan")
                // tambah user
                .antMatchers("/user/tambah").hasAnyAuthority("Pustakawan")
                .antMatchers(HttpMethod.POST, "/user/tambah").hasAnyAuthority("Pustakawan")
                // login logout
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
