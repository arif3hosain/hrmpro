package com.hrm.hrmpro.service;


import com.hrm.hrmpro.domain.Leave;
import com.hrm.hrmpro.model.LeaveDTO;
import com.hrm.hrmpro.repos.LeaveRepository;
import com.hrm.hrmpro.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;

    public LeaveService(final LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    public List<LeaveDTO> findAll() {
        final List<Leave> leaves = leaveRepository.findAll(Sort.by("id"));
        return leaves.stream()
                .map(leave -> mapToDTO(leave, new LeaveDTO()))
                .toList();
    }

    public LeaveDTO get(final Long id) {
        return leaveRepository.findById(id)
                .map(leave -> mapToDTO(leave, new LeaveDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final LeaveDTO leaveDTO) {
        final Leave leave = new Leave();
        mapToEntity(leaveDTO, leave);
        return leaveRepository.save(leave).getId();
    }

    public void update(final Long id, final LeaveDTO leaveDTO) {
        final Leave leave = leaveRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(leaveDTO, leave);
        leaveRepository.save(leave);
    }

    public void delete(final Long id) {
        leaveRepository.deleteById(id);
    }

    private LeaveDTO mapToDTO(final Leave leave, final LeaveDTO leaveDTO) {
        leaveDTO.setId(leave.getId());
        leaveDTO.setEmployee(leave.getEmployee());
        leaveDTO.setStartDate(leave.getStartDate());
        leaveDTO.setEndDate(leave.getEndDate());
        leaveDTO.setStatus(leave.getStatus());
        leaveDTO.setEmployee(leave.getEmployee());
        leaveDTO.setReason(leave.getReason());
        return leaveDTO;
    }

    private Leave mapToEntity(final LeaveDTO leaveDTO, final Leave leave) {
        leave.setEmployee(leaveDTO.getEmployee());
        leave.setStartDate(leaveDTO.getStartDate());
        leave.setEndDate(leaveDTO.getEndDate());
        leave.setStatus(leaveDTO.getStatus());
        leave.setEmployee(leaveDTO.getEmployee());
        leave.setReason(leaveDTO.getReason());
        return leave;
    }

}
