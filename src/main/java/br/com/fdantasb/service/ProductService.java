package br.com.fdantasb.service;

import br.com.fdantasb.model.Product;
import br.com.fdantasb.model.Tag;
import br.com.fdantasb.repository.ProductRepository;
import br.com.fdantasb.repository.TagRepository;
import br.com.fdantasb.service.exception.ProductNotFoundException;
import br.com.fdantasb.service.exception.TagNotFoundException;
import br.com.fdantasb.service.exception.TagsOutofBoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final String TAGS_É_MAIOR_QUE_O_LIMITE = "A quantidade de tags é maior que o limite.";
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
            throw new ProductNotFoundException(EXISTENT_PRODUCT);
        }
        populateTagList(product);

        Product result = productRepository.save(product);
        LOG.info("Produto salvo id: " + product.getId());

        return result;
    }

    private void populateTagList(Product prod) {
        validateStringTags(prod);

        List<Tag> tagList = new ArrayList<>();
        prod.getTags().stream().forEach(s -> tagList.add(findTagByName(s)));
        if (tagList.size() > 20) {
			throw new TagsOutofBoundException(TAGS_É_MAIOR_QUE_O_LIMITE);
		}
        if (!tagList.isEmpty()){
            prod.setTagList(tagList);
        }

    }

	private void validateStringTags(Product prod) {
		List<String> tagNotFound = prod.getTags().stream().filter(s -> findTagByName(s) == null).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(tagNotFound)) {
            LOG.info("As seguintes Tags não foram encontradas:\n");
            tagNotFound.stream().forEach(s -> LOG.info(s));
            throw new TagNotFoundException(TAG_NAO_ENCONTRADA);
        }
	}

    private boolean productExist(Product product) {
        Optional<Product> result = productRepository.findById(product.getId());
        return result.isPresent();
    }
}
