package apap.tugas_akhir.siperpustakaan.restservice;

import apap.tugas_akhir.siperpustakaan.rest.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Random;

@Service
@Transactional
public class UserRestServiceImpl implements UserRestService {
    private final WebClient webClient;

    public UserRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.siSivitasUrl).build();
    }

    @Override
    public String postUserToSiSivitas(UserDetail userDetail, String role) {
        if(role.equals("Guru")) {
            return postUserGuruToSivitas(userDetail).getUserDetail().getNama();
        } else if(role.equals("Siswa")) {
            return postUserSiswaToSivitas(userDetail).getUserDetail().getNama();
        } else {
            return postUserPegawaiToSivitas(userDetail).getUserDetail().getNama();
        }
    }

    private ResultDetail postUserGuruToSivitas(UserDetail userDetail) {
        userDetail.setNig(generateNIG(userDetail.getTanggalLahir(), userDetail.getIdUser()));
        return this.webClient
                .post()
                .uri("/teachers")
                .bodyValue(userDetail)
                .retrieve()
                .bodyToMono(ResultDetail.class)
                .block();
    }

    private ResultDetail postUserSiswaToSivitas(UserDetail userDetail) {
        userDetail.setNis(generateNIS(userDetail.getTanggalLahir(), userDetail.getIdUser()));
        return this.webClient
                .post()
                .uri("/students")
                .bodyValue(userDetail)
                .retrieve()
                .bodyToMono(ResultDetail.class)
                .block();
    }

    private ResultDetail postUserPegawaiToSivitas(UserDetail userDetail) {
        userDetail.setNip(generateNIP(userDetail.getTanggalLahir(), userDetail.getIdUser()));
        return this.webClient
                .post()
                .uri("/employees")
                .bodyValue(userDetail)
                .retrieve()
                .bodyToMono(ResultDetail.class)
                .block();
    }

    private String generateNIG(String tanggalLahir, String uuid) {
        return "G".concat(generateNomorInduk(tanggalLahir, uuid));
    }

    private String generateNIS(String tanggalLahir, String uuid) {
        return "S".concat(generateNomorInduk(tanggalLahir, uuid));
    }

    private String generateNIP(String tanggalLahir, String uuid) {
        return "P".concat(generateNomorInduk(tanggalLahir, uuid));
    }

    private String generateNomorInduk(String tanggalLahir, String uuid) {
        String[] date = tanggalLahir.split("-");
        return date[2].concat(date[1]).concat(date[0])
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
    public UserDetail getUserProfile(String uuid, String role) {
        String uri;
        if(role.equals("Guru")) uri = "/teachers/";
        else if(role.equals("Siswa")) uri = "/students/";
        else uri = "/employees/";
        try {
            ResultDetail resultDetail = this.webClient.get().uri(uri.concat(uuid))
                    .retrieve().bodyToMono(ResultDetail.class).block();
            return resultDetail != null ? resultDetail.getUserDetail() : null;
        } catch (Exception e) {
            return null;
        }
    }
}
