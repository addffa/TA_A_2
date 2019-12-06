package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.PeminjamanBukuModel;
import apap.tugas_akhir.siperpustakaan.model.UserModel;

import java.util.List;

public interface PeminjamanService {
    PeminjamanBukuModel getPinjamanbyId(Integer idPinjaman);

    PeminjamanBukuModel changeStatusPeminjaman(PeminjamanBukuModel peminjamanBukuModel);

    List<PeminjamanBukuModel> getListPeminjamanBuku();

    List<PeminjamanBukuModel> getListPeminjamanBukuByUser(UserModel user);
}
