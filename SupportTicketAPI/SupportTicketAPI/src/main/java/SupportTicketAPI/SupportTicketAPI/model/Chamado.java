package SupportTicketAPI.SupportTicketAPI.model;

import SupportTicketAPI.SupportTicketAPI.dto.ChamadoRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "CHAMADO")
@Table(name = "CHAMADOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chamado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String categoria; //Sistema - Rede - Hardware - Software - Acesso - Email - Impressora - Banco de Dados - Servidor - Outros
    private String prioridade; //Baixa - Media - Alta
    private String status; //Em aberto - Em andamento - Finalizado

    public Chamado(ChamadoRequestDTO chamadoRequestDTO) {
        this.titulo = chamadoRequestDTO.getTitulo();
        this.descricao = chamadoRequestDTO.getDescricao();
        this.categoria = chamadoRequestDTO.getCategoria();
        this.prioridade = chamadoRequestDTO.getPrioridade();
        this.status = chamadoRequestDTO.getStatus();
    }

    public Chamado(Long id, @Valid ChamadoRequestDTO chamadoRequestDTO) {
        this.id = id;
        this.titulo = chamadoRequestDTO.getTitulo();
        this.descricao = chamadoRequestDTO.getDescricao();
        this.categoria = chamadoRequestDTO.getCategoria();
        this.prioridade = chamadoRequestDTO.getPrioridade();
        this.status = chamadoRequestDTO.getStatus();
    }
}
