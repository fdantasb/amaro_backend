package br.com.fdantasb.repository;

import br.com.fdantasb.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByNome(String string);
}
