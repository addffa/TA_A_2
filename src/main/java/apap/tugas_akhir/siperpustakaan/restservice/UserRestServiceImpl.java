package apap.tugas_akhir.siperpustakaan.restservice;

import apap.tugas_akhir.siperpustakaan.rest.PegawaiDetail;
import apap.tugas_akhir.siperpustakaan.rest.Setting;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class UserRestServiceImpl implements UserRestService {
    @Override
    @Transactional
    public void postUserPegawaiToSiSivitas(PegawaiDetail pegawaiDetail) {
        pegawaiDetail.setNip(generateNIP(pegawaiDetail.getTanggalLahir(), pegawaiDetail.getIdUser()));

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PegawaiDetail> request = new HttpEntity<>(pegawaiDetail, headers);
        restTemplate.exchange(Setting.siSivitasUrl.concat("/employees"), HttpMethod.POST, request, PegawaiDetail.class);
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
}
