package apap.tugas_akhir.siperpustakaan.controller;

import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.rest.Base;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import reactor.core.publisher.Mono;

import java.util.Objects;

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
        Mono<Base> api = suratRestService.postSuratPeringatantoSiTU(suratDetail, user);
        if(Objects.requireNonNull(api.block()).getStatus() == 200){
            model.addAttribute("surat", suratDetail);
            model.addAttribute("msg", "Surat Pengajuan Overdue Berhasil Terkirim");
            return "form-pengajuan-surat";
        }
        model.addAttribute("surat", suratDetail);
        model.addAttribute("msg", "Surat Pengajuan Overdue Gagal Terkirim");
        return "form-pengajuan-surat";
    }

}
