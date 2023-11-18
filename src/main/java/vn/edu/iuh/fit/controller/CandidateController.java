package vn.edu.iuh.fit.controller;

import com.neovisionaries.i18n.CountryCode;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.entities.Address;
import vn.edu.iuh.fit.entities.Candidate;
import vn.edu.iuh.fit.repositories.CandidateRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/candidates")
@AllArgsConstructor
public class CandidateController {
    private CandidateRepository candidateRepository;
    @GetMapping()
    public String getAll(@RequestParam(value = "page",defaultValue = "1") int page,
                         @RequestParam(value = "size",defaultValue = "10") int size,
                         Model model) {
        Page<Candidate> candidatePage = candidateRepository.findAll(PageRequest.of(page-1, size));
        int totalPages = candidatePage.getTotalPages();
        List<Integer> numpage = new ArrayList<>();
        for (int i = 0; i < totalPages; i++) {
            numpage.add(i);
        }
        model.addAttribute("candidatePage",candidatePage);
        model.addAttribute("numpage",numpage);
        return "candidate/candidate-list";
    }
    @GetMapping("/new")
    public  String showCandidateForm(Model model){
        Faker faker = new Faker();
        Candidate candidate = new Candidate();
        Address address = new Address();
        candidate.setAddress(address);
        List<CountryCode> codes = Arrays.stream(CountryCode.values()).toList();
        model.addAttribute("candidate",candidate);
        model.addAttribute("codes",codes);
        return "candidate/candidate-add-form";
    }
    @PostMapping("/save")
    public  String save(@ModelAttribute("candidate") Candidate candidate){
        candidateRepository.save(candidate);
        return "redirect:/admin/candidates";
    }
}
