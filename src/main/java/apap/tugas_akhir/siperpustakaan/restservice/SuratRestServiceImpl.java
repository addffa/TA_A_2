package apap.tugas_akhir.siperpustakaan.restservice;
import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.rest.SuratDetail;
import apap.tugas_akhir.siperpustakaan.rest.Base;
import apap.tugas_akhir.siperpustakaan.rest.Setting;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SuratRestServiceImpl implements SuratRestService {

    private final WebClient webClient;

    public SuratRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.siTuUrl).build();
    }

    @Override
    public Mono<Base> postSuratPeringatantoSiTU(SuratDetail surat, UserModel userModel) {
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("jenis", surat.getJenis());
        data.add("keterangan", surat.getKeterangan());
        data.add("uuid", userModel.getUuid());
        data.add("nama", userModel.getUsername());
        return this.webClient.post().uri("/api/v1/pengajuan-surat")
                .syncBody(data)
                .retrieve()
                .bodyToMono(Base.class);
    }
}
