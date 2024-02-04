package com.achchaimae.loisiresa.Domain.user.client;

import com.achchaimae.loisiresa.Domain.user.client.dto.ClientReqDTO;
import com.achchaimae.loisiresa.Domain.user.client.dto.ClientRespDTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ClientService implements ClientServiceInterface {
    ClientRepository clientRepository;
    ModelMapper modelMapper;

    // create a client
    public ClientRespDTO saveClient(ClientReqDTO client)
    {
        client.setDateOfSubscription(LocalDate.now() );
        return modelMapper.map(
                clientRepository.save(modelMapper.map(client, Client.class)),
                ClientRespDTO.class
        );
    }

    // delete a client
    public Integer deleteClient(Integer id)
    {
        Optional<Client> clientOptional = clientRepository.findById(id);
        return clientOptional.map(client -> {
            clientRepository.delete(clientOptional.get());
            return 1;
        }).orElse(0);
    }

    // get one client
    public ClientRespDTO getClient(Integer id) {
        Optional<Client> client = clientRepository.findById(id);
        return modelMapper.map(client.orElse(null), ClientRespDTO.class);
    }

    // update  client
    public ClientRespDTO updateClient(Integer id,ClientReqDTO client)
    {
        Optional<Client> clientOptional = clientRepository.findById(id);
        return clientOptional.map(client1 -> {
            client.setId(id);
            return modelMapper.map(
                    clientRepository.save(modelMapper.map(client,Client.class)),
                    ClientRespDTO.class
            );
        }).orElse(null);
    }

    // get all client , paginated
    public Page<ClientRespDTO> getAll(Pageable pageable)
    {
        Page<Client> entityPage = clientRepository.findAll(pageable);
        return entityPage.map(entity -> modelMapper.map(entity, ClientRespDTO.class));
    }

}
