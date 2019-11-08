package apap.tugas_akhir.siperpustakaan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "peminjaman_buku")
public class PeminjamanBukuModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "tanggal_peminjaman", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tangganPeminjaman;

    @Temporal(TemporalType.DATE)
    @Column(name = "tanggal_pengembalian", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalPengembalian;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idBuku", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private BukuModel buku;

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

    public Date getTangganPeminjaman() {
        return tangganPeminjaman;
    }

    public void setTangganPeminjaman(Date tangganPeminjaman) {
        this.tangganPeminjaman = tangganPeminjaman;
    }

    public Date getTanggalPengembalian() {
        return tanggalPengembalian;
    }

    public void setTanggalPengembalian(Date tanggalPengembalian) {
        this.tanggalPengembalian = tanggalPengembalian;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BukuModel getBuku() {
        return buku;
    }

    public void setBuku(BukuModel buku) {
        this.buku = buku;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
