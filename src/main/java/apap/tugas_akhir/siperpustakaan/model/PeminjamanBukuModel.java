package apap.tugas_akhir.siperpustakaan.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Column(name = "tanggal_lahir")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalPengembalian;

    @NotNull
    @Column(name = "jumlah", nullable = false)
    private Integer status;

    private Integer idBuku;

    private String uuidUser;

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

    public Integer getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(Integer idBuku) {
        this.idBuku = idBuku;
    }

    public String getUuidUser() {
        return uuidUser;
    }

    public void setUuidUser(String uuidUser) {
        this.uuidUser = uuidUser;
    }
}
