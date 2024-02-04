package com.achchaimae.loisiresa.Domain.user.contact;

import com.achchaimae.loisiresa.Domain.user.contact.dto.ContactReqDTO;
import com.achchaimae.loisiresa.Domain.user.contact.dto.ContactRespDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class ContactController {
     ContactServiceInterface  contactServiceInterface;

    public ContactController(ContactServiceInterface contactServiceInterface) {
        this.contactServiceInterface = contactServiceInterface;
    }
    @PostMapping
    public ResponseEntity<ContactRespDTO> createContact(@RequestBody ContactReqDTO contact)
    {
        ContactRespDTO contact1 = contactServiceInterface.saveContact(contact);
        if(contact1 != null)
        {
            return ResponseEntity.ok().body(contact1);
        }
        return ResponseEntity.badRequest().body(null);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ContactRespDTO> updateContact(@PathVariable Integer id, @RequestBody ContactReqDTO contact)
    {
        ContactRespDTO contact1 = contactServiceInterface.updateContact(id,contact);
        if(contact1 != null)
        {
            return ResponseEntity.ok().body(contact1);
        }
        return ResponseEntity.badRequest().body(null);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ContactRespDTO> getContact(@PathVariable Integer id)
    {
        return ResponseEntity.ok().body(contactServiceInterface.getContact(id));
    }
    @GetMapping
    public ResponseEntity<Page<ContactRespDTO>> getContacts(Pageable pageable)
    {
        return ResponseEntity.ok().body(contactServiceInterface.getAll(pageable));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Integer id)
    {
        Integer deleted = contactServiceInterface.deleteContact(id);
        if(deleted == 1){
            return ResponseEntity.ok().body("Contact deleted ?");
        }
        return ResponseEntity.ok().body("Contact not deleted ?");
    }
}
