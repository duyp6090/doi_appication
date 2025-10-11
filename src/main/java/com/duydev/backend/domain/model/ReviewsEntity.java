package com.duydev.backend.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_reviews")
public class ReviewsEntity extends AbstractEntity<Long> {
    @ManyToOne
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    @JsonBackReference
    private BookingEntity booking;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "comment")
    private String comment;
}
