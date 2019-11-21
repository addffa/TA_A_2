package apap.tugas_akhir.siperpustakaan.controller;

import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.rest.SuratDetail;
import apap.tugas_akhir.siperpustakaan.restservice.SuratRestService;
import apap.tugas_akhir.siperpustakaan.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SuratController {

    @Autowired
    private SuratRestService suratRestService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/surat/pengajuan", method = RequestMethod.GET)
    public String tambahSuratForm(Model model) {
        SuratDetail surat = new SuratDetail();
        model.addAttribute("surat", surat);
        return "form-pengajuan-surat";
    }

    @RequestMapping(value = "/surat/pengajuan", method = RequestMethod.POST)
    public String tambahSuratFormSubmit(@ModelAttribute SuratDetail suratDetail, Model model) {
        UserModel user = userService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        suratRestService.postSuratPeringatantoSiTU(suratDetail, user);

        model.addAttribute("msg", "Surat Pengajuan Overdue Berhasil Terkirim");
        return "form-pengajuan-surat";
    }

}
