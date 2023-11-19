package vn.edu.iuh.fit.controller;

import com.neovisionaries.i18n.CountryCode;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.nodes.ScalarNode;
import vn.edu.iuh.fit.entities.Company;
import vn.edu.iuh.fit.repositories.CompanyRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/companies")
@AllArgsConstructor
public class CompanyController {
    private CompanyRepository companyRepository;

    @GetMapping
    public String getAll(Model model,
                         @RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "5") int size
    ) {
        Page<Company> companyPage = companyRepository.findAll(PageRequest.of(page - 1, size));
        int totalPages = companyPage.getTotalPages();
        List<Integer> pageNums = new ArrayList<>();
        for (int i = 0; i < totalPages; i++) {
            pageNums.add(i);
        }
        model.addAttribute("companyPage", companyPage);
        model.addAttribute("pageNums", pageNums);
        return "company/company-list";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable("id") long id, Model model) {
        Company company = companyRepository.findById(id).get();
        model.addAttribute("company", company);
        return "company/company-view-detail";
    }

    @GetMapping("/new")
    public String showFormAdd(Model model) {
        Company company = new Company();
        List<CountryCode> codes = Arrays.stream(CountryCode.values()).toList();
        model.addAttribute("company", company);
        model.addAttribute("codes", codes);
        model.addAttribute("country", "");
        return "company/company-form";
    }
    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable("id") long id,Model model){
        Company company = companyRepository.findById(id).get();
        List<CountryCode> codes = Arrays.stream(CountryCode.values()).toList();
        String country = company.getAddress().getCountry().name();
        model.addAttribute("company",company);
        model.addAttribute("codes", codes);
        model.addAttribute("country", country);
        return "company/company-form";
    }

    @PostMapping("/save")
    public  String save(@ModelAttribute("company") Company company){
        companyRepository.save(company);
        return "redirect:/admin/companies";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")long id){
        companyRepository.delete(companyRepository.findById(id).get());
        return  "redirect:/admin/companies";
    }
}
