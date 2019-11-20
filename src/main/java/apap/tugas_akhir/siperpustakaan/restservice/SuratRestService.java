package apap.tugas_akhir.siperpustakaan.restservice;
import apap.tugas_akhir.siperpustakaan.rest.SuratDetail;
import reactor.core.publisher.Mono;

public interface SuratRestService {
    Mono<SuratDetail> postSuratPeringatantoSiTU();
}
