package apap.tugas_akhir.siperpustakaan.service;

import apap.tugas_akhir.siperpustakaan.model.BukuModel;
import apap.tugas_akhir.siperpustakaan.model.PeminjamanBukuModel;

public interface PinjamanService {
   PeminjamanBukuModel getPinjamanbyId(Integer idPinjaman);

   PeminjamanBukuModel changeStatusPeminjaman(PeminjamanBukuModel peminjamanBukuModel);
}
