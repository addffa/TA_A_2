package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.PengadaanBukuModel;
import apap.tugas_akhir.siperpustakaan.model.UserModel;

import java.util.List;

public interface PengadaanService {
    List<PengadaanBukuModel> getListPengadaanBuku();

    List<PengadaanBukuModel> getListPengadaanBukuByUser(UserModel user);
}
