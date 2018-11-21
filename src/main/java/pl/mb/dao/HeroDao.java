package pl.mb.dao;

import org.springframework.data.repository.CrudRepository;
import pl.mb.model.Hero;

public interface HeroDao extends CrudRepository<Hero, Long> {
}
