package apap.tugas_akhir.siperpustakaan.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "jenis_buku")
public class JenisBukuModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 200)
    @Column(name = "nama", nullable = false)
    private String nama;

    @OneToMany(mappedBy = "jenisBuku", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BukuModel> listBuku;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public List<BukuModel> getListBuku() {
        return listBuku;
    }

    public void setListBuku(List<BukuModel> listBuku) {
        this.listBuku = listBuku;
    }
}
