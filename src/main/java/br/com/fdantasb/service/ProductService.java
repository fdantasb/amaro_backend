package br.com.fdantasb.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.fdantasb.model.Product;
import br.com.fdantasb.model.Tag;
import br.com.fdantasb.repository.ProductRepository;
import br.com.fdantasb.repository.TagRepository;
import br.com.fdantasb.service.exception.ProductNotFoundException;
import br.com.fdantasb.service.exception.TagNotFoundException;
import br.com.fdantasb.service.exception.TagsOutofBoundException;

@Service
public class ProductService {

	private static Logger LOG = LoggerFactory.getLogger(ProductService.class);
	
    private static final String TAGS_É_MAIOR_QUE_O_LIMITE = "A quantidade de tags é maior que o limite.";
	private static final String EXISTENT_PRODUCT = "Produto já existe.";
    private static final String TAG_NAO_ENCONTRADA = "Tag não Encontrada.";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TagRepository tagRepository;

    public void createProductList(ArrayList<Product> productList) {
    	LOG.info("Tamanho da lista de produtos: " + productList.size());
    	productList.stream().forEach(p -> createProduct(p));
    }
    
    public Tag findTagByName(String string) {
    	LOG.info("Buscando Tag: " + string);
        Tag result = tagRepository.findByNome(string);
        return result;
    }

    public Product createProduct(Product product) {
    	LOG.info("Inserindo o produto de ID: " + product.getId());
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
        
        LOG.info("Tags validadas com sucesso");
        List<Tag> tagList = fillTagList(prod);
		
        if (!tagList.isEmpty()){
        	LOG.info("Inserindo Tags validadas");
            prod.setTagList(tagList);
        }

    }

	private List<Tag> fillTagList(Product prod) {
		List<Tag> tags = new ArrayList<Tag>();
		prod.getTags().stream().forEach(s -> tags.add(findTagByName(s)));
		
		Tag tagArray[] = tagOrderArray(tags);
		
		return Arrays.asList(tagArray);
	}

	private Tag[] tagOrderArray(List<Tag> tags) {
		Tag[] result = new Tag[20];
		tags.stream().forEach(t -> result[Integer.valueOf(t.getPosition())] = t);
		Tag neutral = tagRepository.findByPosition("0");
		
		for (int i = 0; i < result.length; i++) {
			if (result[i] == null) {
				result[i] = neutral;
			}
		}
		
		return result;
	}

	private void validateStringTags(Product prod) {
		List<String> tagNotFound = prod.getTags().stream().filter(s -> findTagByName(s) == null).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(tagNotFound)) {
            LOG.info("As seguintes Tags não foram encontradas:\n");
            tagNotFound.stream().forEach(s -> LOG.info(s));
            throw new TagNotFoundException(TAG_NAO_ENCONTRADA);
        }
        if (prod.getTags().size() > 20) {
        	LOG.info(TAGS_É_MAIOR_QUE_O_LIMITE);
			throw new TagsOutofBoundException(TAGS_É_MAIOR_QUE_O_LIMITE);
		}
	}

    private boolean productExist(Product product) {
        Optional<Product> result = productRepository.findById(product.getId());
        return result.isPresent();
    }

}
