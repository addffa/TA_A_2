package apap.tugas_akhir.siperpustakaan.restservice;
import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.rest.SuratDetail;
import apap.tugas_akhir.siperpustakaan.rest.Base;
import reactor.core.publisher.Mono;

public interface SuratRestService {
    Mono<Base> postSuratPeringatantoSiTU(SuratDetail suratDetail, UserModel userModel);
}
