package team.asd.service;

import lombok.NonNull;
import team.asd.constants.ProductState;
import team.asd.entities.IsProduct;
import team.asd.exceptions.WrongProductException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductService implements IsProductService{
    @Override
    public @NonNull List<String> defineProductNames(List<IsProduct> productList) throws WrongProductException {


        return  productList.stream()
                .map(IsProduct::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<IsProduct> defineProductsWithCreatedState(List<IsProduct> productList) {
        return null;
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
