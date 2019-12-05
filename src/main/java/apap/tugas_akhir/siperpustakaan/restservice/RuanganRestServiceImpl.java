package apap.tugas_akhir.siperpustakaan.restservice;

import apap.tugas_akhir.siperpustakaan.model.PengadaanBukuModel;
import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.rest.Base;
import apap.tugas_akhir.siperpustakaan.rest.Setting;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RuanganRestServiceImpl implements RuanganRestService{
    private final WebClient webClient;

    public RuanganRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.siRuanganUrl).build();
    }

    @Override
    public Mono<Base> postPengadaansiRuangan(PengadaanBukuModel pengadaanBukuModel, UserModel userModel) {
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("judul", pengadaanBukuModel.getJudul());
        data.add("pengarang", pengadaanBukuModel.getPengarang());
        data.add("penerbit", pengadaanBukuModel.getPenerbit());
        data.add("uuid", userModel.getUuid());
        return this.webClient.post().uri("")
                .syncBody(data)
                .retrieve()
                .bodyToMono(Base.class);
    }
}
