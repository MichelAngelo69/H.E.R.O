package pl.mb;

import org.springframework.data.repository.CrudRepository;
import pl.mb.model.Hero;

public interface HeroRepository extends CrudRepository<Hero, Integer> {
}
