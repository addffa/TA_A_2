package apap.tugas_akhir.siperpustakaan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import apap.tugas_akhir.siperpustakaan.service.BukuService;

@Controller
public class BukuController {

    @Qualifier("bukuServiceImpl")
    @Autowired
    private BukuService bukuService;

    //Menghapus buku
    @RequestMapping("buku/hapus/{idBuku}")
    public String hapusBuku(@PathVariable Integer idBuku){
        bukuService.hapusBuku(idBuku);
        return "hapus-buku";
    }
}
