package SupportTicketAPI.SupportTicketAPI.controller;

import SupportTicketAPI.SupportTicketAPI.dto.ChamadoRequestDTO;
import SupportTicketAPI.SupportTicketAPI.model.Chamado;
import SupportTicketAPI.SupportTicketAPI.service.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {
    @Autowired
    private ChamadoService chamadoService;

    @PostMapping
    public ResponseEntity<Chamado> insert(@RequestBody @Valid ChamadoRequestDTO chamadoRequestDTO, UriComponentsBuilder builder) {
        Chamado chamado = new Chamado(chamadoRequestDTO);
        chamado = chamadoService.insert(chamado);

        URI uri = builder.path("/chamado/{id}").buildAndExpand(chamado.getId()).toUri();
        return ResponseEntity.created(uri).body(chamado);
    }

    @GetMapping
    public ResponseEntity<List<Chamado>> findAll(
            @RequestParam(value = "titulo", required = false) String titulo) {
        List<Chamado> retorno = chamadoService.findAll(titulo);
        return ResponseEntity.ok(retorno);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        chamadoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Chamado> update(@PathVariable Long id,
                                          @RequestBody @Valid ChamadoRequestDTO chamadoRequestDTO) {
    Chamado chamado = new Chamado (id, chamadoRequestDTO);

    chamado = chamadoService.update(chamado);
    return ResponseEntity.ok(chamado);
    }
}
