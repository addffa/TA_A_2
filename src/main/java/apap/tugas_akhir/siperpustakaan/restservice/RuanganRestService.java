package apap.tugas_akhir.siperpustakaan.restservice;

import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.rest.PengadaanDetail;
import apap.tugas_akhir.siperpustakaan.rest.ResultDetail;

public interface RuanganRestService {
    ResultDetail postPengadaansiRuangan(PengadaanDetail pengadaanDetail, UserModel userModel);
}
