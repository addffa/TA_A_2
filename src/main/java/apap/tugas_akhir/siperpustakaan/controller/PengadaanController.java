package apap.tugas_akhir.siperpustakaan.controller;

import apap.tugas_akhir.siperpustakaan.model.PengadaanBukuModel;
import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.rest.AnggotaKoperasiDetail;
import apap.tugas_akhir.siperpustakaan.restservice.KoperasiRestService;
import apap.tugas_akhir.siperpustakaan.service.PengadaanService;

import apap.tugas_akhir.siperpustakaan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PengadaanController {

    @Autowired
    PengadaanService pengadaanService;

    @Autowired
    UserService userService;

    @Autowired
    KoperasiRestService koperasiRestService;


    @RequestMapping(value = "/pengadaan/tambah", method = RequestMethod.GET)
    public String tambahPengadaanForm(Model model) {
        PengadaanBukuModel newPengadaan = new PengadaanBukuModel();
        model.addAttribute("pengadaan", newPengadaan);
        return "form-pengajuan-pengadaan";
    }

    @RequestMapping(value = "/pengadaan/tambah", method = RequestMethod.POST)
    public String tambahPengadaanSubmit(@ModelAttribute PengadaanBukuModel pengadaanBukuModel, Model model) {
        UserModel user = userService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        AnggotaKoperasiDetail anggotaKoperasiDetail = koperasiRestService.getAnggotaKoperasi(user.getUuid(), user.getRole().getNama());
        pengadaanService.tambahPengadaan(pengadaanBukuModel, user, anggotaKoperasiDetail);

        String successMessage = "Pengadaan buku dengan judul " + pengadaanBukuModel.getJudul() + " berhasil diajukan";
        model.addAttribute("message", successMessage);
        model.addAttribute("type", "alert-info");
        model.addAttribute("pengadaan", pengadaanBukuModel);
        return "form-pengajuan-pengadaan";

    }

    @RequestMapping(value = "/pengadaan", method = RequestMethod.GET)
    private String daftarPengadaan(Model model) {
        UserModel user = userService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        boolean isPustakawan = user.getRole().getNama().equals("Pustakawan");
        model.addAttribute("isPustakawan", isPustakawan);
        model.addAttribute("isPeminjam", user.getRole().getNama().equals("Guru") ||
                user.getRole().getNama().equals("Siswa"));
        model.addAttribute("listPengadaan",
                isPustakawan ? pengadaanService.getListPengadaanBuku() : pengadaanService.getListPengadaanBukuByUser(user));
        return "daftar-pengadaan";
    }

    @RequestMapping(value = "/pengadaan/{idPengadaan}/hapus", method = RequestMethod.POST)
    public String hapusPengadaan(@PathVariable Integer idPengadaan, Model model) {
        UserModel user = userService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        String message;
        boolean isDeleted = pengadaanService.hapusPengadaan(idPengadaan, user.getRole().getNama());
        if(isDeleted) {
            message = "Pengadaan buku berhasil dihapus";
        } else {
            message = "Pengadaan buku gagal dihapus";
        }
        model.addAttribute("message", message);
        model.addAttribute("type", "alert-info");

        boolean isPustakawan = user.getRole().getNama().equals("Pustakawan");
        model.addAttribute("isPustakawan", isPustakawan);
        model.addAttribute("isPeminjam", user.getRole().getNama().equals("Guru") ||
                user.getRole().getNama().equals("Siswa"));
        model.addAttribute("listPengadaan",
                isPustakawan ? pengadaanService.getListPengadaanBuku() : pengadaanService.getListPengadaanBukuByUser(user));
        return "daftar-pengadaan";

    }
}


