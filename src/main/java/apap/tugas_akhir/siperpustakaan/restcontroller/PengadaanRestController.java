package apap.tugas_akhir.siperpustakaan.restcontroller;

import apap.tugas_akhir.siperpustakaan.model.PengadaanBukuModel;
import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.rest.AnggotaKoperasiDetail;
import apap.tugas_akhir.siperpustakaan.rest.Base;
import apap.tugas_akhir.siperpustakaan.rest.PengadaanDetail;
import apap.tugas_akhir.siperpustakaan.restservice.KoperasiRestService;
import apap.tugas_akhir.siperpustakaan.service.PengadaanService;
import apap.tugas_akhir.siperpustakaan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1")
public class PengadaanRestController {

    @Autowired
    PengadaanService pengadaanService;

    @Autowired
    UserService userService;

    @Autowired
    KoperasiRestService koperasiRestService;

    @PostMapping(value= "/pengadaan/tambah")
    private Base<PengadaanBukuModel> addPengadaan(@Valid @RequestBody PengadaanDetail pengadaanDetail, BindingResult bindingResult){
        Base<PengadaanBukuModel> response;
        response = new Base<>();

        if (!bindingResult.hasFieldErrors()) {
            PengadaanBukuModel pengadaan = new PengadaanBukuModel();
            pengadaan.setJudul(pengadaanDetail.getJudul());
            pengadaan.setPengarang(pengadaanDetail.getPengarang());
            pengadaan.setPenerbit(pengadaanDetail.getPenerbit());
            pengadaan.setJumlah(pengadaanDetail.getJumlah());
            pengadaan.setHarga(pengadaanDetail.getHarga());

            UserModel userModel = userService.getByUsername("admin");
            AnggotaKoperasiDetail anggotaKoperasiDetail = koperasiRestService.getAnggotaKoperasi(userModel.getUuid(), userModel.getRole().getNama());
            pengadaanService.tambahPengadaan(pengadaan, userModel, anggotaKoperasiDetail);

            response.setStatus(200);
            response.setMessage("success");
            response.setResult(pengadaan);
        } else {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        }
        return response;
    }


}
