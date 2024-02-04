package com.achchaimae.loisiresa.Domain.user.contact;

import com.achchaimae.loisiresa.Domain.user.contact.dto.ContactReqDTO;
import com.achchaimae.loisiresa.Domain.user.contact.dto.ContactRespDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactServiceInterface {

    Page<ContactRespDTO> getAll(Pageable pageable);
    ContactRespDTO saveContact(ContactReqDTO contact);
    Integer deleteContact(Integer id);
    ContactRespDTO getContact(Integer id);
    ContactRespDTO updateContact(Integer id,ContactReqDTO contact);

}
