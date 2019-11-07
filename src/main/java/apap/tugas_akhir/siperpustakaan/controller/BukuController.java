package apap.tugas_akhir.siperpustakaan.controller;

import apap.tugas_akhir.siperpustakaan.model.BukuModel;
import apap.tugas_akhir.siperpustakaan.model.JenisBukuModel;
import apap.tugas_akhir.siperpustakaan.service.BukuService;
import apap.tugas_akhir.siperpustakaan.service.JenisBukuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
