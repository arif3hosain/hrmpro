package com.hrm.hrmpro.service;

import com.hrm.hrmpro.domain.PerformanceReview;
import com.hrm.hrmpro.model.PerformanceReviewDTO;
import com.hrm.hrmpro.repos.PerformanceReviewRepository;
import com.hrm.hrmpro.util.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class PerformanceReviewService {

    private final PerformanceReviewRepository performanceReviewRepository;

    public PerformanceReviewService(final PerformanceReviewRepository performanceReviewRepository) {
        this.performanceReviewRepository = performanceReviewRepository;
    }

    public List<PerformanceReviewDTO> findAll() {
        final List<PerformanceReview> performanceReviews = performanceReviewRepository.findAll(Sort.by("id"));
        return performanceReviews.stream()
                .map(performanceReview -> mapToDTO(performanceReview, new PerformanceReviewDTO()))
                .toList();
    }

    public PerformanceReviewDTO get(final Long id) {
        return performanceReviewRepository.findById(id)
                .map(performanceReview -> mapToDTO(performanceReview, new PerformanceReviewDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final PerformanceReviewDTO performanceReviewDTO) {
        final PerformanceReview performanceReview = new PerformanceReview();
        mapToEntity(performanceReviewDTO, performanceReview);
        performanceReview.setReviewDate(LocalDate.now());
        return performanceReviewRepository.save(performanceReview).getId();
    }

    public void update(final Long id, final PerformanceReviewDTO performanceReviewDTO) {
        final PerformanceReview performanceReview = performanceReviewRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(performanceReviewDTO, performanceReview);
        performanceReviewRepository.save(performanceReview);
    }

    public void delete(final Long id) {
        performanceReviewRepository.deleteById(id);
    }

    private PerformanceReviewDTO mapToDTO(final PerformanceReview performanceReview,
            final PerformanceReviewDTO performanceReviewDTO) {
        performanceReviewDTO.setId(performanceReview.getId());
        performanceReviewDTO.setReviewText(performanceReview.getReviewText());
        performanceReviewDTO.setPerformance(performanceReview.getPerformance());
        performanceReviewDTO.setEmployee(performanceReview.getEmployee());
        performanceReviewDTO.setReviewDate(performanceReview.getReviewDate());
        return performanceReviewDTO;
    }

    private PerformanceReview mapToEntity(final PerformanceReviewDTO performanceReviewDTO,
            final PerformanceReview performanceReview) {
        performanceReview.setReviewText(performanceReviewDTO.getReviewText());
        performanceReview.setPerformance(performanceReviewDTO.getPerformance());
        performanceReview.setEmployee(performanceReviewDTO.getEmployee());
        return performanceReview;
    }

}
