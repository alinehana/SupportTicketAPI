package SupportTicketAPI.SupportTicketAPI.service;

import SupportTicketAPI.SupportTicketAPI.model.Chamado;
import SupportTicketAPI.SupportTicketAPI.repository.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado insert(Chamado chamado){
        if (chamado.getStatus() == null){
            chamado.setStatus("Aberto");
        }

        return chamadoRepository.save(chamado);
    }

    public List<Chamado> findAll(String titulo){
        if(titulo != null && !titulo.isEmpty()){
            return chamadoRepository.findAll();
        }
        return chamadoRepository.findAll();
    }

    public void delete(Long id){
        chamadoRepository.deleteById(id);
    }

    public Chamado update(Chamado chamado){
        return chamadoRepository.save(chamado);
    }
}
