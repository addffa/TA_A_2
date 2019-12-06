package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.PengadaanBukuModel;

public interface PengadaanService {
    PengadaanBukuModel getPengadaanById(int idPengadaan);
    boolean hapusPengadaan(int idPengadaan, String role);
}
