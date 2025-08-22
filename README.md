🎫 SupportTicketAPI

API REST em Spring Boot para gerenciamento de chamados de suporte.
Permite criar, listar, atualizar e excluir tickets armazenados em banco de dados SQL.

🚀 Tecnologias

☕ Java 21

🍃 Spring Boot (Web, Data JPA, Validation, DevTools)

🐘 PostgreSQL

🧰 Lombok

📦 Maven

📂 Estrutura

O projeto segue a arquitetura em camadas:

Model → representa a entidade Chamado.

DTO → recebe dados da requisição (ChamadoRequestDTO).

Repository → interface JPA para persistência.

Service → contém as regras de negócio.

Controller → expõe os endpoints REST.

📌 Controller

O ChamadoController é o ponto de entrada da API:

@RestController
@RequestMapping("/chamados")
public class ChamadoController {
    @Autowired
    private ChamadoService chamadoService;

➕ Criar chamado
@PostMapping
public ResponseEntity<Chamado> insert(
        @RequestBody @Valid ChamadoRequestDTO chamadoRequestDTO,
        UriComponentsBuilder builder) {

    Chamado chamado = new Chamado(chamadoRequestDTO);
    chamado = chamadoService.insert(chamado);

    URI uri = builder.path("/chamado/{id}")
                     .buildAndExpand(chamado.getId())
                     .toUri();

    return ResponseEntity.created(uri).body(chamado);
}


📌 Cria um novo chamado e retorna 201 Created com a URI do recurso.

📋 Listar chamados
@GetMapping
public ResponseEntity<List<Chamado>> findAll(
        @RequestParam(value = "titulo", required = false) String titulo) {
    List<Chamado> retorno = chamadoService.findAll(titulo);
    return ResponseEntity.ok(retorno);
}


📌 Lista todos os chamados ou filtra por título (GET /chamados?titulo=login).

❌ Deletar chamado
@DeleteMapping("{id}")
public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    chamadoService.delete(id);
    return ResponseEntity.noContent().build();
}


📌 Remove um chamado pelo id e retorna 204 No Content.

✏️ Atualizar chamado
@PutMapping("{id}")
public ResponseEntity<Chamado> update(
        @PathVariable Long id,
        @RequestBody @Valid ChamadoRequestDTO chamadoRequestDTO) {

    Chamado chamado = new Chamado(id, chamadoRequestDTO);
    chamado = chamadoService.update(chamado);

    return ResponseEntity.ok(chamado);
}


📌 Atualiza os dados de um chamado existente e retorna o objeto atualizado.
