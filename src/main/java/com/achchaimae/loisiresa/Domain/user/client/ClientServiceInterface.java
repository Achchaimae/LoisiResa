package com.achchaimae.loisiresa.Domain.user.client;

import com.achchaimae.loisiresa.Domain.user.client.dto.ClientReqDTO;
import com.achchaimae.loisiresa.Domain.user.client.dto.ClientRespDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientServiceInterface {
    ClientRespDTO saveClient(ClientReqDTO client);
    Integer deleteClient(Integer id);
    ClientRespDTO getClient(Integer id);
    ClientRespDTO updateClient(Integer id,ClientReqDTO client);
    Page<ClientRespDTO> getAll(Pageable pageable);

}
