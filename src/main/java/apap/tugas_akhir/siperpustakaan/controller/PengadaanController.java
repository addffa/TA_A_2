package apap.tugas_akhir.siperpustakaan.controller;

import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.service.PengadaanService;
import apap.tugas_akhir.siperpustakaan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PengadaanController {
    @Autowired
    PengadaanService pengadaanService;

    @Autowired
    UserService userService;

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
}
