package nl.novi.techiteeasy.Services;

import nl.novi.techiteeasy.Dtos.IdInputDto;
import nl.novi.techiteeasy.Dtos.TelevisionDto;
import nl.novi.techiteeasy.Dtos.TelevisionInputDto;
import nl.novi.techiteeasy.Exceptions.RecordNotFoundException;
import nl.novi.techiteeasy.Models.Television;
import nl.novi.techiteeasy.Models.Repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TelevisionService {

    private final TelevisionRepository televisionRepository;

    @Autowired
    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    public List<TelevisionDto> getAllTelevisions() {
        List<TelevisionDto> televisionDtos = new ArrayList<>();
        List<Television> televisions = televisionRepository.findAll();
        for (Television television : televisions) {
            televisionDtos.add(fromTelevision(television));
        }
        return televisionDtos;
    }

    public List<TelevisionDto> getAllTelevisionsByBrand(String brand) {
        List<TelevisionDto> televisionDtos = new ArrayList<>();
        List<Television> televisions = televisionRepository.findAllTelevisionsByBrandEqualsIgnoreCase(brand);
        for (Television television : televisions) {
            televisionDtos.add(fromTelevision(television));
        }
        return televisionDtos;
    }

    public TelevisionDto getTelevision(IdInputDto id) {
        Television televisionFound = televisionRepository.getById(id.getId());
        if (televisionFound == null) {
            throw new RecordNotFoundException("cannot find book");
        }
        return fromTelevision(televisionFound);
    }

    public TelevisionDto saveTelevision(TelevisionInputDto televisionInputDto) {
        Television television = toTelevision(televisionInputDto);
        televisionRepository.save(television);
        return fromTelevision(television);
    }

    public TelevisionDto updateTelevision(TelevisionInputDto televisionInputDto, IdInputDto id) {
        Television existingTelevision = televisionRepository.findById(televisionInputDto.getId()).orElse(null);
        assert existingTelevision != null;
        existingTelevision.setPrice(televisionInputDto.getPrice());
        existingTelevision.setOriginalStock(televisionInputDto.getOriginalStock());
        existingTelevision.setSold(televisionInputDto.getSold());
        televisionRepository.save(existingTelevision);
        return fromTelevision(existingTelevision);
    }

    public String deleteTelevision(IdInputDto id) {
        televisionRepository.deleteById(id.getId());
        return "Product removed!! " + id;
    }

    public static TelevisionDto fromTelevision(Television television) {
        var dto = new TelevisionDto();
        dto.setId(television.getId());
        dto.setType(television.getType());
        dto.setBrand(television.getBrand());
        dto.setName(television.getName());
        dto.setPrice(television.getPrice());
        dto.setAvailableSize(television.getAvailableSize());
        dto.setRefreshRate(television.getRefreshRate());
        dto.setScreenType(television.getScreenType());
        dto.setScreenQuality(television.getScreenQuality());
        dto.setSmartTv(television.getSmartTv());
        dto.setWifi(television.getWifi());
        dto.setVoiceControl(television.getVoiceControl());
        dto.setHdr(television.getHdr());
        dto.setBluetooth(television.getBluetooth());
        dto.setAmbiLight(television.getAmbiLight());
        dto.setOriginalStock(television.getOriginalStock());
        dto.setSold(television.getSold());

        return dto;
    }

    public static Television toTelevision(TelevisionInputDto dto) {
        var television = new Television();
        television.setId(dto.getId());
        television.setBrand(dto.getBrand());
        television.setType(dto.getType());
        television.setName(dto.getName());
        television.setPrice(dto.getPrice());
        television.setAvailableSize(dto.getAvailableSize());
        television.setScreenQuality(dto.getScreenQuality());
        television.setSmartTv(dto.getSmartTv());
        television.setWifi(dto.getWifi());
        television.setVoiceControl(dto.getVoiceControl());
        television.setHdr(dto.getHdr());
        television.setBluetooth(dto.getBluetooth());
        television.setAmbiLight(dto.getAmbiLight());
        television.setOriginalStock(dto.getOriginalStock());
        television.setSold(dto.getSold());
        return television;
    }


}
