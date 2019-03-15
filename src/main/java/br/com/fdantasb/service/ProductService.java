package br.com.fdantasb.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.fdantasb.data.ProductData;
import br.com.fdantasb.data.Similar;
import br.com.fdantasb.model.Product;
import br.com.fdantasb.model.Tag;
import br.com.fdantasb.repository.ProductRepository;
import br.com.fdantasb.repository.TagRepository;
import br.com.fdantasb.service.exception.ProductException;
import br.com.fdantasb.service.exception.TagNotFoundException;
import br.com.fdantasb.service.exception.TagsOutofBoundException;

@Service
public class ProductService {

	private static Logger LOG = LoggerFactory.getLogger(ProductService.class);
	
    private static final String TAGS_É_MAIOR_QUE_O_LIMITE = "A quantidade de tags é maior que o limite.";
	private static final String EXISTENT_PRODUCT = "Produto já existe.\n ID: ";
    private static final String TAG_NAO_ENCONTRADA = "As seguintes Tags não foram encontradas:";
	private static final String NOT_FOUND_PRODUCT = "Producot não encontrado.\n";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TagRepository tagRepository;

    public void createProductList(ArrayList<Product> productList) {
    	LOG.info("Tamanho da lista de produtos: " + productList.size());
    	productList.stream().forEach(p -> validateProduct(p));
    	productList.stream().forEach(p -> createProduct(p));
    }
    
    public Tag findTagByName(String string) {
    	LOG.info("Buscando Tag: " + string);
        Tag result = tagRepository.findByNome(string);
        return result;
    }

    public Product createProduct(Product product) {
    	LOG.info("Inserindo o produto de ID: " + product.getId());
        populateTagList(product);

        Product result = productRepository.save(product);
        LOG.info("Produto salvo id: " + product.getId());

        return result;
    }

    private void populateTagList(Product prod) {
        List<Tag> tagList = fillTagList(prod);
		
        if (!tagList.isEmpty()){
        	LOG.info("Inserindo Tags");
            prod.setTagList(tagList);
        }

    }

	private List<Tag> fillTagList(Product prod) {
		List<Tag> tags = new ArrayList<Tag>();
		prod.getTags().stream().forEach(s -> tags.add(findTagByName(s)));
		return tags;
	}

	private String[] tagTagArray(List<Tag> tags) {
		String[] result = new String[20];
		tags.stream().forEach(t -> result[Integer.valueOf(t.getPosition())] = "1");
		
		for (int i = 0; i < result.length; i++) {
			if (result[i] == null) {
				result[i] = "0";
			}
		}
		
		return result;
	}

    private void validateProduct(Product product) {
        //valida se o produto já existe na base
    	Optional<Product> result = productRepository.findById(product.getId());
        if (result.isPresent()) {
        	LOG.info(EXISTENT_PRODUCT + product.getId());
        	throw new ProductException(EXISTENT_PRODUCT + product.getId());
        }
        
        //valida se as tags já existem
        List<String> tagNotFound = product.getTags().stream().filter(s -> findTagByName(s) == null).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(tagNotFound)) {
        	String tagsError = "";
        	for (String stringTag : tagNotFound) {
				tagsError = tagsError.concat(stringTag + ", ");
			}
            LOG.info(TAG_NAO_ENCONTRADA);
            throw new TagNotFoundException(TAG_NAO_ENCONTRADA.concat(tagsError));
        }
        
        //valida tamanho da lista de tags
        if (product.getTags().size() > 20) {
        	LOG.info(TAGS_É_MAIOR_QUE_O_LIMITE);
			throw new TagsOutofBoundException(TAGS_É_MAIOR_QUE_O_LIMITE);
		}
                
    }

	public List<ProductData> findAllProductDataList() {
		LOG.info("Procurando lista de todos os produtos");
		List<Product> allProduct = productRepository.findAll();
		List<ProductData> result = new ArrayList<>();
		
		allProduct.stream().forEach(p -> result.add(convertProductData(p)));
		return result;
	}

	private ProductData convertProductData(Product p) {
		LOG.info("Convertendo produto de ID" + p.getId());
		ProductData result = new ProductData();
		result.setId(p.getId());
		result.setName(p.getName());
		result.setTagsVector(tagTagArray(p.getTagList()));
		List<String> tags = tagsFromTagList(p.getTagList());
		result.setTags(tags);		
		
		return result;
	}

	private List<String> tagsFromTagList(List<Tag> tagList) {
		List<String> result = new ArrayList<>();
		for (Tag tag : tagList) {
			result.add(tag.getNome());
		}
		return result;
	}

	public List<Similar> findSimilarProductDataList(Long id) {
		LOG.info("Busca de produtos simlares");
		Product product = productRepository.findById(id).get();
		if (product == null) {
			LOG.info(NOT_FOUND_PRODUCT + id);
        	throw new ProductException(NOT_FOUND_PRODUCT + id);
		}
		LOG.info("Produto encontrado, id: " + id);
		List<Product> all = productRepository.findAll();
		all.remove(product);
		List<Similar> result = new ArrayList<>();
		
		//Transforma array de tag para comparacao
		double[] tagPositionA = getArrayPositionValue(product.getTagList());
		double[] tagPositionB = null;
		HashMap<Double, Product> distanceProductMap = new HashMap<Double, Product>();
		
		for (Product similar : all) {
			LOG.info("criando array de comparacao para o produto: " + similar.getId());
			tagPositionB = getArrayPositionValue(similar.getTagList());
			
			double distance = calculateDistance(tagPositionA, tagPositionB);
			
			if (distance > 0) {
				distanceProductMap.put(distance, similar);
			}
		}
		LOG.info("Quantidade de produtos similares: " + distanceProductMap.size());
		distanceProductMap.entrySet().stream().sorted((o1, o2) -> o1.getKey().compareTo(o2.getKey())).forEach(o -> result.add(convertMapProductSimilar(o)));;
		
		return result;
	}

	private Similar convertMapProductSimilar(Entry<Double, Product> entry) {
		Similar result = new Similar();
		result.setId(entry.getValue().getId());
		result.setName(entry.getValue().getName());
		result.setSimilarity(entry.getKey());
		return result;
	}

	private double[] getArrayPositionValue(List<Tag> tagList) {
		LOG.info("Entra no metodo de converter para array de posicao");
		LOG.info("Quantidade de tags para converter: " + tagList.size());
		double[] array = new double[20];
		for (Tag tag : tagList) {
			array[Integer.valueOf(tag.getPosition())] = Integer.valueOf(tag.getPosition()); 
		}
		return array;
	}
	
	public double calculateDistance(double[] tagPositionA, double[] tagPositionB)   {
        double Sum = 0.0;
        for(int i=0;i<tagPositionA.length;i++) {
           Sum = Sum + Math.pow((tagPositionA[i]-tagPositionB[i]),2.0);
        }
        double result = new BigDecimal(1/(1+Math.sqrt(Sum))).setScale(2, RoundingMode.HALF_UP).doubleValue();
        LOG.info("Distancia: " + result);
		return result;
    }

}
