package com.achchaimae.loisiresa.Domain.user.client;

import com.achchaimae.loisiresa.Domain.user.client.dto.ClientReqDTO;
import com.achchaimae.loisiresa.Domain.user.client.dto.ClientRespDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    ClientServiceInterface clientServiceInterface;
    public ClientController(ClientServiceInterface clientServiceInterface) {
        this.clientServiceInterface = clientServiceInterface;
    }

    @PostMapping
    public ResponseEntity<ClientRespDTO> createClient(@RequestBody ClientReqDTO client)
    {
        ClientRespDTO client1 = clientServiceInterface.saveClient(client);
        if(client1 != null)
        {
            return ResponseEntity.ok().body(client1);
        }
        return ResponseEntity.badRequest().body(null);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClientRespDTO> updateClient(@PathVariable Integer id, @RequestBody ClientReqDTO client)
    {
        ClientRespDTO client1 = clientServiceInterface.updateClient(id,client);
        if(client1 != null)
        {
            return ResponseEntity.ok().body(client1);
        }
        return ResponseEntity.badRequest().body(null);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClientRespDTO> getClient(@PathVariable Integer id)
    {
        return ResponseEntity.ok().body(clientServiceInterface.getClient(id));
    }
    @GetMapping
    public ResponseEntity<Page<ClientRespDTO>> getClients(Pageable pageable)
    {
        return ResponseEntity.ok().body(clientServiceInterface.getAll(pageable));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Integer id)
    {
        Integer deleted = clientServiceInterface.deleteClient(id);
        if(deleted == 1){
            return ResponseEntity.ok().body("Client deleted ?");
        }
        return ResponseEntity.ok().body("Client not deleted ?");
    }
}
