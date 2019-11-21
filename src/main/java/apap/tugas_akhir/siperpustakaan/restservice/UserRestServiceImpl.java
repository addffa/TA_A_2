package apap.tugas_akhir.siperpustakaan.restservice;

import apap.tugas_akhir.siperpustakaan.rest.PegawaiDetail;
import apap.tugas_akhir.siperpustakaan.rest.ResultDetail;
import apap.tugas_akhir.siperpustakaan.rest.Setting;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Random;

@Service
@Transactional
public class UserRestServiceImpl implements UserRestService {
    private final WebClient webClient;

    public UserRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.siSivitasUrl).build();
    }

    @Override
    public PegawaiDetail postUserPegawaiToSiSivitas(PegawaiDetail pegawaiDetail) {
        pegawaiDetail.setNip(generateNIP(pegawaiDetail.getTanggalLahir(), pegawaiDetail.getIdUser()));
        return this.webClient
                .post()
                .uri("/employees")
                .bodyValue(pegawaiDetail)
                .retrieve()
                .bodyToMono(PegawaiDetail.class)
                .block();
    }

    private String generateNIP(String tanggalLahir, String uuid) {
        String[] date = tanggalLahir.split("-");
        return "P".concat(date[2]).concat(date[1]).concat(date[0])
                .concat(String.valueOf(randomChar()))
                .concat(String.valueOf(randomChar()))
                .concat(String.valueOf(randomNumber()))
                .concat(String.valueOf(randomNumber()))
                .concat(String.valueOf(randomNumber()))
                .concat(uuid);
    }

    private char randomChar() {
        Random random = new Random();
        return (char) (Math.floorMod(random.nextInt(), 26) + 'A');
    }

    private int randomNumber() {
        Random random = new Random();
        return Math.floorMod(random.nextInt(), 10);
    }

    @Override
    public PegawaiDetail getUserProfile(String uuid) {
        ResultDetail resultDetail = this.webClient.get().uri("/employees/"+uuid)
                .retrieve().bodyToMono(ResultDetail.class).block();
        if (resultDetail == null) {
            return null;
        }
        if (resultDetail.getStatus().equals(200)) {
            return resultDetail.getPegawaiDetail();
        }
        return null;
    }
}
