package apap.tugas_akhir.siperpustakaan.restcontroller;

import apap.tugas_akhir.siperpustakaan.model.UserDetailModel;
import apap.tugas_akhir.siperpustakaan.restservice.UserRestService;
import apap.tugas_akhir.siperpustakaan.restservice.UsersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {

    @Autowired
    private UsersDetailService usersDetailService;

    @Autowired
    private UserRestService userRestService;

    @RequestMapping("/users")
    private List<UserDetailModel> getUsersDetailusernameAndRole() {
        return usersDetailService.getAllUserUsernameAndRoles();
    }
}
