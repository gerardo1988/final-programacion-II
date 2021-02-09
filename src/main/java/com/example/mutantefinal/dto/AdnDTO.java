package com.example.mutantefinal.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdnDTO implements Serializable {

    //en este sector no pongo los setter y getter ni constructores ya que eso me lo da lombok con las anotaciones
    private  String adn[];

}
