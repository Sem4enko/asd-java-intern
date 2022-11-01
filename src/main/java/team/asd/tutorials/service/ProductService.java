package team.asd.tutorials.service;

import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;
import team.asd.tutorials.constants.ProductState;
import team.asd.tutorials.exceptions.WrongProductException;
import team.asd.tutorials.entities.IsProduct;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductService implements IsProductService {
	private void checkProducts(List<IsProduct> list) throws WrongProductException {
		if (list.stream()
				.anyMatch(Objects::isNull)) {
			throw new WrongProductException("Product cannot be null");
		}
	}

	private long countProductByState(List<IsProduct> productList, ProductState state) {
		if (CollectionUtils.isEmpty(productList)) {
			return 0L;
		}

		return productList.stream()
				.filter(product -> product.getState()
						.equals(state))
				.count();
	}

	@Override
	public @NonNull List<String> defineProductNames(List<IsProduct> productList) throws WrongProductException {
		if (CollectionUtils.isEmpty(productList)) {
			return Collections.emptyList();
		}
		checkProducts(productList);
		return productList.stream()
				.map(IsProduct::getName)
				.collect(Collectors.toList());
	}

	@Override
	public List<IsProduct> defineProductsWithCreatedState(List<IsProduct> productList) {
		if (CollectionUtils.isEmpty(productList)) {
			return Collections.emptyList();
		}

		return productList.stream()
				.filter(Objects::nonNull)
				.filter(e -> ProductState.Created.equals(e.getState()))
				.collect(Collectors.toList());
	}

	@Override
	public @NonNull Map<ProductState, Integer> calculateProductCountByState(List<IsProduct> productList) throws WrongProductException {
		Map<ProductState, Integer> map = new HashMap<>();

		if (CollectionUtils.isEmpty(productList)) {
			for (ProductState s : ProductState.values()) {
				map.put(s, null);
			}
			return map;
		}

		checkProducts(productList);

		for (ProductState s : ProductState.values()) {
			map.put(s, (int) countProductByState(productList, s));
		}
		return map;
	}

	@Override
	public @NonNull List<IsProduct> filterProductsByProvidedObject(List<IsProduct> productList, IsProduct product) throws WrongProductException {
		if (CollectionUtils.isEmpty(productList)) {
			return Collections.emptyList();
		} else if (Objects.isNull(product)) {
			throw new WrongProductException("Product cannot be null");
		}
		checkProducts(productList);
		return productList.stream()
				.filter(element -> product.getName() == null || element.getName()
						.equals(product.getName()))
				.filter(element -> product.getState() == null || element.getState()
						.equals(product.getState()))
				.collect(Collectors.toList());
	}
}
