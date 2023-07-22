package com.business.backendBusiness.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "imagenes")
public class ImageTest {
    @Id
    @Column(name = "idimagenes", nullable = false)
    private Long idimagenes;

    @Column(name = "descripcion")
    private String descripcion;

    @Lob
    @Column(name = "imagen")
    private byte[] imagen;

    public Long getIdimagenes() {
        return idimagenes;
    }

    public void setIdimagenes(Long idimagenes) {
        this.idimagenes = idimagenes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
