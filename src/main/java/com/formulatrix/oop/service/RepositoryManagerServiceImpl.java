package com.formulatrix.oop.service;

import com.formulatrix.oop.ItemType;
import com.formulatrix.oop.entity.Item;
import com.formulatrix.oop.model.ItemDto;
import com.formulatrix.oop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RepositoryManagerServiceImpl implements IRepositoryManagerService {

    private final ItemRepository itemRepository;


    private Map<String, ItemDto> items;

    @Override
    @Transactional
    public void register(ItemDto itemDto) {
        if (itemDto == null || itemDto.getItemName() == null || itemDto.getItemName().isEmpty()) {
            throw new IllegalArgumentException("Invalid item details.");
        }

        Item existingItem = itemRepository.findByItemName(itemDto.getItemName());
        if (existingItem != null) {
            throw new IllegalArgumentException("An item with the same name already exists.");
        }

        ItemType.from(itemDto.getItemType());
        Item item = DtoToItem(itemDto);
        itemRepository.save(item);
    }

    @Override
    public ItemDto retrieve(String itemName) {
        return entityToDTO(itemRepository.findByItemName(itemName));

    }

    @Override
    public int getType(String itemName) {
        Item item = itemRepository.findByItemName(itemName);
        return item != null ? item.getItemType() : -1;
    }

    @Override
    @Transactional
    public void deregister(String itemName) {
        Item item = itemRepository.findByItemName(itemName);
        if (item != null) {
            itemRepository.delete(item);
        }
    }

    ItemDto entityToDTO(Item item){
        if (item == null){
            return null;
        }
        ItemDto itemDTO = new ItemDto();
        itemDTO.setItemName(item.getItemName());
        itemDTO.setItemContent(item.getItemContent());
        itemDTO.setItemType(item.getItemType());
        return itemDTO;
    }

    Item DtoToItem(ItemDto itemDTO){
        Item item = new Item();
        item.setItemName(itemDTO.getItemName());
        item.setItemContent(itemDTO.getItemContent());
        item.setItemType(itemDTO.getItemType());
        return item;
    }


}
