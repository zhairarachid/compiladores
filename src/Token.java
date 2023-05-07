

public class Token {
    private String token;
    private String lexema;
    private int linha;
    private int coluna;

    public Token(String token, String lexema, int linha, int coluna) {
        this.token = token;
        this.lexema = lexema;
        this.linha = linha;
        this.coluna = coluna;
    }
    
    public Token() {
        this.token = "";
        this.lexema = "";
        this.linha = 0;
        this.coluna = 0;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
        if (this.linha == 11){
            
        }
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

   public String imprimirTokens(){
        String token    = ajuste(getToken(), 20);
        String lexema   = ajuste(getLexema(), 20);
        String linha   =  ajuste(Integer.toString(getLinha()), 4);
        String coluna   = ajuste(Integer.toString(getColuna()), 4);

        return token+"|"+lexema+"| "+linha+"   | "+coluna;
   }


   public String ajuste(String texto, int total){
    String textoAux = "'";
    int i           = 0;
    while(i < total-1){
        if(texto.length() > i){
            textoAux += texto.charAt(i);
        }else{
            if(texto.length() == i){
                textoAux += "'";
            }
            textoAux += " ";
        }

        i++;
    }

    return textoAux;

   }
    
    
    
}
