package apap.tugas_akhir.siperpustakaan.controller;

import apap.tugas_akhir.siperpustakaan.service.BukuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buku")
public class BukuController {
    @Autowired
    private BukuService bukuService;

    @GetMapping("/{idBuku}")
    private String detailBuku(@PathVariable Integer idBuku, Model model) {
        model.addAttribute("buku", bukuService.findBukuById(idBuku));
        return "detail-buku";
    }

    @GetMapping("")
    private String daftarBuku(Model model) {
        model.addAttribute("listBuku", bukuService.getListBuku());
        return "daftar-buku";
    }
}
