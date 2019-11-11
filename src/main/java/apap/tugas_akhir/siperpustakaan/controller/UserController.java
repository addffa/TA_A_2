package apap.tugas_akhir.siperpustakaan.controller;

import apap.tugas_akhir.siperpustakaan.model.RoleModel;
import apap.tugas_akhir.siperpustakaan.model.UserModel;
import apap.tugas_akhir.siperpustakaan.service.RoleService;
import apap.tugas_akhir.siperpustakaan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/user/tambah", method = RequestMethod.GET)
    public String tambahUserForm(Model model) {
        model.addAttribute("user", new UserModel());
        model.addAttribute("listRole", getListRole());
        return "tambah-user";
    }

    @RequestMapping(value = "/user/tambah", method = RequestMethod.POST)
    public String tambahUser(@ModelAttribute UserModel user, RedirectAttributes redir) {
        if(userService.isUsernameExists(user.getUsername())) {
            redir.addFlashAttribute("msg", "username sudah ada!");
            redir.addFlashAttribute("type", "alert-danger");
        } else {
            userService.addUser(user);
            redir.addFlashAttribute("msg", "User berhasil disimpan!");
            redir.addFlashAttribute("type", "alert-info");
        }
        return "redirect:/user/tambah";
    }

    private List<RoleModel> getListRole() {
        return roleService.getListRole();
    }
}
