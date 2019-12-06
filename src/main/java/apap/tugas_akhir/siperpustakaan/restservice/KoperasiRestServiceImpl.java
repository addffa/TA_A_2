package apap.tugas_akhir.siperpustakaan.restservice;

import apap.tugas_akhir.siperpustakaan.rest.AnggotaKoperasiDetail;
import apap.tugas_akhir.siperpustakaan.rest.ResultDetail;
import apap.tugas_akhir.siperpustakaan.rest.Setting;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Transactional
public class KoperasiRestServiceImpl implements KoperasiRestService {

    private final WebClient webClient;

    public KoperasiRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.siKoperasiUrl).build();
    }

    @Override
    public AnggotaKoperasiDetail getAnggotaKoperasi(String uuid, String role){
        String uri;
        //if(role.equals("Guru"))
        uri = "/teachers/";
        try {
            ResultDetail resultDetail = this.webClient.get().uri(uri.concat(uuid))
                    .retrieve().bodyToMono(ResultDetail.class).block();
            return resultDetail != null ? resultDetail.getAnggotaKoperasiDetail() : null;
        } catch (Exception e) {
            return null;
        }
    }

}
