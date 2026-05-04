package com.example.personal_finance_tracker_api.common.audit;

import java.time.LocalDateTime;

public interface Auditable {
   /* LocalDateTime getCreatedAt();
    void setCreatedAt(LocalDateTime createdAt);

    LocalDateTime getUpdatedAt();
    void setUpdatedAt(LocalDateTime updatedAt);


    Long getCreatedBy();
    void setCreatedBy(Long createdBy);

    Long getUpdatedBy();
    void setUpdatedBy(Long updatedBy);*/

}






//Auditing
/*
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Auditable {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;


    @LastModifiedDate
    private LocalDateTime updatedAt;
}*/





//Specification
/*
public class ContactSpecification {

    public static Specification<Contact> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) ->
                firstName == null ? null : criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%");
    }

    public static Specification<Contact> hasLastName(String lastName) {
        return (root, query, criteriaBuilder) ->
                lastName == null ? null : criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%");
    }

    public static Specification<Contact> hasPhone(String phone) {
        return (root, query, criteriaBuilder) ->
                phone == null ? null : criteriaBuilder.like(root.get("phone"), "%" + phone + "%");
    }

    public static Specification<Contact> hasEmail(String email) {
        return (root, query, criteriaBuilder) ->
                email == null ? null : criteriaBuilder.like(root.get("email"), "%" + email + "%");
    }

    public static Specification<Contact> hasCategory(String category) {
        return (root, query, criteriaBuilder) ->
                category == null ? null : criteriaBuilder.equal(root.get("category"), category);
    }

    public static Specification<Contact> isActive(Boolean isActive) {
        return (root, query, criteriaBuilder) ->
                isActive == null ? null : criteriaBuilder.equal(root.get("isActive"), isActive);
    }
}
*/


/*
public Page<ContactResponseDto> search(String firstname,String lastname,String phone,
                                       String email, Boolean isActive,String category,Pageable pageable)
{

    Specification<Contact> spec=Specification.where(ContactSpecification.
                    hasFirstName(firstname)).and(ContactSpecification.hasLastName(lastname)).
            and(ContactSpecification.hasPhone(phone)).and(ContactSpecification.hasEmail(email)).
            and(ContactSpecification.isActive(isActive)).and(ContactSpecification.hasCategory(category));

    return repository.findAll(spec,pageable).map(mapper::toDto);

}
*/


/*
@GetMapping("/specification")
public ResponseEntity<Page<ContactResponseDto>> search(
        @RequestParam(required = false) String firstname,
        @RequestParam(required = false) String lastname,
        @RequestParam(required = false) String phone,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) Boolean isActive,
        @RequestParam(required = false) String category,
        Pageable pageable
)
{
    return ResponseEntity.ok(service.search(firstname,lastname,phone,email,isActive,category,pageable));
}
*/
































//Projection
/*
public interface ContactSummary {
    String getFirstName();
    String getEmail();
}
*/
