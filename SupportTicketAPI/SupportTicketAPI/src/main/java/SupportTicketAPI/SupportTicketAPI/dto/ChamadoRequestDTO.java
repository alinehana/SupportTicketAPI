package SupportTicketAPI.SupportTicketAPI.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChamadoRequestDTO {

    @NotNull
    @Size(min = 1, max = 50)
    private String titulo;

    @NotNull
    @Size(min = 1, max = 100)
    private String descricao;

    @NotNull
    private String categoria;

    @NotNull
    private String prioridade;

    @NotNull
    private String status;
}
