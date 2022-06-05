package thymeleaf.controller;

import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.model.Company;

import thymeleaf.service.CompanyService;
import thymeleaf.service.GroupService;

import java.util.List;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    public final GroupService groupService;


    public CompanyController(CompanyService companyService, GroupService groupService) {
        this.companyService = companyService;
        this.groupService = groupService;
    }

    @GetMapping
    public String getCompaniesPage(Model model){
        List<Company> companies = companyService.getAllCompany();
        model.addAttribute("allCompany",companies);
        return "/companies/getCompanyPage";
    }

    @GetMapping("/saveCompany")
    public String addCompany(Model model){
        model.addAttribute("newCompany", new Company());
        return "companies/save";
    }

    @PostMapping("/saveCompany")
    public String saveCompany(@ModelAttribute("company") Company company){
        companyService.saveCompany(company);
        return "redirect:/companies";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") Long id) {
        model.addAttribute("company",companyService.findById(id));
        return "companies/update";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("company")Company company,@PathVariable("id") Long id) throws NotFoundException{
        companyService.updateById(company, id);
        return "redirect:/companies";

    }

    @GetMapping("/delete/{id}")
    public String delete(@ModelAttribute("companyId")@PathVariable("id") Long id) throws NotFoundException {
        companyService.deleteById(id);
        return "redirect:/companies";
    }

}
