package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.BukuModel;

import java.util.List;

public interface BukuService {
    void tambahBuku(BukuModel bukuModel);
    List<BukuModel> getBukuList();
}
