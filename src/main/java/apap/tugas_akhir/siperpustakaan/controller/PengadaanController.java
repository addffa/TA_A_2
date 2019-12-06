package apap.tugas_akhir.siperpustakaan.controller;

import apap.tugas_akhir.siperpustakaan.model.PengadaanBukuModel;
import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.rest.AnggotaKoperasiDetail;
import apap.tugas_akhir.siperpustakaan.rest.Base;
import apap.tugas_akhir.siperpustakaan.rest.PengadaanDetail;
import apap.tugas_akhir.siperpustakaan.rest.ResultDetail;
import apap.tugas_akhir.siperpustakaan.restservice.KoperasiRestService;
import apap.tugas_akhir.siperpustakaan.restservice.RuanganRestService;
import apap.tugas_akhir.siperpustakaan.service.PengadaanService;

import apap.tugas_akhir.siperpustakaan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Controller
public class PengadaanController {

    @Autowired
    PengadaanService pengadaanService;

    @Autowired
    UserService userService;

    @Autowired
    KoperasiRestService koperasiRestService;

    @Autowired
    RuanganRestService ruanganRestService;

    @RequestMapping(value = "/pengadaan/tambah", method = RequestMethod.GET)
    public String tambahPengadaanForm(Model model) {
        PengadaanBukuModel newPengadaan = new PengadaanBukuModel();
        model.addAttribute("pengadaan", newPengadaan);
        return "form-pengajuan-pengadaan";
    }

    @RequestMapping(value = "/pengadaan/tambah", method = RequestMethod.POST)
    public String tambahPengadaanSubmit(@ModelAttribute PengadaanBukuModel pengadaanBukuModel, PengadaanDetail pengadaanDetail, Model model) {
        UserModel user = userService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        AnggotaKoperasiDetail anggotaKoperasiDetail = koperasiRestService.getAnggotaKoperasi(user.getUuid(), user.getRole().getNama());
        pengadaanService.tambahPengadaan(pengadaanBukuModel, user, anggotaKoperasiDetail);

        Mono<Base> api = ruanganRestService.postPengadaansiRuangan(pengadaanDetail, user);
        if(Objects.requireNonNull(api.block()).getStatus() == 200){
            pengadaanService.tambahPengadaan(pengadaanBukuModel, user, anggotaKoperasiDetail);
            String successMessage = "Pengadaan buku dengan judul " + pengadaanBukuModel.getJudul() + " berhasil diajukan";
            model.addAttribute("message", successMessage);
            model.addAttribute("type", "alert-info");
            model.addAttribute("pengadaan", pengadaanBukuModel);
            return "form-pengajuan-pengadaan";
        }

        model.addAttribute("pengadaan", pengadaanBukuModel);
        model.addAttribute("msg", "Unexpected error");
        model.addAttribute("type", "alert-danger");
        return "form-pengajuan-pengadaan";
    }
}


