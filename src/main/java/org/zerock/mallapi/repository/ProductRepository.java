package org.zerock.mallapi.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.mallapi.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    
    @EntityGraph(attributePaths = "imageList")
    @Query("select p from Product p where p.pno = :pno")
    Optional<Product> selectOne(@Param("pno") Long pno);

    @Modifying
    @Query("update Product p set p.delFlag = :flag where p.pno = :pno")
    void updateToDelte(@Param("pno") Long pno, @Param("flag") boolean flag);

    /*
     * SELECT a.pno, a.del_flag, a.pdesc, a.pname, a.price, b.file_name, b.ord
     * FROM tbl_product a
     * LEFT JOIN product_image_list b ON a.pno = b.product_pno
     * WHERE b.ord=0 AND a.del_flag=0
     * ORDER BY a.pno DESC
     * LIMIT 0, 10;
     */
    @Query("select p, pi from Product p left join p.imageList pi where pi.ord = 0 and p.delFlag = false")
    Page<Object[]> selectList(Pageable Pageble);
}
