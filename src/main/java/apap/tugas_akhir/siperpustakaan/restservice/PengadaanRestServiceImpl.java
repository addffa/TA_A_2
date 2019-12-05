package apap.tugas_akhir.siperpustakaan.restservice;

import apap.tugas_akhir.siperpustakaan.rest.PengadaanDetail;
import apap.tugas_akhir.siperpustakaan.rest.ResultDetail;
import apap.tugas_akhir.siperpustakaan.rest.Setting;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Transactional
public class PengadaanRestServiceImpl implements PengadaanRestService{

    private final WebClient webClient;

    public PengadaanRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.siKoperasiUrl).build();
    }

    @Override
    public PengadaanDetail getAnggotaKoperasi(String uuid, String role){
        String uri;
        if(role.equals("Guru")) uri = "/teachers/";
        else if(role.equals("Siswa")) uri = "/students/";
        else uri = "/employees/";
        try {
            ResultDetail resultDetail = this.webClient.get().uri(uri.concat(uuid))
                    .retrieve().bodyToMono(ResultDetail.class).block();
            return resultDetail != null ? resultDetail.getPengadaanDetail() : null;
        } catch (Exception e) {
            return null;
        }
    }
}
