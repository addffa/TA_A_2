package apap.tugas_akhir.siperpustakaan.controller;

import apap.tugas_akhir.siperpustakaan.model.BukuModel;
import apap.tugas_akhir.siperpustakaan.model.JenisBukuModel;
import apap.tugas_akhir.siperpustakaan.service.BukuService;
import apap.tugas_akhir.siperpustakaan.service.JenisBukuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BukuController {
    @Autowired
    BukuService bukuService;

    @Autowired
    JenisBukuService jenisBukuService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String beranda(Model model) {
        return "beranda";
    }

    @RequestMapping(value = "/buku/tambahBuku", method = RequestMethod.GET)
    public String tambahBukuForm(Model model) {
        BukuModel newBuku = new BukuModel();
        List<JenisBukuModel> jenisBukuList = jenisBukuService.getJenisBukuList();
        model.addAttribute("jenisBukuList", jenisBukuList);
        model.addAttribute("buku", newBuku);
        return "form-tambah-buku";
    }

    @RequestMapping(value = "/buku/ubahJumlahBuku/{idBuku}", method = RequestMethod.GET)
    private String ubahJumlahBukuForm(
            @PathVariable Integer idBuku, RedirectAttributes redir, Model model
    ) {
        if(bukuService.getBukuById(idBuku).isPresent()) {
            BukuModel existingBuku = bukuService.getBukuById(idBuku).get();
            model.addAttribute("title", "Ubah Jumlah Buku");
            model.addAttribute("buku", existingBuku);
            return "form-ubah-jumlah-buku";
        } else {
            redir.addFlashAttribute("msg", "Buku tidak ada!");
            redir.addFlashAttribute("type", "alert-danger");
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/buku/ubahJumlahBuku/{idBuku}", method = RequestMethod.POST)
    private String ubahJumlahBukuSubmit(
            @PathVariable Integer idBuku,
            @ModelAttribute BukuModel buku, RedirectAttributes redir, Model model
    ) {
        BukuModel jumlahBukuBaru = bukuService.ubahJumlahBuku(buku);
        model.addAttribute("title", "Ubah Jumlah Buku Berhasil");
        redir.addFlashAttribute("msg", "Ubah jumlah buku berhasil");
        redir.addFlashAttribute("type", "alert-info");
        return "redirect:/" ;
    }

    @RequestMapping(value = "/buku/hapusBuku/{idBuku}", method = RequestMethod.POST)
    public String hapusBuku(@PathVariable Integer idBuku){
        bukuService.hapusBuku(idBuku);
        return "hapus-buku";
    }

}
