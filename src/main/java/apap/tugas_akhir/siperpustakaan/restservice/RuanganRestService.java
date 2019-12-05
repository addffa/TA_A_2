package apap.tugas_akhir.siperpustakaan.restservice;

import apap.tugas_akhir.siperpustakaan.model.PengadaanBukuModel;
import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.rest.Base;
import reactor.core.publisher.Mono;

public interface RuanganRestService {
    Mono<Base> postPengadaansiRuangan(PengadaanBukuModel pengadaanBukuModel, UserModel userModel);
}
