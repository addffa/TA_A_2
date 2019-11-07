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
    @Column(name = "jenis_buku", nullable = false)
    private String jenis_buku;

    @OneToMany(mappedBy = "jenisBukuModel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BukuModel> listBuku;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJenis_buku() {
        return jenis_buku;
    }

    public void setJenis_buku(String jenis_buku) {
        this.jenis_buku = jenis_buku;
    }

    public List<BukuModel> getListBuku() {
        return listBuku;
    }

    public void setListBuku(List<BukuModel> listBuku) {
        this.listBuku = listBuku;
    }
}
