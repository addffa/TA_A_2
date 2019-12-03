package apap.tugas_akhir.siperpustakaan.controller;

import apap.tugas_akhir.siperpustakaan.model.PengadaanBukuModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PengadaanController {

    @RequestMapping(value = "/pengadaan/tambah", method = RequestMethod.GET)
    public String tambahPengadaanForm(Model model) {
        PengadaanBukuModel newPengadaan = new PengadaanBukuModel();
        model.addAttribute("pengadaan", newPengadaan);
        return "form-pengajuan-pengadaan";
    }

    @RequestMapping(value = "/pengadaan/tambah", method = RequestMethod.POST)
    public String tambahPengadaanSubmit(@ModelAttribute PengadaanBukuModel pengadaanBukuModel, Model model) {


        pengadaanService.tambahBuku(pengadaanBukuModel);

        String successMessage = "Pengadaan buku dengan judul " + pengadaanBukuModel.getJudul() + " berhasil diajukan";
        model.addAttribute("message", successMessage);
        model.addAttribute("type", "alert-info");
        model.addAttribute("pengadaan", pengadaanBukuModel);
       return "form-pengajuan-pengadaan";
    }
}
