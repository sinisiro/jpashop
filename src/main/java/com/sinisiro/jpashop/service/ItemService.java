package com.sinisiro.jpashop.service;

import com.sinisiro.jpashop.domain.Book;
import com.sinisiro.jpashop.domain.Item;
import com.sinisiro.jpashop.repository.BookRepository;
import com.sinisiro.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor        //final로 선언해야함
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;
    private final BookRepository bookRepository;

    @Transactional
    public void saveItem(Item item) {

        itemRepository.save(item);
    }

    public void findItem(Item item){
        Item findItem = itemRepository.findOne(item.getId());

        findItem.setName(item.getName());
        findItem.setPrice(item.getPrice());
        findItem.setStockQuantity(item.getStockQuantity());



    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    @Transactional
    public void updateItem(Long id, String name, int price, String isbn, String author, int stockQuantity) {
        Book book = bookRepository.findOne(id);

        book.setName(name);
        book.setPrice(price);
        book.setIsbn(isbn);
        book.setAuthor(author);
        book.setStockQuantity(stockQuantity);
//        Item item = itemRepository.findOne(id);

//        item.setName(name);
//        item.setPrice(price);
    }

    @Transactional
    public void deleteItem(Long id) {
        Item findItem = itemRepository.findOne(id);
        itemRepository.delete(findItem);

    }
}
