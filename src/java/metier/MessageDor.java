package metier;

public class MessageDor {
    
    private int id;
    private String pseudo;
    private String message;
    
    public MessageDor (){
        
    }
    
    //utile pour creation
    public MessageDor (String pseudo, String message){
        this.pseudo = pseudo;
        this.message = message;
    }

    //utile pour modif et sup
    public MessageDor (int numero, String pseudo, String message){
        this.id = numero;
        this.pseudo = pseudo;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
    
}
