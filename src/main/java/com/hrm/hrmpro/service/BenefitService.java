package com.hrm.hrmpro.service;

import com.hrm.hrmpro.domain.Benefit;
import com.hrm.hrmpro.model.BenefitDTO;
import com.hrm.hrmpro.repos.BenefitRepository;
import com.hrm.hrmpro.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class BenefitService {

    private final BenefitRepository benefitRepository;

    public BenefitService(final BenefitRepository benefitRepository) {
        this.benefitRepository = benefitRepository;
    }

    public List<BenefitDTO> findAll() {
        final List<Benefit> benefits = benefitRepository.findAll(Sort.by("id"));
        return benefits.stream()
                .map(benefit -> mapToDTO(benefit, new BenefitDTO()))
                .toList();
    }

    public BenefitDTO get(final Long id) {
        return benefitRepository.findById(id)
                .map(benefit -> mapToDTO(benefit, new BenefitDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final BenefitDTO benefitDTO) {
        final Benefit benefit = new Benefit();
        mapToEntity(benefitDTO, benefit);
        return benefitRepository.save(benefit).getId();
    }

    public void update(final Long id, final BenefitDTO benefitDTO) {
        final Benefit benefit = benefitRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(benefitDTO, benefit);
        benefitRepository.save(benefit);
    }

    public void delete(final Long id) {
        benefitRepository.deleteById(id);
    }

    private BenefitDTO mapToDTO(final Benefit benefit, final BenefitDTO benefitDTO) {
        benefitDTO.setId(benefit.getId());
        benefitDTO.setName(benefit.getName());
        benefitDTO.setDescription(benefit.getDescription());
        benefitDTO.setAmount(benefit.getAmount());
        return benefitDTO;
    }

    private Benefit mapToEntity(final BenefitDTO benefitDTO, final Benefit benefit) {
        benefit.setName(benefitDTO.getName());
        benefit.setDescription(benefitDTO.getDescription());
        benefit.setAmount(benefitDTO.getAmount());
        return benefit;
    }

}
