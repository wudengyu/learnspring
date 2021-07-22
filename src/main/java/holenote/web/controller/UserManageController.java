package holenote.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import holenote.business.entities.District;
import holenote.business.entities.Organization;
import holenote.business.entities.User;
import holenote.business.repository.DistrictRepository;
import holenote.business.repository.OrganizationRepository;
import holenote.business.repository.UserRepository;
import holenote.business.form.QueryUserForm;

@Controller
@RequestMapping("/manage/user")
@SessionAttributes("queryform")
public class UserManageController {

    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    public List<District> districts() {
        return districtRepository.findByEnabledTrue();
    }

    @ModelAttribute
    public List<Organization> organizations() {
        return organizationRepository.findByEnabledTrue();
    }

    @GetMapping
    public String showqueryuserform(Model model) {
        model.addAttribute("queryform",new QueryUserForm());
        return "manage/usermanage";
    }

    @PostMapping
    public String listuser(QueryUserForm queryparam,@PageableDefault(page=0,size=10) Pageable pageable,Model model) {
        Page<User> users = userRepository.findAll((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (!queryparam.getUsername().isEmpty()) {
                predicates.add(builder.like(root.get("username"), "%" + queryparam.getUsername() + "%"));
            }
            if (!"000000".equals(queryparam.getDistrict_code())) {
                predicates.add(builder.equal(root.get("district").<String>get("code"), queryparam.getDistrict_code()));
            }
            if (queryparam.getOrganization_id() != 0) {
                predicates.add(
                        builder.equal(root.get("organization").<Integer>get("id"), queryparam.getOrganization_id()));
            }
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        },pageable);
        model.addAttribute("queryform",queryparam);
        model.addAttribute("users",users);
        return "manage/usermanage";
    }
    @PostMapping(path="reset",params="userid")
    @ResponseBody
    public String resetpassword(@RequestParam int userid){
        return "sucess";
    }
}