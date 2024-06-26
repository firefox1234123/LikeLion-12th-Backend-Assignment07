package org.likelion.likelionrecrud.item.api;

import lombok.RequiredArgsConstructor;
import org.likelion.likelionrecrud.item.api.dto.request.ItemSaveReqDto;
import org.likelion.likelionrecrud.item.api.dto.request.ItemUpdateReqDto;
import org.likelion.likelionrecrud.item.api.dto.response.ItemInfoResDto;
import org.likelion.likelionrecrud.item.api.dto.response.ItemListResDto;
import org.likelion.likelionrecrud.item.application.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<String> itemSave(@RequestBody ItemSaveReqDto itemSaveReqDto) {
        itemService.itemSave(itemSaveReqDto);
        return new ResponseEntity<>("아이템 저장!", HttpStatus.CREATED);
    }

    @GetMapping("/items")
    public ResponseEntity<ItemListResDto> itemFindAll() {
        ItemListResDto itemListResDto = itemService.itemFindAll();
        return new ResponseEntity<>(itemListResDto, HttpStatus.OK);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemInfoResDto> itemFindOne(@PathVariable("itemId") Long itemId) {
        ItemInfoResDto itemInfoResDto = itemService.itemFindOne(itemId);
        return new ResponseEntity<>(itemInfoResDto, HttpStatus.OK);
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<String> itemUpdate(@PathVariable("itemId") Long itemId, @RequestBody ItemUpdateReqDto itemUpdateReqDto) {
        itemService.updateItem(itemId, itemUpdateReqDto);
        return new ResponseEntity<>("상품 수정!", HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<String> itemDelete(@PathVariable("itemId") Long itemId) {
        itemService.deleteItem(itemId);
        return new ResponseEntity<>("상품 삭제!", HttpStatus.OK);
    }

}
