package b.daw.cifpcm.animales_persistencia.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import b.daw.cifpcm.animales_persistencia.models.Animal;
import b.daw.cifpcm.animales_persistencia.repositories.AnimalRepository;

//CRRUD + EG
@Service
public class AnimalService_Rep {
    @Autowired
    private AnimalRepository ar;

    public void Create(Animal createAnimal){
        ar.saveAndFlush(createAnimal);
    }
    
    public List<Animal> ReadAll(){
        Iterable<Animal> iterAnimal = ar.findAll();

        List<Animal> listAnimal = StreamSupport
        .stream(iterAnimal.spliterator(), false)
        .toList();

        return listAnimal;
    }

    public Animal ReadSingle(Integer idAnimal){
        return GetAnimal(idAnimal);
    }

    public void Update(Animal animalUpdate){
        Animal oldAnimal = GetAnimal(animalUpdate.getId());

        oldAnimal.setExtinto(animalUpdate.getExtinto());
        oldAnimal.setNombre(animalUpdate.getNombre());
        oldAnimal.setVidaMedia(animalUpdate.getVidaMedia());

        ar.saveAndFlush(oldAnimal);
    }

    public void Delete(Integer idAnimal){
        ar.deleteById(idAnimal);
        ar.flush();
    }


    public Boolean Exist(Integer idAnimal){
        return ar.existsById(idAnimal);
    }

    public Animal GetAnimal(Integer idAnimal){
        Animal animalRead = null;
        Optional<Animal> animalFound = ar.findById(idAnimal);

        if (animalFound.isPresent()) {
            animalRead = animalFound.get();
        }
        
        return animalRead;
    }

}
