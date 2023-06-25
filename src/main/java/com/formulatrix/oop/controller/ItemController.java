package com.formulatrix.oop.controller;

import com.formulatrix.oop.model.ItemDto;
import com.formulatrix.oop.service.IRepositoryManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final IRepositoryManagerService repositoryManagerService;

    @PostMapping
    public ResponseEntity<String> registerItem(@RequestBody ItemDto itemDTO) {
        try {
            repositoryManagerService.register(itemDTO);
            return new ResponseEntity<>("Item registered successfully.", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{itemName}")
    public ResponseEntity<?> getItem(@PathVariable String itemName) {
        ItemDto itemDTO = repositoryManagerService.retrieve(itemName);
        if (itemDTO != null) {
            return ResponseEntity.ok(itemDTO);
        } else {
            return new ResponseEntity<>("Item not found.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{itemName}/type")
    public ResponseEntity<?> getItemType(@PathVariable String itemName) {
        int itemType = repositoryManagerService.getType(itemName);
        if (itemType != -1) {
            return ResponseEntity.ok(itemType);
        } else {
            return new ResponseEntity<>("Item not found.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{itemName}")
    public ResponseEntity<String> deleteItem(@PathVariable String itemName) {
        repositoryManagerService.deregister(itemName);
        return ResponseEntity.ok("Item deleted successfully.");
    }
}
