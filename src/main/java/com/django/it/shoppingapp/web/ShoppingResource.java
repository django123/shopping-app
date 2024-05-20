package com.django.it.shoppingapp.web;

import com.django.it.shoppingapp.common.PageResponse;
import com.django.it.shoppingapp.dtos.requests.ShoppingRequest;
import com.django.it.shoppingapp.dtos.responses.ShoppingResponse;
import com.django.it.shoppingapp.model.Share;
import com.django.it.shoppingapp.services.ShoppingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shoppings")
@Tag(name = "Shopping")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ShoppingResource {

    private final ShoppingService shoppingService;

    @GetMapping
    public ResponseEntity<PageResponse<ShoppingResponse>> getShoppings(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(shoppingService.findAllShopping(page, size));
    }

    @GetMapping("/{shop-id}")
    public ResponseEntity<ShoppingResponse>findShoppingById(@PathVariable("shop-id") Integer id) {
        return ResponseEntity.ok(shoppingService.findShoppingById(id));
    }

    @PostMapping
    public ResponseEntity<String> createShopping(@Valid @RequestBody ShoppingRequest shoppingRequest, Authentication connectedUser) {
        shoppingService.addShopping(shoppingRequest, connectedUser);
        return ResponseEntity.ok("Shopping created successfully");
    }

    @PutMapping("/{shop-id}")
    public ResponseEntity<String> updateShopping(@RequestBody ShoppingRequest shoppingRequest, @PathVariable("shop-id") Integer id) {
        shoppingService.updateShopping(shoppingRequest, id);
        return ResponseEntity.ok("Shopping updated successfully");
    }

    @DeleteMapping("/{shop-id}")
    public ResponseEntity<String> deleteShopping(@PathVariable("shop-id") Integer id) {
        shoppingService.deleteShoppingById(id);
        return ResponseEntity.ok("Shopping deleted successfully");
    }

    @GetMapping("/archive")
    public ResponseEntity<List<ShoppingResponse>> findAllShoppingArchived(Authentication connectedUser) {

        return ResponseEntity.ok(shoppingService.findAllShoppingArchived(connectedUser));
    }

    @GetMapping("/shared")
    public ResponseEntity<List<ShoppingResponse>> sharedShopping(Authentication connectedUser) {

        return ResponseEntity.ok(shoppingService.sharedShopping(connectedUser));
    }

    @GetMapping("/archive/{shop-id}")
    public ResponseEntity<String> archived(@PathVariable("shop-id") Integer id) {
        shoppingService.archived(id);
        return ResponseEntity.ok("Shopping archived successfully");
    }

    @GetMapping("/share/{shop-id}")
    public ResponseEntity<String> shareShopping(@Valid Share share, @PathVariable("shop-id") Integer id,Authentication connectedUser) {
        shoppingService.shareShopping(share, id, connectedUser);
        return ResponseEntity.ok("Shopping archived successfully");
    }

}
