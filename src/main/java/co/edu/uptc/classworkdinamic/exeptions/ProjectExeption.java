package co.edu.uptc.classworkdinamic.exeptions;

public class ProjectExeption extends Exception {
private int code;

    public ProjectExeption(int code, String message) {
        super(message);
        this.code = code;        
    }
    

    
}
