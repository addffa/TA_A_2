package apap.tugas_akhir.siperpustakaan.restservice;

import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.rest.Base;
import apap.tugas_akhir.siperpustakaan.rest.PengadaanDetail;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;
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
    public Mono<Base> postPengadaansiRuangan(PengadaanDetail pengadaanDetail, UserModel userModel) {
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("judul", pengadaanDetail.getJudul());
        data.add("pengarang", pengadaanDetail.getPengarang());
        data.add("penerbit", pengadaanDetail.getPenerbit());
        data.add("jumlah", String.valueOf(pengadaanDetail.getJumlah()));
        data.add("harga", String.valueOf(pengadaanDetail.getHarga()));

        return this.webClient.post().uri("/api/v1/pengajuan-pengadaan")
                .syncBody(data)
                .retrieve()
                .bodyToMono(Base.class);
    }
}
