package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.BukuModel;

import java.util.List;
import java.util.Optional;

public interface BukuService {
    void tambahBuku(BukuModel bukuModel);

    Optional<BukuModel> getBukuById(Integer idBuku);

    BukuModel ubahJumlahBuku(BukuModel bukuModel);

    void hapusBuku(Integer idBuku);

    List<BukuModel> getListBuku();

    int jumlahBukuDipinjam(BukuModel buku);

    boolean cekBuku(BukuModel buku);
}
