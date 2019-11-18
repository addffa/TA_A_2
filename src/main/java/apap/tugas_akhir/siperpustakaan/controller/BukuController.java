package apap.tugas_akhir.siperpustakaan.controller;

import apap.tugas_akhir.siperpustakaan.model.BukuModel;
import apap.tugas_akhir.siperpustakaan.model.JenisBukuModel;
import apap.tugas_akhir.siperpustakaan.model.PeminjamanBukuModel;
import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.service.BukuService;
import apap.tugas_akhir.siperpustakaan.service.JenisBukuService;
import apap.tugas_akhir.siperpustakaan.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class BukuController {
    @Autowired
    BukuService bukuService;

    @Autowired
    JenisBukuService jenisBukuService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/buku", method = RequestMethod.GET)
    private String daftarBuku(Model model) {
        List<BukuModel> bukuList = bukuService.getListBuku();
        model.addAttribute("bukuList", bukuList);
        return "daftar-buku";
    }

    @RequestMapping(value = "/buku/tambah", method = RequestMethod.GET)
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
  
    @RequestMapping(value = "/buku/tambah", method = RequestMethod.POST)
    public String tambahBukuSubmit(@ModelAttribute BukuModel buku, Model model) {
        model.addAttribute("jenisBukuList", jenisBukuService.getJenisBukuList());
        if (!bukuService.cekJudulDanPengarangBuku(buku)) {
            bukuService.tambahBuku(buku);
            String successMessage = "Buku dengan judul " + buku.getJudul() + " berhasil ditambahkan";
            model.addAttribute("message", successMessage);
            model.addAttribute("buku", buku);
            return "form-tambah-buku";
        } else {
            String failMessage = "Buku dengan judul " + buku.getJudul() + " dan pengarang " + buku.getPengarang() + " sudah ada";
            model.addAttribute("message", failMessage);
            model.addAttribute("buku", buku);
            return "form-tambah-buku";
        }
    }
  
    @RequestMapping(value = "/buku/{idBuku}/update-jumlah", method = RequestMethod.GET)
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

    @RequestMapping(value = "/buku/{idBuku}/update-jumlah", method = RequestMethod.POST)
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

    @RequestMapping(value = "/buku/{idBuku}/hapus", method = RequestMethod.POST)
    public String hapusBuku(@PathVariable Integer idBuku){
        bukuService.hapusBuku(idBuku);
        return "hapus-buku";
    }

    @RequestMapping(value = "/buku/{idBuku}", method = RequestMethod.GET)
    private String detailBuku(@PathVariable Integer idBuku, Model model) {
        BukuModel buku = bukuService.getBukuById(idBuku).get();
        model.addAttribute("buku", buku);
        model.addAttribute("jumlahDipinjam", bukuService.jumlahBukuDipinjam(buku));
        return "detail-buku";
    }

    @RequestMapping(value = "/buku/pinjam/{idBuku}", method = RequestMethod.POST)
    private String pinjamBuku(@PathVariable Integer idBuku, Model model, RedirectAttributes redir) {
        BukuModel buku = bukuService.getBukuById(idBuku).get();
        int jumlahTotalBuku = buku.getJumlah();
        int jumlahBukuDipinjam = bukuService.jumlahBukuDipinjam(buku);
        if(jumlahTotalBuku - jumlahBukuDipinjam > 0) {
            UserModel user = userService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            bukuService.addPeminjamanBuku(buku, user);
        }
        String message = "Peminjaman buku dengan judul " + buku.getJudul() + " berhasil di ajukan";
        redir.addFlashAttribute("msg", message);
        redir.addFlashAttribute("type", "alert-info");
        return "redirect:/buku/" + idBuku;
    }
}
