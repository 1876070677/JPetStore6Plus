package org.mybatis.jpetstore.service;

import org.mybatis.jpetstore.mapper.ItemMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ItemMapper itemMapper;

    public ProductService(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public int addItem() {
        return 0;
    }
}
