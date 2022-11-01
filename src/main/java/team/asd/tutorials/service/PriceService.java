package team.asd.tutorials.service;

import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;
import team.asd.tutorials.entities.IsPerDayPrice;
import team.asd.tutorials.entities.IsPrice;
import team.asd.tutorials.exceptions.WrongPriceException;
import team.asd.tutorials.service.IsPriceService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Period;
import java.util.List;
import java.util.Objects;

public class PriceService implements IsPriceService {

	@Override
	public @NonNull BigDecimal defineAverageValueFromPerDayPrice(List<IsPerDayPrice> prices) throws WrongPriceException {
		if (CollectionUtils.isEmpty(prices)) {
			return BigDecimal.ZERO;
		}

		if (prices.stream()
				.filter(Objects::nonNull)
				.anyMatch(element -> element.getDate() == null || element.getPrice() == null)) {
			throw new WrongPriceException("Wrong price item was provided");
		}

		if (prices.stream()
				.map(IsPerDayPrice::getDate)
				.distinct()
				.count() != prices.size()) {
			throw new WrongPriceException("Date of two prices are equals");
		}

		return prices.stream()
				.map(IsPerDayPrice::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add)
				.divide(BigDecimal.valueOf(prices.size()), RoundingMode.HALF_DOWN);

	}

	@Override
	public @NonNull BigDecimal defineAverageValueFromPrices(List<IsPrice> prices) throws WrongPriceException {
		if (CollectionUtils.isEmpty(prices)) {
			return BigDecimal.ZERO;
		}

		if (prices.contains(null) || prices.stream()
				.anyMatch(p -> p.getFromDate() == null || p.getToDate() == null)) {
			throw new WrongPriceException("Wrong price or date was provided");
		}

		if (prices.stream()
				.anyMatch(price -> prices.stream()
						.anyMatch(nextPrice -> (nextPrice.getFromDate()
								.isAfter(price.getFromDate()) && nextPrice.getFromDate()
								.isBefore(price.getToDate())) || (nextPrice.getToDate()
								.isAfter(price.getFromDate()) && nextPrice.getToDate()
								.isBefore(price.getToDate()))))) {
			throw new WrongPriceException("Price dates are collides");
		}

		return sumOfValue(prices).divide(sumOfDuration(prices), RoundingMode.HALF_DOWN);
	}

	private BigDecimal sumOfValue(List<IsPrice> prices) {
		return prices.stream()
				.map(price -> price.getPrice()
						.multiply(getDuration(price)))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private BigDecimal sumOfDuration(List<IsPrice> prices) {
		return prices.stream()
				.map(this::getDuration)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private BigDecimal getDuration(IsPrice price) {
		return BigDecimal.valueOf(Period.between(price.getFromDate(), price.getToDate())
				.getDays() + 1);
	}
}

