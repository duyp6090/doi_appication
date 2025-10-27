package com.duydev.backend.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
    @OneToOne
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    private BookingEntity booking;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "comment")
    private String comment;
}
