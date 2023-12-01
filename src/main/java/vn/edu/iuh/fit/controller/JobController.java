package vn.edu.iuh.fit.controller;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.entities.Company;
import vn.edu.iuh.fit.entities.Job;
import vn.edu.iuh.fit.entities.JobSkill;
import vn.edu.iuh.fit.entities.Skill;
import vn.edu.iuh.fit.enums.SkillLevel;
import vn.edu.iuh.fit.repositories.CompanyRepository;
import vn.edu.iuh.fit.repositories.JobRepository;
import vn.edu.iuh.fit.repositories.JobSkillRepository;
import vn.edu.iuh.fit.repositories.SkillRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/jobs")
@AllArgsConstructor
public class JobController {
    private JobRepository jobRepository;
    private CompanyRepository companyRepository;
    private SkillRepository skillRepository;
    private JobSkillRepository jobSkillRepository;

    @GetMapping
    public String getAll(Model model,
                         @RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "size", defaultValue = "5") int size
    ) {
        Page<Job> jobs = jobRepository.findAll(PageRequest.of(page - 1, size));
        int totalPages = jobs.getTotalPages();
        List<Integer> pageNums = new ArrayList<>();
        for (int i = 0; i < totalPages; i++) {
            pageNums.add(i);
        }
        model.addAttribute("jobs", jobs);
        model.addAttribute("pageNums", pageNums);
        return "job/job-list";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable("id") long id, Model model) {
        Job job = jobRepository.findById(id).get();
        model.addAttribute("job", job);
        return "job/job-detail";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        List<Company> companies = companyRepository.findAll();
        List<Skill> skills = skillRepository.findAll();
        List<Skill> skillSelect = new ArrayList<>();
        List<SkillLevel> skillLevels = Arrays.stream(SkillLevel.values()).toList();
        model.addAttribute("job", new Job());
        model.addAttribute("companies", companies);
        model.addAttribute("skills", skills);
        model.addAttribute("skillSelect", skillSelect);
        model.addAttribute("skillLevels", skillLevels);
        return "job/job-form";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(Model model, @PathVariable long id) {
        Job job = jobRepository.findById(id).get();
        model.addAttribute("job", job);
        return "job/job-form";
    }

    @Transactional
    @PostMapping("/save")
    public String save(@ModelAttribute("job") Job job,
                       @RequestParam("selectedSkills") List<Long> skillID) {

        List<Skill> skills = new ArrayList<>();
        skillID.forEach(e -> {
            skills.add(skillRepository.findById(e).get());
        });
        List<JobSkill> jobSkills = new ArrayList<>();
        skills.forEach(e->{
            JobSkill jobSkill = new JobSkill();
            jobSkill.setSkill(e);
            jobSkill.setJob(job);
            jobSkill.setMoreInfo("");
            jobSkill.setSkillLevel(SkillLevel.BEGINER);
            jobSkills.add(jobSkill);
        });
        job.setJobSkills(jobSkills);
        jobRepository.save(job);
        return "redirect:/admin/jobs";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        jobRepository.delete(jobRepository.findById(id).get());
        return "redirect:/admin/jobs";
    }

}
