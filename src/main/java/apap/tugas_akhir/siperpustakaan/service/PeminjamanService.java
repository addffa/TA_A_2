package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.PeminjamanBukuModel;
import apap.tugas_akhir.siperpustakaan.model.UserModel;

import java.util.List;

public interface PeminjamanService {
    List<PeminjamanBukuModel> getListPeminjamanBuku();

    List<PeminjamanBukuModel> getListPeminjamanBukuByUser(UserModel user);
}
