package com.hrm.hrmpro.service;

import com.hrm.hrmpro.domain.Applicant;
import com.hrm.hrmpro.model.ApplicantDTO;
import com.hrm.hrmpro.repos.ApplicantRepository;
import com.hrm.hrmpro.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ApplicantService {

    private final ApplicantRepository applicantRepository;

    public ApplicantService(final ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    public List<ApplicantDTO> findAll() {
        final List<Applicant> applicants = applicantRepository.findAll(Sort.by("id"));
        return applicants.stream()
                .map(applicant -> mapToDTO(applicant, new ApplicantDTO()))
                .toList();
    }

    public ApplicantDTO get(final Long id) {
        return applicantRepository.findById(id)
                .map(applicant -> mapToDTO(applicant, new ApplicantDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ApplicantDTO applicantDTO) {
        final Applicant applicant = new Applicant();
        mapToEntity(applicantDTO, applicant);
        return applicantRepository.save(applicant).getId();
    }

    public void update(final Long id, final ApplicantDTO applicantDTO) {
        final Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(applicantDTO, applicant);
        applicantRepository.save(applicant);
    }

    public void delete(final Long id) {
        applicantRepository.deleteById(id);
    }

    private ApplicantDTO mapToDTO(final Applicant applicant, final ApplicantDTO applicantDTO) {
        applicantDTO.setId(applicant.getId());
        applicantDTO.setFirstName(applicant.getFirstName());
        applicantDTO.setLastName(applicant.getLastName());
        applicantDTO.setEmail(applicant.getEmail());
        applicantDTO.setPhone(applicant.getPhone());
        applicantDTO.setResumeUrl(applicant.getResumeUrl());
        return applicantDTO;
    }

    private Applicant mapToEntity(final ApplicantDTO applicantDTO, final Applicant applicant) {
        applicant.setFirstName(applicantDTO.getFirstName());
        applicant.setLastName(applicantDTO.getLastName());
        applicant.setEmail(applicantDTO.getEmail());
        applicant.setPhone(applicantDTO.getPhone());
        applicant.setResumeUrl(applicantDTO.getResumeUrl());
        return applicant;
    }

    public boolean emailExists(final String email) {
        return applicantRepository.existsByEmailIgnoreCase(email);
    }

    public boolean phoneExists(final String phone) {
        return applicantRepository.existsByPhoneIgnoreCase(phone);
    }

}
