package br.com.fdantasb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonInclude(Include.NON_NULL)
    private Long id;

    @NotEmpty(message = "O campo nome não pode ser vazio.")
    private String name;
    @JsonIgnore
    @ManyToMany
    @NotEmpty
    private List<Tag> tagList;

    @Transient
    private List<String> labelTagList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public List<String> getLabelTagList() {
        return labelTagList;
    }

    public void setLabelTagList(List<String> labelTagList) {
        this.labelTagList = labelTagList;
    }
}
