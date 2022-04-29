package nl.novi.techiteeasy.Models.Repositories;

import nl.novi.techiteeasy.Models.Television;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelevisionRepository extends JpaRepository <Television, Long> {

    List<Television> findAllTelevisionsByBrandEqualsIgnoreCase(String brand);

}
