package team.asd.service;

import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;
import team.asd.constants.ProductState;
import team.asd.entities.IsProduct;
import team.asd.exceptions.WrongProductException;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductService implements IsProductService {
    @Override
    public @NonNull List<String> defineProductNames(List<IsProduct> productList) throws WrongProductException {
        if (CollectionUtils.isEmpty(productList)) {
            return Collections.emptyList();
        }
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i) == null) {
                throw new WrongProductException("Product cannot be null");
            }
        }

        return productList.stream()
                .filter(Objects::nonNull)
                .map(IsProduct::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<IsProduct> defineProductsWithCreatedState(List<IsProduct> productList) {
        return null;
//        return  productList.stream()
//                .filter(Objects::nonNull)
//                .filter(e -> e.getState().equals("Created"))
//                .collect(Collectors.toList());
    }

    @Override
    public @NonNull Map<ProductState, Integer> calculateProductCountByState(List<IsProduct> productList) throws WrongProductException {
        return null;
    }

    @Override
    public @NonNull List<IsProduct> filterProductsByProvidedObject(List<IsProduct> productList, IsProduct product) throws WrongProductException {
        return null;
    }
}
