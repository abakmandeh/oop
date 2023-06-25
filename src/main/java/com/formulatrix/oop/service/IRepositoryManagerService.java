package com.formulatrix.oop.service;

import com.formulatrix.oop.model.ItemDto;

public interface IRepositoryManagerService {
    public void register(ItemDto itemDTO);

    public ItemDto retrieve(String itemName);

    public int getType(String itemName);

    public void deregister(String itemName);
}
