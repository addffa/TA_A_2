package apap.tugas_akhir.siperpustakaan.controller;

import apap.tugas_akhir.siperpustakaan.model.BukuModel;
import apap.tugas_akhir.siperpustakaan.model.JenisBukuModel;
import apap.tugas_akhir.siperpustakaan.service.BukuService;
import apap.tugas_akhir.siperpustakaan.service.JenisBukuService;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
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

    @RequestMapping(value = "/buku/daftarBuku", method = RequestMethod.GET)
    private String daftarBuku(Model model) {
        List<BukuModel> bukuList = bukuService.getBukuList();
        model.addAttribute("bukuList", bukuList);
        return "daftar-buku";
    }

    @RequestMapping(value = "/buku/tambahBuku", method = RequestMethod.GET)
    public String tambahBukuForm(Model model) {
        BukuModel newBuku = new BukuModel();
        JenisBukuModel jenisBuku = new JenisBukuModel();
        List<BukuModel> bukuList = new ArrayList<BukuModel>();
        bukuList.add(newBuku);
        jenisBuku.setListBuku(bukuList);
        List<JenisBukuModel> jenisBukuList = jenisBukuService.getJenisBukuList();
        model.addAttribute("jenisBukuList", jenisBukuList);
        model.addAttribute("buku", newBuku);
        return "form-tambah-buku";
    }

    @RequestMapping(value = "/buku/tambahBuku", method = RequestMethod.POST)
    public String tambahBukuSubmit(@ModelAttribute BukuModel buku, Model model) {
//        bukuService.tambahBuku(buku);
//        String successMessage = "Buku dengan judul " + buku.getJudul() + " berhasil ditambahkan";
//        String failMessage = "Buku dengan judul " + buku.getJudul() + " berhasil ditambahkan";
//        model.addAttribute("message", successMessage);
//        model.addAttribute("buku", buku);
//        return "form-tambah-buku";
        List<BukuModel> bukuList = bukuService.getBukuList();
        boolean isExist = false;
        for (BukuModel bukuModel : bukuList) {
            if (bukuModel.getJudul().equals(buku.getJudul()) && bukuModel.getPengarang().equals(buku.getPengarang())) {
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            bukuService.tambahBuku(buku);
            String successMessage = "Buku dengan judul " + buku.getJudul() + " berhasil ditambahkan";
            model.addAttribute("message", successMessage);
            model.addAttribute("buku", buku);
            return "form-tambah-buku";
        } else {
            String failMessage = "Buku dengan judul " + buku.getJudul() + " sudah ada";
            model.addAttribute("message", failMessage);
            model.addAttribute("buku", buku);
            return "form-tambah-buku";
        }
    }
}
