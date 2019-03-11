package br.com.fdantasb.service;

import br.com.fdantasb.model.Product;
import br.com.fdantasb.model.Tag;
import br.com.fdantasb.repository.ProductRepository;
import br.com.fdantasb.repository.TagRepository;
import br.com.fdantasb.service.exception.ProductException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    public static final String EXISTENT_PRODUCT = "Produto já existe.";
    public static final String TAG_NAO_ENCONTRADA = "Tag não Encontrada.";
    private static Logger LOG = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TagRepository tagRepository;

    public Tag findTagByName(String string) {
        Tag result = tagRepository.findByNome(string);
        return result;
    }

    public Product createProduct(Product product) {
        if (productExist(product)){
            LOG.info(EXISTENT_PRODUCT);
            throw new ProductException(EXISTENT_PRODUCT);
        }
        populateTagList(product);

        Product result = productRepository.save(product);
        LOG.info("Produto salvo id: " + product.getId());

        return result;
    }

    private void populateTagList(Product prod) {


        List<String> strings = prod.getLabelTagList().stream().filter(s -> findTagByName(s) == null).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(strings)) {
            LOG.info("As seguintes Tags não foram encontradas:\n");
            strings.stream().forEach(s -> LOG.info(s));
            throw new ProductException(TAG_NAO_ENCONTRADA);
        }

        List<Tag> tagList = new ArrayList<>();
        prod.getLabelTagList().stream().forEach(s -> tagList.add(findTagByName(s)));

        if (!tagList.isEmpty()){
            prod.setTagList(tagList);
        }

    }

    private boolean productExist(Product product) {
        Optional<Product> result = productRepository.findById(product.getId());
        return result.isPresent();
    }
}
