package vn.edu.iuh.fit.controller;

import com.neovisionaries.i18n.CountryCode;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.entities.Address;
import vn.edu.iuh.fit.entities.Candidate;
import vn.edu.iuh.fit.repositories.CandidateRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/candidates")
@AllArgsConstructor
public class CandidateController {
    private CandidateRepository candidateRepository;
    @GetMapping()
    public String getAll(@RequestParam(value = "page",defaultValue = "1") int page,
                         @RequestParam(value = "size",defaultValue = "5") int size,
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

    @GetMapping("/{id}")
    public String getOne(@PathVariable("id") long id, Model model) {
        Candidate candidate = candidateRepository.findById(id).get();
        model.addAttribute("candidate",candidate);
        return "candidate/candidate-detail";
    }
    @GetMapping("/new")
    public  String showCandidateForm(Model model){
        Faker faker = new Faker();
        Candidate candidate = new Candidate();
        List<CountryCode> codes = Arrays.stream(CountryCode.values()).toList();
        model.addAttribute("candidate",candidate);
        model.addAttribute("codes",codes);
        return "candidate/candidate-add-form";
    }
    @GetMapping("/update/{id}")
    public  String showCandidateForm(@PathVariable("id") long id,Model model){
        Faker faker = new Faker();
        Candidate candidate = candidateRepository.findById(id).get();
        List<CountryCode> codes = Arrays.stream(CountryCode.values()).toList();
        System.out.println(candidate.getDob());
        model.addAttribute("candidate",candidate);
        model.addAttribute("codes",codes);
        model.addAttribute("code",candidate.getAddress().getCountry().getName());
        return "candidate/candidate-update-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        candidateRepository.delete(candidateRepository.findById(id).get());
        return "redirect:/admin/candidates";
    }
    @PostMapping("/save")
    public  String save(@ModelAttribute("candidate") Candidate candidate){
        candidateRepository.save(candidate);
        return "redirect:/admin/candidates";
    }
}
