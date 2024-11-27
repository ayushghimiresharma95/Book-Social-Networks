package com.example.BookNetworkServer.Common;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue
    private Integer id; 

    @CreatedDate
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdDate ;

    @LastModifiedBy
    @Column(insertable = false) 
    private String lastModifiedBy ;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate ;


    @CreatedBy
    @Column(nullable = false,updatable = false) 
    private String  createdBy ; 



}