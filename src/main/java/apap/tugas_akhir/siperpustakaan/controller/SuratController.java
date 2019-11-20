package apap.tugas_akhir.siperpustakaan.controller;


import apap.tugas_akhir.siperpustakaan.restservice.SuratRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SuratController {

    @Autowired
    private SuratRestService suratRestService;

    @RequestMapping(value = "/surat/pengajuan", method = RequestMethod.GET)
    public String tambahSuratForm(Model model) {
        suratRestService.postSuratPeringatantoSiTU();
        return "form-pengajuan-surat";
    }

    @RequestMapping(value = "/surat/pengajuan", method = RequestMethod.GET)
    public String tambahSuratFormSubmit(Model model) {
        suratRestService.postSuratPeringatantoSiTU();
        return "form-pengajuan-surat";
    }



}
