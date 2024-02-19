package org.zerock.mallapi.service;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mallapi.dto.PageRequestDTO;
import org.zerock.mallapi.dto.PageResponseDTO;
import org.zerock.mallapi.dto.ProductDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ProductServiceTests {
    @Autowired
    ProductService productService;

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        PageResponseDTO<ProductDTO> result = productService.getList(pageRequestDTO);

        result.getDtoList().forEach(dto -> log.info(dto));
    }

    @Test
    public void testRegister(){
        ProductDTO productDTO = ProductDTO.builder()
            .pname("new product1")
            .pdesc("this is a new product")
            .price(1000)
            .build();

        productDTO.setUploadFileNames(
            List.of(
                UUID.randomUUID()+"_"+"Test1.jpg",
                UUID.randomUUID()+"_"+"Test2.jpg")
        );
        
        productService.register(productDTO);
    }

    @Test
    public void testRead(){
        Long pno = 12L;

        ProductDTO productDTO = productService.get(pno);

        log.info(productDTO);
        log.info(productDTO.getUploadFileNames());
    }
}
