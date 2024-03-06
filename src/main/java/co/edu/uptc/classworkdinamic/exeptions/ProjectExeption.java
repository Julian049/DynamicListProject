package co.edu.uptc.classworkdinamic.exeptions;

public class ProjectExeption extends Exception {
private TypeMessage typeMessage;

    public ProjectExeption(TypeMessage typeMessage) {
        super(typeMessage.message);
        this.typeMessage = typeMessage;        
    }
    

    public Mensaje getMenssage(){
        return new Mensaje(this.typeMessage.code,
         this.typeMessage.message, 
         this.typeMessage.codeHttp);
            
    }
    
}
