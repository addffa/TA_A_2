package apap.tugas_akhir.siperpustakaan.restservice;

import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.rest.Base;
import apap.tugas_akhir.siperpustakaan.rest.PengadaanDetail;
import reactor.core.publisher.Mono;

public interface RuanganRestService {
    Mono<Base> postPengadaansiRuangan(PengadaanDetail pengadaanDetail, UserModel userModel);
}
