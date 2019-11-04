package apap.tugas_akhir.siperpustakaan.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "buku")
public class BukuModel implements Serializable {
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

    private Integer idJenisBuku;

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

    public Integer getIdJenisBuku() {
        return idJenisBuku;
    }

    public void setIdJenisBuku(Integer idJenisBuku) {
        this.idJenisBuku = idJenisBuku;
    }
}
