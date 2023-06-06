package com.rgt.vechilerentalsystem;

import java.math.BigDecimal;

public interface RentalCostCalculator {
	BigDecimal calculateRentalCost(Rental rental);
}
