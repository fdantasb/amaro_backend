package br.com.fdantasb.model;

import br.com.fdantasb.repository.TagRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TagTest {

    @Autowired
    private TagRepository repository;

    @Test
    public void fillTable(){

        ArrayList<Tag> list = new ArrayList<>();

        Tag tag0 = new Tag();
        tag0.setId(0l);
        tag0.setNome("neutro");
        list.add(tag0);

        Tag tag1 = new Tag();
        tag1.setId(1l);
        tag1.setNome("veludo");
        list.add(tag1);

        Tag tag2 = new Tag();
        tag2.setId(2l);
        tag2.setNome("couro");
        list.add(tag2);

        Tag tag3 = new Tag();
        tag3.setId(3l);
        tag3.setNome("basics");
        list.add(tag3);

        Tag tag4 = new Tag();
        tag4.setId(4l);
        tag4.setNome("festa");
        list.add(tag4);

        Tag tag5 = new Tag();
        tag5.setId(5l);
        tag5.setNome("workwear");
        list.add(tag5);

        Tag tag6 = new Tag();
        tag6.setId(6l);
        tag6.setNome("inverno");
        list.add(tag6);

        Tag tag7 = new Tag();
        tag7.setId(7l);
        tag7.setNome("boho");
        list.add(tag7);

        Tag tag8 = new Tag();
        tag8.setId(8l);
        tag8.setNome("estampas");
        list.add(tag8);

        Tag tag9 = new Tag();
        tag9.setId(9l);
        tag9.setNome("balada");
        list.add(tag9);

        Tag tag10 = new Tag();
        tag10.setId(10l);
        tag10.setNome("colorido");
        list.add(tag10);

        Tag tag11 = new Tag();
        tag11.setId(11l);
        tag11.setNome("casual");
        list.add(tag11);

        Tag tag12 = new Tag();
        tag12.setId(12l);
        tag12.setNome("liso");
        list.add(tag12);

        Tag tag13 = new Tag();
        tag13.setId(13l);
        tag13.setNome("moderno");
        list.add(tag13);

        Tag tag14 = new Tag();
        tag14.setId(14l);
        tag14.setNome("passeio");
        list.add(tag14);

        Tag tag15 = new Tag();
        tag15.setId(15l);
        tag15.setNome("metal");
        list.add(tag15);

        Tag tag16 = new Tag();
        tag16.setId(16l);
        tag16.setNome("viagem");
        list.add(tag16);

        Tag tag17 = new Tag();
        tag17.setId(17l);
        tag17.setNome("delicado");
        list.add(tag17);

        Tag tag18 = new Tag();
        tag18.setId(18l);
        tag18.setNome("descolado");
        list.add(tag18);

        Tag tag19 = new Tag();
        tag19.setId(19l);
        tag19.setNome("elastano");
        list.add(tag19);

        repository.saveAll(list);
    }
}