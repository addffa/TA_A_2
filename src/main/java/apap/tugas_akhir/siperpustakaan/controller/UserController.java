package apap.tugas_akhir.siperpustakaan.controller;

import apap.tugas_akhir.siperpustakaan.model.RoleModel;
import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.rest.PegawaiDetail;
import apap.tugas_akhir.siperpustakaan.rest.ResultDetail;
import apap.tugas_akhir.siperpustakaan.restservice.UserRestService;
import apap.tugas_akhir.siperpustakaan.service.RoleService;
import apap.tugas_akhir.siperpustakaan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRestService userRestService;

    @RequestMapping(value = "/user/tambah", method = RequestMethod.GET)
    public String tambahUserForm(Model model) {
        model.addAttribute("listRole", getListRole());
        return "tambah-user";
    }

    @RequestMapping(value = "/user/tambah", method = RequestMethod.POST)
    public String tambahUser(@ModelAttribute UserModel user,
                             @ModelAttribute PegawaiDetail pegawaiDetail,
                             RedirectAttributes redir) {
        if(userService.isUsernameExists(user.getUsername())) {
            redir.addFlashAttribute("msg", "username sudah ada!");
            redir.addFlashAttribute("type", "alert-danger");
        } else {
            try {
                pegawaiDetail.setIdUser(userService.addUser(user).getUuid());
                PegawaiDetail response = userRestService.postUserPegawaiToSiSivitas(pegawaiDetail);
                redir.addFlashAttribute("msg", "User" + response.getNama() +" berhasil disimpan!");
                redir.addFlashAttribute("type", "alert-info");
            } catch (Exception e) {
                e.printStackTrace();
                redir.addFlashAttribute("msg", "Unexpected error");
                redir.addFlashAttribute("type", "alert-danger");
            }
        }
        return "redirect:/user/tambah";
    }

    private List<RoleModel> getListRole() {
        return roleService.getListRole();
    }

    @RequestMapping(value = "/user/profil",  method = RequestMethod.GET)
    public String profilUser(Model model) {
        UserModel user = userService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        PegawaiDetail pegawai = userRestService.getUserProfile(user.getUuid());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("role", user.getRole().getNama());
        model.addAttribute("pegawai", pegawai);
        return "profil-user";
    }
}
