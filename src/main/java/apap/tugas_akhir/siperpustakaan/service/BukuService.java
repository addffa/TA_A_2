package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.BukuModel;
import apap.tugas_akhir.siperpustakaan.model.PeminjamanBukuModel;
import apap.tugas_akhir.siperpustakaan.model.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BukuService {
    void tambahBuku(BukuModel bukuModel);

    Optional<BukuModel> getBukuById(Integer idBuku);

    BukuModel ubahJumlahBuku(BukuModel bukuModel);

    void hapusBuku(Integer idBuku);

    List<BukuModel> getListBuku();

    int jumlahBukuDipinjam(int idBuku);

    boolean cekJudulDanPengarangBuku(BukuModel buku);

    void addPeminjamanBuku(BukuModel buku, UserModel user);
}
