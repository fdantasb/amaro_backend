package br.com.fdantasb;

import br.com.fdantasb.model.Tag;
import br.com.fdantasb.repository.TagRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AmaroBackendApplicationTests {

	@Autowired
	private TagRepository repository;

	@Test
	public void contextLoads() {
		ArrayList<Tag> list = new ArrayList<>();

		Tag tag0 = new Tag();
		tag0.setNome("neutro");
		tag0.setPosition("0");
		list.add(tag0);

		Tag tag1 = new Tag();
		tag1.setNome("veludo");
		tag1.setPosition("1");
		list.add(tag1);

		Tag tag2 = new Tag();
		tag2.setNome("couro");
		tag2.setPosition("2");
		list.add(tag2);

		Tag tag3 = new Tag();
		tag3.setNome("basics");
		tag3.setPosition("3");
		list.add(tag3);

		Tag tag4 = new Tag();
		tag4.setPosition("4");
		tag4.setNome("festa");
		list.add(tag4);

		Tag tag5 = new Tag();
		tag5.setPosition("5");
		tag5.setNome("workwear");
		list.add(tag5);

		Tag tag6 = new Tag();
		tag6.setNome("inverno");
		tag6.setPosition("6");
		list.add(tag6);

		Tag tag7 = new Tag();
		tag7.setNome("boho");
		tag7.setPosition("7");
		list.add(tag7);

		Tag tag8 = new Tag();
		tag8.setNome("estampas");
		tag8.setPosition("8");
		list.add(tag8);

		Tag tag9 = new Tag();
		tag9.setNome("balada");
		tag9.setPosition("9");
		list.add(tag9);

		Tag tag10 = new Tag();
		tag10.setNome("colorido");
		tag10.setPosition("10");
		list.add(tag10);

		Tag tag11 = new Tag();
		tag11.setNome("casual");
		tag11.setPosition("11");
		list.add(tag11);

		Tag tag12 = new Tag();
		tag12.setNome("liso");
		tag12.setPosition("12");
		list.add(tag12);

		Tag tag13 = new Tag();
		tag13.setNome("moderno");
		tag13.setPosition("13");
		list.add(tag13);

		Tag tag14 = new Tag();
		tag14.setNome("passeio");
		tag14.setPosition("14");
		list.add(tag14);

		Tag tag15 = new Tag();
		tag15.setNome("metal");
		tag15.setPosition("15");
		list.add(tag15);

		Tag tag16 = new Tag();
		tag16.setNome("viagem");
		tag16.setPosition("16");
		list.add(tag16);

		Tag tag17 = new Tag();
		tag17.setNome("delicado");
		tag17.setPosition("17");
		list.add(tag17);

		Tag tag18 = new Tag();
		tag18.setNome("descolado");
		tag18.setPosition("18");
		list.add(tag18);

		Tag tag19 = new Tag();
		tag19.setNome("elastano");
		tag19.setPosition("19");
		list.add(tag19);

		repository.saveAll(list);

	}

}
