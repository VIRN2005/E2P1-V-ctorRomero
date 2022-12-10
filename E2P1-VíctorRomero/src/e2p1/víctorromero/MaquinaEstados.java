package e2p1.víctorromero;

import java.util.ArrayList;

public class MaquinaEstados {

    public ArrayList<String> estados = new ArrayList<>();
    public ArrayList<String> estados_aceptacion = new ArrayList<>();
    public ArrayList<String> Aristas = new ArrayList<>();
    public String estado_actual;

    public MaquinaEstados(String estados, String aristas) {
        this.estados = splitStr(estados, ';');
        extractAcceptNodes();
        this.Aristas = splitStr(estados, ';');
        this.estado_actual = this.estados.get(0);
        System.out.println("hii");
    }

    public ArrayList<String> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<String> estados) {
        this.estados = estados;
    }

    public ArrayList<String> getEstados_aceptacion() {
        return estados_aceptacion;
    }

    public void setEstados_aceptacion(ArrayList<String> estados_aceptacion) {
        this.estados_aceptacion = estados_aceptacion;
    }

    public ArrayList<String> getAristas() {
        return Aristas;
    }

    public void setAristas(ArrayList<String> Aristas) {
        this.Aristas = Aristas;
    }

    public String getEstado_actual() {
        return estado_actual;
    }

    public void setEstado_actual(String estado_actual) {
        this.estado_actual = estado_actual;
    }

    public ArrayList<String> splitStr(String Str, char delim) {
        String[] Split = Str.split(Character.toString(delim));

        ArrayList<String> Chain = new ArrayList<>();

        for (int i = 0; i < Split.length; i++) {
            Chain.add(Split[i]);
        }
        return Chain;
    }

    public void extractAcceptNodes() {
        String SubChain = "";

        for (int i = 0; i < estados.size(); i++) {
            if (estados.get(i).contains(".")) {
                SubChain = estados.get(i).substring(1);
                estados_aceptacion.add(estados.get(i));
                estados.set(i, SubChain);
            }
        }
    }

    public String computeStr(String cadena) {
        System.out.println("entro almetodo");
        estado_actual = estados.get(0);
        String output = "";

        for (int i = 0; i < cadena.length(); i++) {
            String ar = getArista(estado_actual + ',' + cadena.charAt(i));
            if (!ar.equals("")) {
                estado_actual = ar.split(",")[2];
                output += ar.split(",")[0] + ':' + cadena.charAt(i) + "->" + ar.split(",")[2] + '\n';;
            } else {
                output += "Rechazada \n";
                return output;
            }
        }

        if (estados_aceptacion.contains(estado_actual)) {
            output += "Aceptada \n";
            
        } else {
            output += "Rechazada \n";
           
        }
        return output;
    }

    public String getArista(String str) {

        for (int i = 0; i < Aristas.size(); i++) {
            if (Aristas.get(i).contains(str)) {
                return Aristas.get(i);
            }
        }
        return "";
    }

}
