package com.hrm.hrmpro.service;

import com.hrm.hrmpro.domain.Payroll;
import com.hrm.hrmpro.model.PayrollDTO;
import com.hrm.hrmpro.repos.PayrollRepository;
import com.hrm.hrmpro.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class PayrollService {

    private final PayrollRepository payrollRepository;

    public PayrollService(final PayrollRepository payrollRepository) {
        this.payrollRepository = payrollRepository;
    }

    public List<PayrollDTO> findAll() {
        final List<Payroll> payrolls = payrollRepository.findAll(Sort.by("id"));
        return payrolls.stream()
                .map(payroll -> mapToDTO(payroll, new PayrollDTO()))
                .toList();
    }

    public PayrollDTO get(final Long id) {
        return payrollRepository.findById(id)
                .map(payroll -> mapToDTO(payroll, new PayrollDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final PayrollDTO payrollDTO) {
        final Payroll payroll = new Payroll();
        mapToEntity(payrollDTO, payroll);
        return payrollRepository.save(payroll).getId();
    }

    public void update(final Long id, final PayrollDTO payrollDTO) {
        final Payroll payroll = payrollRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(payrollDTO, payroll);
        payrollRepository.save(payroll);
    }

    public void delete(final Long id) {
        payrollRepository.deleteById(id);
    }

    private PayrollDTO mapToDTO(final Payroll payroll, final PayrollDTO payrollDTO) {
        payrollDTO.setId(payroll.getId());
        payrollDTO.setPayPeriodStartDate(payroll.getPayPeriodStartDate());
        payrollDTO.setPayPeriodEndDate(payroll.getPayPeriodEndDate());
        payrollDTO.setGrossSalary(payroll.getGrossSalary());
        payrollDTO.setDeductions(payroll.getDeductions());
        payrollDTO.setNetSalary(payroll.getNetSalary());
        return payrollDTO;
    }

    private Payroll mapToEntity(final PayrollDTO payrollDTO, final Payroll payroll) {
        payroll.setPayPeriodStartDate(payrollDTO.getPayPeriodStartDate());
        payroll.setPayPeriodEndDate(payrollDTO.getPayPeriodEndDate());
        payroll.setGrossSalary(payrollDTO.getGrossSalary());
        payroll.setDeductions(payrollDTO.getDeductions());
        payroll.setNetSalary(payrollDTO.getNetSalary());
        return payroll;
    }

}
