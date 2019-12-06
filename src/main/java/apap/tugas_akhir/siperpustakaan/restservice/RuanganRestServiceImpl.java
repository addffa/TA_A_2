package apap.tugas_akhir.siperpustakaan.restservice;

import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.rest.PengadaanDetail;
import apap.tugas_akhir.siperpustakaan.rest.ResultDetail;
import apap.tugas_akhir.siperpustakaan.rest.Setting;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RuanganRestServiceImpl implements RuanganRestService{
    private final WebClient webClient;

    public RuanganRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.siRuanganUrl).build();
    }

    @Override
    public ResultDetail postPengadaansiRuangan(PengadaanDetail pengadaanDetail, UserModel userModel) {
        return this.webClient
                .post()
                .uri("/api/v1/pengajuan-pengadaan")
                .bodyValue(pengadaanDetail)
                .retrieve()
                .bodyToMono(ResultDetail.class)
                .block();
    }
}
