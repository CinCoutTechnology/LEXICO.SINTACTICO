package com.example.analyzer.view.An;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class lexi {
    static String[][] tas;
    private String X;
    private String a;
    static Stack pila;
    private int apuntador;
    public static ArrayList movimientos = new ArrayList();
    private int estado;

    public lexi() {
        tas = new String[11][20];
    }

    public static String[][] getTas() {
        return tas;
    }

    public void cargarTabla() {
        tas[1][0] = "P";
        tas[2][0] = "P'";
        tas[3][0] = "P''";
        tas[4][0] = "E";
        tas[5][0] = "E'";
        tas[6][0] = "S";
        tas[7][0] = "S'";
        tas[8][0] = "T";
        tas[9][0] = "T'";
        tas[10][0] = "F";

        tas[0][1] = "id";
        tas[0][2] = "i";
        tas[0][3] = "t";
        tas[0][4] = "w";
        tas[0][5] = "d";
        tas[0][6] = "e";
        tas[0][7] = "=";
        tas[0][8] = "==";
        tas[0][9] = "<=";
        tas[0][10] = "=>";
        tas[0][11] = "<>";
        tas[0][12] = "+";
        tas[0][13] = "-";
        tas[0][14] = "*";
        tas[0][15] = "/";
        tas[0][16] = "num";
        tas[0][17] = "(";
        tas[0][18] = ")";
        tas[0][19] = "$";

        tas[1][1] = "P -> id P''";
        tas[1][2] = "P -> i E t P P'";
        tas[1][4] = "P -> w E d P";

        tas[2][6] = "P' -> e P";
        tas[2][19] = "P' -> Ð„";

        tas[3][6] = "P'' -> Ð„";
        tas[3][7] = "P'' -> = E";
        tas[3][19] = "P'' -> Ð„";

        tas[4][1] = "E -> S E'";
        tas[4][12] = "E -> S E'";
        tas[4][13] = "E -> S E'";
        tas[4][16] = "E -> S E'";
        tas[4][17] = "E -> S E'";

        tas[5][3] = "E' -> Ð„";
        tas[5][5] = "E' -> Ð„";
        tas[5][6] = "E' -> Ð„";
        tas[5][8] = "E' -> == S";
        tas[5][9] = "E' -> <= S";
        tas[5][10] = "E' -> => S";
        tas[5][11] = "E' -> <> S";
        tas[5][18] = "E' -> Ð„";
        tas[5][19] = "E' -> Ð„";

        tas[6][1] = "S -> T S'";
        tas[6][12] = "S -> + S'";
        tas[6][13] = "S -> - S'";
        tas[6][16] = "S -> T S'";
        tas[6][17] = "S -> T S'";

        tas[7][3] = "S' -> Ð„";
        tas[7][5] = "S' -> Ð„";
        tas[7][6] = "S' -> Ð„";
        tas[7][8] = "S' -> Ð„";
        tas[7][9] = "S' -> Ð„";
        tas[7][10] = "S' -> Ð„";
        tas[7][11] = "S' -> Ð„";
        tas[7][12] = "S' -> + T S'";
        tas[7][13] = "S' -> - T S'";
        tas[7][18] = "S' -> Ð„";
        tas[7][19] = "S' -> Ð„";

        tas[8][1] = "T -> F T'";
        tas[8][16] = "T -> F T'";
        tas[8][17] = "T -> F T'";

        tas[9][3] = "T' -> Ð„";
        tas[9][5] = "T' -> Ð„";
        tas[9][6] = "T' -> Ð„";
        tas[9][7] = "T' -> Ð„";
        tas[9][8] = "T' -> Ð„";
        tas[9][9] = "T' -> Ð„";
        tas[9][10] = "T' -> Ð„";
        tas[9][11] = "T' -> Ð„";
        tas[9][12] = "T' -> Ð„";
        tas[9][12] = "T' -> Ð„";
        tas[9][12] = "T' -> Ð„";
        tas[9][12] = "T' -> Ð„";
        tas[9][13] = "T' -> Ð„";
        tas[9][14] = "T' -> * F T'";
        tas[9][15] = "T' -> / F T'";
        tas[9][18] = "T' -> Ð„";
        tas[9][19] = "T' -> Ð„";

        tas[10][1] = "F -> id";
        tas[10][16] = "F -> num";
        tas[10][17] = "F -> ( E )";
    }


    public String[] splitString(String linea) {
        StringTokenizer lex = new StringTokenizer(linea);

        String[] texto = new String[lex.countTokens()];
        for (int i = 0; i < texto.length; i++) {
            texto[i] = lex.nextToken();
        }
        return texto;
    }

    public boolean esTerminal(String cadena) {
        for (int i = 0; i < tas[0].length; i++) {
            if (cadena.equals(tas[0][i])) {
                return true;
            }
        }
        if ((cadena.equals("Ð„")) || (cadena.equals("="))
                || (cadena.equals("==")) || (cadena.equals("<="))
                || (cadena.equals(">="))) {
            return true;
        }
        return false;
    }

    public String getGrammar(String noTerminal, String terminal) {
        int row = getRow(noTerminal);
        int col = getColumn(terminal);
        if ((row > 0) && (col > 0)) {
            return tas[row][col];
        }
        return "";
    }

    public int getRow(String cadena) {
        int row = 0;
        for (int i = 1; i < tas.length; i++) {
            if (tas[i][0].equals(cadena)) {
                row = i;
                break;
            }
        }
        return row;
    }

    public int getColumn(String cadena) {
        int column = 0;
        for (int j = 1; j < tas[0].length; j++) {
            if (tas[0][j].equals(cadena)) {
                column = j;
                break;
            }
        }
        return column;
    }

    public String[] getProductions(String[] tokens) {
        String[] cad = new String[tokens.length - 2];
        for (int i = 2; i < tokens.length; i++) {
            cad[(i - 2)] = tokens[i];
        }
        return cad;
    }

    public String printPila(Stack pila) {
        String cadena = "";
        for (int i = 0; i < pila.size(); i++) {
            cadena = cadena + pila.get(i);
            if (i != pila.size() - 1) {
                cadena = cadena + " ";
            }
        }
        if (cadena.length() < 12) {
            for (int i = cadena.length(); i <= 12; i++) {
                cadena = cadena + " ";
            }
        }
        return cadena;
    }

    public ArrayList analizar(String entrada) throws IOException {
        this.estado = 1;

        //noinspection rawtypes
        pila = new Stack();
        //noinspection unchecked
        pila.push("$");
        //noinspection unchecked
        pila.push(tas[1][0]);

        String[] tokens = splitString(entrada + " " + "$");
        Log.e("tokens ", tokens.toString());
        this.apuntador = 0;
        this.X = ((String) pila.peek());
        this.a = tokens[0];

        int indexM = movimientos.size();
        //noinspection unchecked
        movimientos.add("");
        if (!this.X.equals("$")) {
            ArrayList data = new ArrayList();
            data.add(printPila(pila));
            data.add(this.a);
            data.add("");

            movimientos.add(data);
        } else {
            ArrayList data = new ArrayList();
            data.add(" - ERROR - ");
            data.add(" - ERROR - ");
            data.add(" - ERROR - ");

            movimientos.add(data);
        }
        while (!this.X.equals("$")) {
            if ((esTerminal(this.X)) || (this.X.equals("$"))) {
                if (this.X.equals(this.a)) {
                    pila.pop();

                    this.X = ((String) pila.peek());

                    this.apuntador += 1;
                    this.a = tokens[this.apuntador];

                    ArrayList data = new ArrayList();
                    data.add(printPila(pila));
                    data.add(this.a);
                    data.add("");

                    movimientos.add(data);
                } else {
                    ArrayList data = new ArrayList();
                    data.add(" - ERROR - ");
                    data.add(" - ERROR - ");
                    data.add(" - ERROR - ");

                    movimientos.add(data);
                    this.estado = 0;
                    break;
                }
            } else {
                String gram = getGrammar(this.X, this.a);
                if (gram != null) {
                    if (!gram.equals("")) {
                        pila.pop();
                        String[] cad = getProductions(splitString(gram));
                        for (int i = cad.length - 1; i >= 0; i--) {
                            if (!cad[i].equals("Ð„")) {
                                pila.push(cad[i]);
                            }
                        }
                        ArrayList data = new ArrayList();
                        data.add(printPila(pila));
                        data.add(this.a);
                        data.add(Arrays.toString(splitString(gram)));

                        movimientos.add(data);
                        this.X = ((String) pila.peek());
                    } else {
                        ArrayList data = new ArrayList();
                        data.add(" - ERROR - ");
                        data.add(" - ERROR - ");
                        data.add(" - ERROR - ");

                        movimientos.add(data);
                        this.estado = 0;
                        break;
                    }
                } else {
                    ArrayList data = new ArrayList();
                    data.add(" - ERROR - ");
                    data.add(" - ERROR - ");
                    data.add(" - ERROR - ");

                    movimientos.add(data);
                    this.estado = 0;
                    break;
                }
            }
        }
        String ok;
        if (this.estado == 1) {
            ok = "Reconocida";
        } else {
            ok = "NO Reconocida";
        }
        ArrayList data1 = new ArrayList();
        data1.add(entrada);
        data1.add("");
        data1.add("");
        data1.add(ok);
        movimientos.set(indexM, data1);
        pila.clear();
        return movimientos;
    }
}
