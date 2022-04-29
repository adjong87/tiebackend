package nl.novi.techiteeasy.Dtos;

import javax.validation.constraints.NotBlank;

public class IdInputDto {

    @NotBlank
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
