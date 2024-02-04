package com.achchaimae.loisiresa.Domain.user.contact;

import com.achchaimae.loisiresa.Domain.user.contact.dto.ContactReqDTO;
import com.achchaimae.loisiresa.Domain.user.contact.dto.ContactRespDTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ContactService implements ContactServiceInterface{
    ContactRepository contactRepository;
    ModelMapper modelMapper;

    // create a contact
    public ContactRespDTO saveContact(ContactReqDTO contact)
    {
        contact.setFirstDateContact(LocalDate.now() );
        return modelMapper.map(
                contactRepository.save(modelMapper.map(contact,Contact.class)),
                ContactRespDTO.class
        );
    }
    // delete a contact
    public Integer deleteContact(Integer id)
    {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        return contactOptional.map(contact -> {
            contactRepository.delete(contactOptional.get());
            return 1;
        }).orElse(0);
    }

    // get one contact
    public ContactRespDTO getContact(Integer id) {
        Optional<Contact> student = contactRepository.findById(id);
        return modelMapper.map(student.orElse(null), ContactRespDTO.class);
    }

    // update  contact
    public ContactRespDTO updateContact(Integer id,ContactReqDTO contact)
    {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        return contactOptional.map(contact1 -> {
            contact.setId(id);
            return modelMapper.map(
                    contactRepository.save(modelMapper.map(contact,Contact.class)),
                    ContactRespDTO.class
            );
        }).orElse(null);
    }

    // get all contact , paginated
    public Page<ContactRespDTO> getAll(Pageable pageable)
    {
        Page<Contact> entityPage = contactRepository.findAll(pageable);
        return entityPage.map(entity -> modelMapper.map(entity, ContactRespDTO.class));
    }






}
