package b.daw.cifpcm.animales_persistencia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import b.daw.cifpcm.animales_persistencia.models.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Integer>{
    
}
