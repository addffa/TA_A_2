package apap.tugas_akhir.siperpustakaan.restservice;
import apap.tugas_akhir.siperpustakaan.rest.SuratDetail;
import apap.tugas_akhir.siperpustakaan.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class SuratRestServiceImpl implements SuratRestService {

    private final WebClient webClient;

    //customer service
    public SuratRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.siTuUrl).build();
    }

    @Override
    public Mono<SuratDetail> postSuratPeringatantoSiTU() {
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("jenis", "toko elektronik");
        data.add("keterangan", "200");
        return this.webClient.post().uri("/surat/tambah")
                .syncBody(data)
                .retrieve()
                .bodyToMono(SuratDetail.class);
    }
}
