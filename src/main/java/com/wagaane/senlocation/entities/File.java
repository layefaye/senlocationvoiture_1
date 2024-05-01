package com.wagaane.senlocation.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.Hibernate;

import java.util.Objects;

/**
 * @author Abdou Karim CISSOKHO
 * @created 08/11/2023-16:27
 * @project gestion_courriers
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SequenceGenerator(name = "seq_file", initialValue = 100, allocationSize = 2, sequenceName = "seq_file")
@Table(name = "TD_FILE")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_file")
    @Column(nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "generated_name")
    private String generatedName;

    @Column(name = "file_code")
    private String fileCode;

    @Column(name = "download_url")
    private String downloadUrl;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_size")
    private long fileSize;


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        File file = (File) o;
        return id != null && Objects.equals(id, file.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}