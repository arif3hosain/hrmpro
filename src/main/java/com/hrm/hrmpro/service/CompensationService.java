package com.hrm.hrmpro.service;

import com.hrm.hrmpro.domain.Compensation;
import com.hrm.hrmpro.model.CompensationDTO;
import com.hrm.hrmpro.repos.CompensationRepository;
import com.hrm.hrmpro.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CompensationService {

    private final CompensationRepository compensationRepository;

    public CompensationService(final CompensationRepository compensationRepository) {
        this.compensationRepository = compensationRepository;
    }

    public List<CompensationDTO> findAll() {
        final List<Compensation> compensations = compensationRepository.findAll(Sort.by("id"));
        return compensations.stream()
                .map(compensation -> mapToDTO(compensation, new CompensationDTO()))
                .toList();
    }

    public CompensationDTO get(final Long id) {
        return compensationRepository.findById(id)
                .map(compensation -> mapToDTO(compensation, new CompensationDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final CompensationDTO compensationDTO) {
        final Compensation compensation = new Compensation();
        mapToEntity(compensationDTO, compensation);
        return compensationRepository.save(compensation).getId();
    }

    public void update(final Long id, final CompensationDTO compensationDTO) {
        final Compensation compensation = compensationRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(compensationDTO, compensation);
        compensationRepository.save(compensation);
    }

    public void delete(final Long id) {
        compensationRepository.deleteById(id);
    }

    private CompensationDTO mapToDTO(final Compensation compensation,
            final CompensationDTO compensationDTO) {
        compensationDTO.setId(compensation.getId());
        compensationDTO.setBonus(compensation.getBonus());
        compensationDTO.setCommission(compensation.getCommission());
        compensationDTO.setAllowances(compensation.getAllowances());
        compensationDTO.setOvertimePay(compensation.getOvertimePay());
        compensationDTO.setEmployee(compensation.getEmployee());
        return compensationDTO;
    }

    private Compensation mapToEntity(final CompensationDTO compensationDTO,
            final Compensation compensation) {
        compensation.setBonus(compensationDTO.getBonus());
        compensation.setCommission(compensationDTO.getCommission());
        compensation.setAllowances(compensationDTO.getAllowances());
        compensation.setOvertimePay(compensationDTO.getOvertimePay());
        compensation.setEmployee(compensationDTO.getEmployee());
        return compensation;
    }

}
