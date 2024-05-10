package com.hrm.hrmpro.service;

import com.hrm.hrmpro.domain.Goal;
import com.hrm.hrmpro.model.GoalDTO;
import com.hrm.hrmpro.repos.GoalRepository;
import com.hrm.hrmpro.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class GoalService {

    private final GoalRepository goalRepository;

    public GoalService(final GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public List<GoalDTO> findAll() {
        final List<Goal> goals = goalRepository.findAll(Sort.by("id"));
        return goals.stream()
                .map(goal -> mapToDTO(goal, new GoalDTO()))
                .toList();
    }

    public GoalDTO get(final Long id) {
        return goalRepository.findById(id)
                .map(goal -> mapToDTO(goal, new GoalDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final GoalDTO goalDTO) {
        final Goal goal = new Goal();
        mapToEntity(goalDTO, goal);
        return goalRepository.save(goal).getId();
    }

    public void update(final Long id, final GoalDTO goalDTO) {
        final Goal goal = goalRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(goalDTO, goal);
        goalRepository.save(goal);
    }

    public void delete(final Long id) {
        goalRepository.deleteById(id);
    }

    private GoalDTO mapToDTO(final Goal goal, final GoalDTO goalDTO) {
        goalDTO.setId(goal.getId());
        goalDTO.setGoalDescription(goal.getGoalDescription());
        goalDTO.setCompleted(goal.getCompleted());
        goalDTO.setTargetDate(goal.getTargetDate());
        goalDTO.setEmployee(goal.getEmployee());
        return goalDTO;
    }

    private Goal mapToEntity(final GoalDTO goalDTO, final Goal goal) {
        goal.setGoalDescription(goalDTO.getGoalDescription());
        goal.setCompleted(goalDTO.getCompleted());
        goal.setTargetDate(goalDTO.getTargetDate());
        goal.setEmployee(goalDTO.getEmployee());
        return goal;
    }

}
