package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.PengadaanBukuModel;
import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.rest.AnggotaKoperasiDetail;

public interface PengadaanService {

    void tambahPengadaan(PengadaanBukuModel pengadaanModel, UserModel userModel, AnggotaKoperasiDetail anggotaKoperasiDetail);
}
