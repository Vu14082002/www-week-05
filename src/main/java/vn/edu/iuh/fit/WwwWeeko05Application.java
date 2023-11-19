package vn.edu.iuh.fit;

import com.neovisionaries.i18n.CountryCode;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import net.datafaker.providers.base.ProgrammingLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.fit.entities.*;
import vn.edu.iuh.fit.enums.SkillLevel;
import vn.edu.iuh.fit.enums.SkillType;
import vn.edu.iuh.fit.repositories.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class WwwWeeko05Application {
	private CompanyRepository companyRepository;
	private SkillRepository skillRepository;
	private JobSkillRepository  jobSkillRepository;
	private JobRepository jobRepository;
	private CandidateRepository candidateRepository;
	private  ExperienceRepository experienceRepository;
	private CandidateSkillRepository candidateSkillRepository;
	public static void main(String[] args) {
		SpringApplication.run(WwwWeeko05Application.class, args);
	}
	@Bean
	CommandLineRunner loadData(){
		Faker faker = new Faker();
		return  args -> {
			// Skill
			for (int i = 0; i < 50; i++) {
				Skill skill = new Skill();
				ProgrammingLanguage programmingLanguage = faker.programmingLanguage();
				skill.setSkillName(programmingLanguage.name());
				skill.setSkillDescription(programmingLanguage.creator());
				if(i % 3 ==0){
					skill.setType(SkillType.TECHNICAL_SKILL);
				}else if (i % 2 ==0){
					skill.setType(SkillType.UNSPECIFIC);
				}else{
					skill.setType(SkillType.SOFT_SKILL);
				}
				skillRepository.save(skill);
			}
			// create Company;
			for (int i = 0; i < 30; i++) {
				Company company = new Company();
				company.setAbout(faker.lorem().characters(15,20));
				company.setName(faker.company().name());
				company.setEmail(faker.internet().emailAddress());
				company.setPhone(faker.phoneNumber().cellPhone());
				company.setWebURL(faker.company().url());
				company.setAddress(createAddress());
				company.addJob(createJob());
				company.addJob(createJob());
				company.addJob(createJob());
				company.addJob(createJob());
				companyRepository.save(company);
			}
			// Job skill
			for (long i = 0; i < 30; i++) {
				for (int j = 0; j < 2; j++) {
					JobSkill jobSkill = new JobSkill();
					jobSkill.setJob(jobRepository.findById(i+1).get());
					List<Skill> skills = skillRepository.findAll();
					List<SkillLevel> skillLevels = Arrays.stream(SkillLevel.values()).toList();
					jobSkill.setSkill(skills.get(j+1));
					jobSkill.setSkillLevel(skillLevels.get(j+1));
					jobSkill.setMoreInfo(faker.lorem().characters(15,20));
					jobSkillRepository.save(jobSkill);
				}
			}
			// cabdidate
			for (int i = 0; i < 200; i++) {
				Candidate candidate = new Candidate();
				candidate.setAddress(createAddress());
				candidate.setDob(faker.date().birthday().toLocalDateTime().toLocalDate());
				candidate.setEmail(faker.internet().emailAddress());
				candidate.setFullName((faker.name().fullName()));
				candidate.setPhone(faker.phoneNumber().cellPhone());
				candidateRepository.save(candidate);
			}
			for (long i = 0; i < 10; i++) {
				Experience experience = new Experience();
				experience.setFromDate(LocalDate.of(1998,10,2));
				experience.setToDate(LocalDate.now());
				experience.setCandidate(candidateRepository.findById(i+1).get());
				experience.setRole(faker.job().position());
				experience.setWorkDescription(faker.lorem().characters(20,30));
				experience.setCompanyName(faker.company().name());
				experienceRepository.save(experience);
			}
			// candidate skill
			for (long i = 0; i <20; i++) {
				CandidateSkill candidateSkill = new CandidateSkill();
				candidateSkill.setSkillLevel(SkillLevel.PROFESSIONAL);
				candidateSkill.setCandidate(candidateRepository.findById(i+1).get());
				candidateSkill.setSkill(skillRepository.findById(faker.random().nextLong(1,20)).get());
				candidateSkill.setMoreInfo(faker.job().title());
				candidateSkillRepository.save(candidateSkill);
			}
		};
	}
	private Address createAddress(){
		Faker faker = new Faker();
		Address address = new Address();
		address.setStreet(faker.address().streetAddress());
		address.setCity(faker.address().city());
		address.setCountry(CountryCode.getByCode(faker.address().countryCode()));
		address.setNumber(faker.address().streetAddressNumber());
		address.setZipcode(faker.address().zipCode());
		return  address;
	}
	private Job createJob(){
		Faker faker = new Faker();
		net.datafaker.providers.base.Job jobFaker = faker.job();
		Job job = new Job();
		job.setName(jobFaker.field());
		job.setDescription(jobFaker.keySkills());
		return  job;
	}
}
