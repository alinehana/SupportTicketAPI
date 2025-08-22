# 🎫 SupportTicketAPI
API REST em **Spring Boot** para gerenciamento de chamados de suporte.  
Permite **criar, listar, atualizar e excluir** tickets armazenados em banco de dados SQL.  

---

### 🎯 Objetivo Geral
Oferecer uma solução backend para registro e controle de chamados de suporte, garantindo organização, rastreabilidade e padronização das operações CRUD.  

---

### 🚀 Tecnologias Utilizadas
- ☕ Java 21
- 🍃 Spring Boot (Web, Data JPA, Validation, DevTools)
- 🐘 PostgreSQL
- 🧰 Lombok
- 📦 Maven

---

### 🛠 Ferramentas
- Postman / Insomnia (teste de endpoints)
- Git e GitHub
- IDE (IntelliJ / STS / Eclipse)
- PgAdmin (gerenciamento do banco)

---

### 🔌 Como funciona a API SupportTicketAPI
O sistema segue o fluxo típico de uma **API RESTful**:  

1. **Recepção da requisição**  
   - O cliente envia uma requisição HTTP para o endpoint no `ChamadoController`.  

2. **Validação e conversão**  
   - Os dados recebidos são encapsulados em um `ChamadoRequestDTO`, que aplica validações via **Spring Validation**.  

3. **Regras de negócio**  
   - O `ChamadoService` processa a lógica de negócio.  

4. **Persistência**  
   - O `ChamadoRepository` utiliza **Spring Data JPA** para salvar ou recuperar dados.  

5. **Resposta**  
   - O `ChamadoController` retorna a resposta adequada (`200 OK`, `201 Created`, `204 No Content`).  

---

### 📌 Funcionalidades
✅ Criar novo chamado

✅ Listar chamados (com filtro por título)

✅ Atualizar chamado existente

✅ Excluir chamado

---

### 📌 Exemplos de Endpoints  

## ➕ Criar chamado  
```java
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
```

---

## 📋 Listar chamados
```java
@GetMapping
public ResponseEntity<List<Chamado>> findAll(
        @RequestParam(value = "titulo", required = false) String titulo) {
    List<Chamado> retorno = chamadoService.findAll(titulo);
    return ResponseEntity.ok(retorno);
}
```

📌 Lista todos os chamados ou filtra por título.
Exemplo de chamada:

GET /chamados
GET /chamados?titulo=erro

---

## ❌ Excluir chamado
```java
@DeleteMapping("{id}")
public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    chamadoService.delete(id);
    return ResponseEntity.noContent().build();
}
```

📌 Remove um chamado pelo id e retorna 204 No Content.
Exemplo de chamada:

DELETE /chamados/1

---

## ✏️ Atualizar chamado
```java
@PutMapping("{id}")
public ResponseEntity<Chamado> update(
        @PathVariable Long id,
        @RequestBody @Valid ChamadoRequestDTO chamadoRequestDTO) {

    Chamado chamado = new Chamado(id, chamadoRequestDTO);
    chamado = chamadoService.update(chamado);

    return ResponseEntity.ok(chamado);
}
```

📌 Atualiza os dados de um chamado existente e retorna 200 OK com o objeto atualizado.
Exemplo de chamada:

PUT /chamados/1
