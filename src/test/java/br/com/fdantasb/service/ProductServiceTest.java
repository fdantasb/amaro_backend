package br.com.fdantasb.service;

import br.com.fdantasb.model.Product;
import br.com.fdantasb.model.Tag;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void findTagSuccess(){
        Tag tag = productService.findTagByName("balada");
        Assert.assertNotNull(tag);
    }

    @Test
    public void createProductSuccess(){

        Product product = new Product();
        product.setId(58345l);
        product.setName("Saia Maxi Chiffon Saint");

        List<String> taglist = new ArrayList<>();
        taglist.addAll(Arrays.asList("balada", "metal", "delicado", "descolado"));

        product.setLabelTagList(taglist);

        Product result = productService.createProduct(product);

        Assert.assertNotNull(result);

    }

}