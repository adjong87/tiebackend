package nl.novi.techiteeasy.Controllers;

import nl.novi.techiteeasy.Dtos.IdInputDto;
import nl.novi.techiteeasy.Dtos.TelevisionDto;
import nl.novi.techiteeasy.Dtos.TelevisionInputDto;
import nl.novi.techiteeasy.Services.TelevisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    private final TelevisionService televisionService;

    @Autowired
    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

// Het endpoint voor het GET request om alle televisies op te vragen
//    @GetMapping("")
//    public List<TelevisionDto> getAllTelevisions(){
//        return televisionService.getAllTelevisions();
//    }

// Het endpoint voor de GET request voor een specifiek merk televisies
    @GetMapping("")
    public ResponseEntity<List<TelevisionDto>> getAllTelevisionsByBrand(@RequestParam(value = "brand", required = false) String brand){
        List<TelevisionDto> televisions;
        if (brand == null){
            televisions = televisionService.getAllTelevisions();
            return ResponseEntity.ok().body(televisions);
        } else {
            televisions = televisionService.getAllTelevisionsByBrand(brand);
        }
        return ResponseEntity.ok().body(televisions);
    }

// Het endpoint voor de GET request voor een specifieke televisie
    @GetMapping("{id}")
    public TelevisionDto getTelevision(@PathVariable IdInputDto id) {
        return televisionService.getTelevision(id);
    }

// Het endpoint voor de POST request
    @PostMapping()
    public TelevisionDto saveTelevision(@RequestBody TelevisionInputDto televisionInputDto) {
        return televisionService.saveTelevision(televisionInputDto);
    }

// Het endpoint voor de PUT request
    @PutMapping("/update/{id}")
    public TelevisionDto updateTelevision(@RequestBody TelevisionInputDto televisionInputDto,
                                       @PathVariable IdInputDto id) {
        return televisionService.updateTelevision(televisionInputDto, id);
    }

// Het endpoint voor het DELETE request
    @DeleteMapping("delete")
    public String deleteTelevision(@PathVariable IdInputDto id) {
        return televisionService.deleteTelevision(id);
    }

}



