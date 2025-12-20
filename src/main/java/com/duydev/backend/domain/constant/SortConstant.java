package com.duydev.backend.domain.constant;

import java.util.List;

public class SortConstant {
    public static final List<String> SORT_FIELDS_CAR = List.of("distance", "price_per_hour", "year");
    public static final List<String> SORT_FIELDS_MANAGEMENT_CAR = List.of("pricePerHour", "year");

    public static List<String> getManagementCarSortFields() {
        return SORT_FIELDS_MANAGEMENT_CAR;
    }
}
