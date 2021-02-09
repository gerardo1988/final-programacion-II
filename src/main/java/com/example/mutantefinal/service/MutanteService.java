package com.example.mutantefinal.service;

import com.example.mutantefinal.dto.AdnDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class MutanteService{




    public boolean buscaMutante(String[] cad) throws Exception{

        boolean cadenaHorizontal, cadenaVertical, cadenaDiagonalSuperior, cadenaDiagonalInferior;
        int contadorMutante = 0;
        boolean esMutante;

        try {

            /*en esta variable n saco la raiz de la dimension del array eso me va a permitir hacer  luego una
            matriz cuadrada, luego n se lo paso como dimension de la matriz */
            int n = (int) Math.sqrt(cad.length);
            String analiza[][] = new String[n][n];
            int cont = 0;

            /*con este for recorro toda la matriz y lo que se encuentre en el array lo paso a la matriz para
            * despues poder realizar el analisis en forma de matriz*/
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {

                    analiza[x][y] = cad[cont];
                    cont++;

                }

            }

            /*empiezo recorriendo por las filas, la variable cadenaHorizontal se encargara de determinar si existe patrones de letras
            * a la variable la inicializo false y despues del for  para que a cada vuelta de bucle se vuelva a iniciar en false.
            * el siguiente for lo recorro hasta la posicion 3 ya que no si hay 4 letras iguales no hace falta buscar mas allá
            * si existe un patron de 4 o mas letras lo que hara sera incrementar el contador mutante*/
            for (int i = 0; i < analiza.length; i++) {

                cadenaHorizontal = false;

                for (int j = 0; j < analiza[0].length - 3; j++) {

                    if (analiza[i][j].equals("A") || analiza[i][j].equals("G") || analiza[i][j].equals("T") || analiza[i][j].equals("C")) {

                        if (analiza[i][j].equals(analiza[i][j + 1]) && analiza[i][j].equals(analiza[i][j + 2]) && analiza[i][j].equals(analiza[i][j + 3])) {

                            cadenaHorizontal = true;

                            if (cadenaHorizontal) {

                                contadorMutante++;
                            }

                        }
                    }
                }

            }

            /*en este caso se cumples los mismos requisitos que en las filas solo que en este caso empiezo a buscar en el sentido
            * de las columnas*/
            for (int j = 0; j < analiza[0].length; j++) {

                cadenaVertical = false;

                for (int i = 0; i < analiza.length - 3; i++) {

                    if (analiza[i][j].equals("A") || analiza[i][j].equals("G") || analiza[i][j].equals("T") || analiza[i][j].equals("C")) {

                        if (analiza[i][j].equals(analiza[i + 1][j]) && analiza[i][j].equals(analiza[i + 2][j]) && analiza[i][j].equals(analiza[i + 3][j])) {

                            cadenaVertical = true;

                            if (cadenaVertical) {

                                contadorMutante++;
                            }

                        }
                    }

                }

            }

            /*busco en el sentido de la diagonal principal e inferiores. empiezo por la ultima fila le resto 2 para que solo busque
            * por aquellas filas que tienen 4 elementos. tambien empiezo la variable que va a buscar la cadena en false. la variable
            * k la uso solo para que cuando se llegue a 5 el recorrido corte*/
            for (int i = 5 - 2; i >= 0; i--) {

                cadenaDiagonalInferior = false;

                for (int j = 0; j <= 5 - 3; j++) {

                    int k = j + i;

                    if (k > 5) {
                        break;
                    }

                    if (analiza[i][j].equals("A") || analiza[i][j].equals("G") || analiza[i][j].equals("T") || analiza[i][j].equals("C")) {

                        if (analiza[i][j].equals(analiza[i + 1][j + 1]) && analiza[i][j].equals(analiza[i + 2][j + 2]) && analiza[i][j].equals(analiza[i + 3][j + 3])) {

                            cadenaDiagonalInferior = true;

                            if (cadenaDiagonalInferior) {

                                contadorMutante++;

                            }

                        }
                    }

                }
            }

            /*busca por arriba de la diagonal principal. uso la misma logica que para lo anterior solo que esta vez  no busca de
            * abajo hacia arria si no que busca de arriba hacia abajo*/
            for (int i = 1; i <= analiza.length - 3; i++) {

                cadenaDiagonalSuperior = false;

                for (int j = 0; j <= analiza[0].length - 2; j++) {

                    int k = j + i;

                    if (k > 5) {
                        break;
                    }

                    if (analiza[i][j].equals("A") || analiza[i][j].equals("G") || analiza[i][j].equals("T") || analiza[i][j].equals("C")) {

                        if (analiza[i][j].equals(analiza[i][j + 1]) && analiza[i][j].equals( analiza[i][j + 2] )&& analiza[i][j].equals(analiza[i][j + 3])) {

                            cadenaDiagonalSuperior = true;

                            if (cadenaDiagonalSuperior) {

                                contadorMutante++;

                            }

                        }
                    }

                }

            }

            /*este condicional es el que me va a evalular que si el contador mutante encuentra mas de dos coincidencias, quiere
            * decir que hay mutante*/
            if (contadorMutante >= 2) {

                esMutante = true;

            } else {

                esMutante = false;

            }

            return esMutante;

        } catch (Exception e) {

            throw new Exception(e.getMessage());
        }
    }

    /*este método de tipo ResponseEntity es va a recibir un objeto de la clase dto que es la que me hace de puente y va a evaluar
    * el método busca mutante que si es true va a lanzar un status 200 OK de lo contrario lanzara un error 403 forbidden, me queda
    * la siguiente duda: yo esto lo hacia en el controlador y siempre me daba false  */
    public ResponseEntity<?> analizaMutante(AdnDTO adn) throws Exception {

        try {

            if (buscaMutante(adn.getAdn())){

                return ResponseEntity.status(HttpStatus.OK).body(true);
            }else {

                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(false);
            }


        } catch (Exception e) {

            throw new Exception(e.getMessage());
        }
    }

}
