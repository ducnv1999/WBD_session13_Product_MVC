package com.codegym.service;

import com.codegym.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceIpml implements ProductService {
    private static Map<Integer, Product> products;

    static {
        products = new HashMap<>();

        products.put(1, new Product(1, "mì tôm", 7500, "hộp", "hảo hảo") );
        products.put(2, new Product(2, "mì tôm", 7500, "hộp", "hảo hảo") );
        products.put(3, new Product(3, "mì tôm", 7500, "hộp", "hảo hảo") );
        products.put(4, new Product(4, "mì tôm", 7500, "hộp", "hảo hảo") );
        products.put(5, new Product(5, "mì tôm", 7500, "hộp", "hảo hảo") );
        products.put(6, new Product(6, "mì tôm", 7500, "hộp", "hảo hảo") );

    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {

        products.put(product.getId(),product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {

        products.put(id, product);
    }

    @Override
    public void remove(int id) {

        products.remove(id);
    }

    @Override
    public Product search(String name) {

     return products.get(name);
    }
}
