package apap.tugas_akhir.siperpustakaan.restcontroller;

import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.rest.Base;
import apap.tugas_akhir.siperpustakaan.rest.PengadaanDetail;
import apap.tugas_akhir.siperpustakaan.restservice.RuanganRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v1")
public class PengadaanRestController {

    @Autowired
    private RuanganRestService ruanganRestService;

    @RequestMapping("/pengadaan/tambah")
    private Mono<Base> postPengadaan(PengadaanDetail pengadaanDetail, UserModel userModel) {
        return ruanganRestService.postPengadaansiRuangan(pengadaanDetail, userModel);
    }

}
