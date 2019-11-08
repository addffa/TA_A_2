package apap.tugas_akhir.siperpustakaan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "pengadaan_buku")
public class PengadaanBukuModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 200)
    @Column(name = "judul", nullable = false)
    private String judul;

    @NotNull
    @Size(max = 200)
    @Column(name = "pengarang", nullable = false)
    private String pengarang;

    @NotNull
    @Size(max = 200)
    @Column(name = "penerbit", nullable = false)
    private String penerbit;

    @NotNull
    @Column(name = "jumlah", nullable = false)
    private Integer jumlah;

    @NotNull
    @Column(name = "harga", nullable = false)
    private Integer harga;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "uuidUser", referencedColumnName = "uuid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
